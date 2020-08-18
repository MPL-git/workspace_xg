package com.jf.service.couponrain;

import com.google.common.collect.Maps;
import com.jf.common.AppBaseService;
import com.jf.common.constant.StateConst;
import com.jf.common.ext.exception.BizException;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.NumberUtil;
import com.jf.common.utils.StringUtil;
import com.jf.dao.CouponRainCustomMapper;
import com.jf.dao.CouponRainMapper;
import com.jf.dao.CouponRainSetupMapper;
import com.jf.dao.CouponRainShareRecordMapper;
import com.jf.entity.CouponRain;
import com.jf.entity.CouponRainExample;
import com.jf.entity.CouponRainRecord;
import com.jf.entity.CouponRainSetup;
import com.jf.entity.CouponRainShareRecord;
import com.jf.entity.CouponRainShareRecordExample;
import com.jf.entity.dto.CouponRainDTO;
import com.jf.vo.enumeration.RainCouponType;
import com.jf.vo.response.couponrain.ParticipateResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * @author luoyb
 * Created on 2019/12/12
 */
@Service
public class CouponRainService extends AppBaseService<CouponRain, CouponRainExample> {

    private static Logger logger = LoggerFactory.getLogger(CouponRainService.class);

    @Autowired
    private CouponRainMapper couponRainMapper;
    @Autowired
    private CouponRainCustomMapper couponRainCustomMapper;
    @Autowired
    private CouponRainSetupMapper couponRainSetupMapper;
    @Autowired
    private CouponRainRecordService couponRainRecordService;
    @Autowired
    private CouponRainSettleService couponRainSettleService;
    @Autowired
    private CouponRainShareRecordMapper couponRainShareRecordMapper;

    @Autowired
    public void setDao() {
        super.setDao(couponRainMapper);
    }

    public CouponRainDTO getEnableFirstRain(Integer memberId, boolean fromApp) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("memberId", memberId);
        params.put("endTime", fromApp ? DateUtil.addSecond(new Date(), -3) : new Date());
        return couponRainCustomMapper.getEnableFirstRain(params);
    }

    @Transactional
    public ParticipateResponse participate(Integer couponRainId, Integer memberId) {
        CouponRain couponRain = validateBeforeParticipate(couponRainId, memberId);

        CouponRainRecord newRecord = new CouponRainRecord();
        newRecord.setMemberId(memberId);
        newRecord.setCouponRainId(couponRainId);
        newRecord.setCreateBy(memberId);
        newRecord.setCreateDate(new Date());
        newRecord.setDelFlag(StateConst.FALSE);
        couponRainRecordService.insert(newRecord);

        int participateCount = couponRainCustomMapper.countCouponRainParticipate(couponRainId);
        couponRain.setMemberCount(participateCount); //更新参与人数
        updateByPrimaryKey(couponRain);

        CouponRainSetup setup = getCouponRainSetup(couponRain.getCouponRainSetupId());
        ParticipateResponse response = new ParticipateResponse(newRecord.getId());
        String rainType = "P";
        if (setup != null && !StringUtil.isBlank(setup.getRedEnvelopeType())) {
            rainType = setup.getRedEnvelopeType();
        }
        response.setRainType(rainType);
        return response;
    }

    /**
     * 红包雨结算
     * 结算先后顺序：积分、商品券、平台券、特卖券
     */
    public CouponRainSettleContext settle(Integer memberId, Integer recordId, Integer score) {
        CouponRainSettleContext context = validateBeforeSettle(memberId, recordId, score);
        try {
            settleWithIntegral(context);
            settleProductCoupon(context);
            settlePlatformCoupon(context);
            settleAreaCoupon(context);
        } catch (Exception e) {
            logger.warn(StringUtil.buildMsg("用户【{}】红包雨活动记录ID:{}，结算异常{}", memberId, recordId, context.printInfo()), e);
        }
        logger.debug(StringUtil.buildMsg("用户【{}】红包雨活动记录ID:{},获取积分红包{}个,券红包{}个", memberId, recordId, context.getIntegralPackCount(), context.getCouponPackCount()));
        context.calculateRecPackCount();
        couponRainSettleService.updateRecord(context); //更新用户红包雨活动记录
        return context;
    }

    public CouponRainSetup getCouponRainSetup(Integer couponRainSetupId) {
        return couponRainSetupMapper.selectByPrimaryKey(couponRainSetupId);
    }

    @Transactional
    public boolean logShareRecord(Integer memberId, Integer couponRainId) {
        //未分享过的红包雨，再次分享能获得一次机会，必须在记录前判断
        boolean gameAgainAble = countMemberCouponRainShareRecord(couponRainId, memberId) == 0;

        CouponRainShareRecord record = new CouponRainShareRecord();
        record.setMemberId(memberId);
        record.setCouponRainId(couponRainId);
        record.setCreateBy(memberId);
        record.setCreateDate(new Date());
        record.setDelFlag(StateConst.FALSE);
        couponRainShareRecordMapper.insert(record);
        return gameAgainAble;
    }

    /**
     * 仅支持分享一次，未分享过的用户提示分享
     */
    public boolean checkMemberShareAble(Integer memberId, Integer couponRainId) {
        return countMemberCouponRainShareRecord(couponRainId, memberId) == 0;
    }

    private CouponRain validateBeforeParticipate(Integer couponRainId, Integer memberId) {
        CouponRain couponRain = selectByPrimaryKey(couponRainId);
        if (couponRain == null || StateConst.OFFLINE.equals(couponRain.getStatus()) || StateConst.TRUE.equals(couponRain.getDelFlag())) {
            throw new BizException("红包雨活动已下架");
        }
        Date now = new Date();
        if (couponRain.getStartTime().after(now)) {
            throw new BizException("红包雨活动尚未开始");
        }
        if (couponRain.getEndTime().before(now)) {
            throw new BizException("红包雨活动已经结束");
        }
        int participateCount = couponRainRecordService.countCouponRainMemberParticipate(couponRainId, memberId);
        if (participateCount > 0) {
            int shareCount = countMemberCouponRainShareRecord(couponRainId, memberId);
            //为分享过仅能参与一次，分享过仅能参与两次
            if (shareCount == 0 || participateCount > 1) {
                throw new BizException("您已参与过本次活动");
            }
        }
        return couponRain;
    }

    private int countMemberCouponRainShareRecord(Integer couponRainId, Integer memberId) {
        CouponRainShareRecordExample example = new CouponRainShareRecordExample();
        example.createCriteria().andMemberIdEqualTo(memberId).andCouponRainIdEqualTo(couponRainId).andDelFlagEqualTo(StateConst.FALSE);
        return couponRainShareRecordMapper.countByExample(example);
    }

    private void settleAreaCoupon(CouponRainSettleContext context) {
        CouponRainSetup setup = context.getCouponRainSetup();
        if (!StateConst.TRUE.equals(setup.getIncludeSale()) || context.getRestPackCount() == 0) return;

        int areaCouponPackCount = calculateCount(context.getRestPackCount(), context.getRecAblePackCount(), setup.getIncludeSalePercent());
        if (areaCouponPackCount == 0) return;

        recCoupon(context, areaCouponPackCount, RainCouponType.AREA);
    }

    private void settlePlatformCoupon(CouponRainSettleContext context) {
        CouponRainSetup setup = context.getCouponRainSetup();
        if (!StateConst.TRUE.equals(setup.getIncludePlatformCoupon()) || context.getRestPackCount() == 0) return;

        int platformCouponPackCount = calculateCount(context.getRestPackCount(), context.getRecAblePackCount(), setup.getIncludePlatformCouponPercent());
        if (platformCouponPackCount == 0) return;

        recCoupon(context, platformCouponPackCount, RainCouponType.PLATFORM);
    }

    private void settleProductCoupon(CouponRainSettleContext context) {
        CouponRainSetup setup = context.getCouponRainSetup();
        if (!StateConst.TRUE.equals(setup.getIncludeProductCoupon()) || context.getRestPackCount() == 0) return;

        int productCouponPackCount = calculateCount(context.getRestPackCount(), context.getRecAblePackCount(), setup.getIncludeProductCouponPercent());
        if (productCouponPackCount == 0) return;

        recCoupon(context, productCouponPackCount, RainCouponType.PRODUCT);
    }

    private void settleWithIntegral(CouponRainSettleContext context) {
        CouponRainSetup setup = context.getCouponRainSetup();
        if (!StateConst.TRUE.equals(setup.getIncludeIntegral()) || context.getRestPackCount() == 0) return;

        int integralPackCount = calculateCount(context.getRestPackCount(), context.getRecAblePackCount(), setup.getIncludeIntegralPercent());
        if (integralPackCount == 0) return;

        int integral = 0;
        for (int i = 1; i <= integralPackCount; i++) {
            integral += NumberUtil.rand(setup.getIncludeIntegralMin(), setup.getIncludeIntegralMax());
        }
        couponRainSettleService.UpdateMemberIntegral(context.getMemberId(), integral);
        context.addIntegralPackCount(integralPackCount);
        context.reduceRestPackCount(integralPackCount);
        context.setIntegral(integral);
    }

    private void recCoupon(CouponRainSettleContext context, int couponPackCount, RainCouponType rainCouponType) {
        while (couponPackCount > 0) {
            CouponRainRecResult recResult = couponRainSettleService.recCoupon(context, rainCouponType);
            if (!recResult.isSuccess()) {
                if (recResult.isCurrentCouponExhausted()) {
                    continue;  //该券已被领完
                }
                break;  //无该类型的券可领取了
            }
            couponPackCount--;

            if (RainCouponType.PRODUCT == rainCouponType) {
                context.getProductCouponList().add(recResult.getCoupon());
            } else if (RainCouponType.PLATFORM == rainCouponType) {
                context.getPlatformCouponList().add(recResult.getCoupon());
            } else if (RainCouponType.AREA == rainCouponType) {
                context.getAreaCouponList().add(recResult.getCoupon());
            }
            context.getMemberCouponIdList().add(recResult.getMemberCoupon().getId());
            context.addCouponPackCount();
            context.reduceRestPackCount();
        }
    }

    private int calculateCount(int restCount, int totalCount, BigDecimal percent) {
        if (percent == null) return 0;

        int result = new BigDecimal(totalCount).multiply(percent).setScale(0, BigDecimal.ROUND_HALF_UP).intValue(); //四舍五入
        return NumberUtil.min(restCount, result);
    }

    private CouponRainSettleContext validateBeforeSettle(Integer memberId, Integer recordId, Integer score) {
        CouponRainRecord record = couponRainRecordService.getById(recordId);
        if (record == null || !record.getMemberId().equals(memberId) || StateConst.TRUE.equals(record.getDelFlag())) {
            throw new BizException("数据异常，请刷新后重试");
        }
        if (record.getUpdateDate() != null) {
            throw new BizException("数据异常，不能重复结算");
        }
        CouponRain rain = couponRainMapper.selectByPrimaryKey(record.getCouponRainId());
        if (rain == null || StateConst.TRUE.equals(rain.getDelFlag())) {
            throw new BizException("数据异常，请刷新后重试");
        }
        CouponRainSetup setup = couponRainSetupMapper.selectByPrimaryKey(rain.getCouponRainSetupId());
        if (setup == null || StateConst.TRUE.equals(setup.getDelFlag())) {
            throw new BizException("数据异常，请刷新后重试");
        }
        if (StateConst.OFFLINE.equals(rain.getStatus())) {
            throw new BizException("抱歉，活动已下架");
        }

        // 初始化结算数据
        CouponRainSettleContext context = CouponRainSettleContext.of(memberId, score, setup, rain, record);
        int totalPackCount = NumberUtil.filterLimit(setup.getLimitRecCount(), score);
        context.setRecAblePackCount(totalPackCount);
        context.setRestPackCount(totalPackCount);
        return context;
    }

}
