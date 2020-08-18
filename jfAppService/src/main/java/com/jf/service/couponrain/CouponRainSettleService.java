package com.jf.service.couponrain;

import com.google.common.base.Joiner;
import com.google.common.collect.Maps;
import com.jf.common.constant.Const;
import com.jf.common.constant.StateConst;
import com.jf.common.utils.DateUtil;
import com.jf.dao.CouponRainCustomMapper;
import com.jf.dao.CouponRainRecordMapper;
import com.jf.dao.IntegralDtlMapper;
import com.jf.dao.MemberAccountMapper;
import com.jf.dao.MemberCouponMapper;
import com.jf.entity.Coupon;
import com.jf.entity.CouponRainRecord;
import com.jf.entity.CouponRainSetup;
import com.jf.entity.IntegralDtl;
import com.jf.entity.MemberAccount;
import com.jf.entity.MemberAccountExample;
import com.jf.entity.MemberCoupon;
import com.jf.vo.enumeration.RainCouponType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * @author luoyb
 * Created on 2019/9/27
 */
@Service
public class CouponRainSettleService {

    @Autowired
    private CouponRainCustomMapper couponRainCustomMapper;
    @Autowired
    private MemberAccountMapper memberAccountMapper;
    @Autowired
    private CouponRainRecordMapper couponRainRecordMapper;
    @Autowired
    private MemberCouponMapper memberCouponMapper;
    @Autowired
    private IntegralDtlMapper integralDtlMapper;

    @Transactional
    public CouponRainRecResult recCoupon(CouponRainSettleContext context, RainCouponType type) {
        Coupon coupon = null;
        if (RainCouponType.PRODUCT == type) {
            coupon = randomOneProductCoupon(context.getMemberId(), context.getCouponRainSetup());
        } else if (RainCouponType.PLATFORM == type) {
            coupon = randomOnePlatformCoupon(context.getMemberId(), context.getCouponRainSetup(),context.getOriginScore());
        } else if (RainCouponType.AREA == type) {
            coupon = randomOneAreaCoupon(context.getMemberId(), context.getCouponRainSetup());
        }
        if (coupon == null) return CouponRainRecResult.fail(); //无该类型的券可领取了

        int successCount = couponRainCustomMapper.increaseCouponRecCount(coupon.getId());
        if (successCount == 0) {
            return CouponRainRecResult.fail(true); //该券已被领完
        }
        MemberCoupon memberCoupon = saveMemberCoupon(coupon, context.getMemberId());
        return CouponRainRecResult.success(coupon, memberCoupon);
    }

    private MemberCoupon saveMemberCoupon(Coupon coupon, Integer memberId) {
        Date now = new Date();
        MemberCoupon memberCoupon = new MemberCoupon();
        memberCoupon.setMemberId(memberId);
        memberCoupon.setCouponId(coupon.getId());
        memberCoupon.setRecDate(now);
        if ("2".equals(coupon.getExpiryType())) { //相对时间
            memberCoupon.setExpiryBeginDate(now);
            memberCoupon.setExpiryEndDate(DateUtil.addDay(now, coupon.getExpiryDays()));
        } else { //绝对时间
            memberCoupon.setExpiryBeginDate(coupon.getExpiryBeginDate());
            memberCoupon.setExpiryEndDate(coupon.getExpiryEndDate());
        }
        memberCoupon.setDelFlag(StateConst.FALSE);
        memberCoupon.setReceiveType("8");
        memberCoupon.setCreateBy(memberId);
        memberCoupon.setCreateDate(now);
        memberCouponMapper.insertSelective(memberCoupon);
        return memberCoupon;
    }

    public Coupon randomOneAreaCoupon(Integer memberId, CouponRainSetup setup) {
        Map<String, Object> params = buildRandomParams(memberId, setup);
        return couponRainCustomMapper.randomOneAreaCoupon(params);
    }

    public Coupon randomOneProductCoupon(Integer memberId, CouponRainSetup setup) {
        Map<String, Object> params = buildRandomParams(memberId, setup);
        return couponRainCustomMapper.randomOneProductCoupon(params);
    }

    public Coupon randomOnePlatformCoupon(Integer memberId, CouponRainSetup setup, Integer originScore) {
        Map<String, Object> params = buildRandomParams(memberId, setup);
        params.put("originScore", originScore);
        return couponRainCustomMapper.randomOnePlatformCoupon(params);
    }

    private Map<String, Object> buildRandomParams(Integer memberId, CouponRainSetup setup) {
        Date now = new Date();
        Map<String, Object> params = Maps.newHashMap();
        params.put("memberId", memberId);
        params.put("now", now);
        params.put("recBeginDate", setup.getRecBeginDate());
        params.put("recEndDate", setup.getRecEndDate());
        params.put("startOfToday", DateUtil.getDateAfterAndBeginTime(now, 0));
        return params;
    }

    @Transactional
    public void UpdateMemberIntegral(Integer memberId, int integral) {
        MemberAccountExample memberAccountExample = new MemberAccountExample();
        memberAccountExample.createCriteria().andMemberIdEqualTo(memberId).andDelFlagEqualTo("0");
        List<MemberAccount> memberAccounts = memberAccountMapper.selectByExample(memberAccountExample);
        if (CollectionUtils.isEmpty(memberAccounts)) return;

        Date now = new Date();
        MemberAccount memberAccount = memberAccounts.get(0);
        memberAccount.setIntegral(memberAccount.getIntegral() + integral);
        memberAccount.setUpdateBy(memberId);
        memberAccount.setUpdateDate(now);
        memberAccountMapper.updateByPrimaryKey(memberAccount);

        IntegralDtl integralDtl = new IntegralDtl();
        integralDtl.setAccId(memberAccount.getId());

        integralDtl.setIntegral(integral);
        integralDtl.setBalance(memberAccount.getIntegral());
        integralDtl.setTallyMode(Const.INTEGRAL_TALLY_MODE_INCOME);
        integralDtl.setType(Const.INTEGRAL_TYPE_COUPON_RAIN);
        integralDtl.setCreateBy(memberId);
        integralDtl.setCreateDate(now);
        integralDtl.setDelFlag(StateConst.FALSE);
        integralDtlMapper.insert(integralDtl);
    }

    @Transactional
    public void updateRecord(CouponRainSettleContext context) {
        CouponRainRecord record = context.getCouponRainRecord();
        record.setIntegral(context.getIntegral());
        record.setMemberCouponIds(Joiner.on(",").join(context.getMemberCouponIdList()));
        record.setUpdateBy(context.getMemberId());
        record.setUpdateDate(new Date());
        record.setRecCount(context.getRecPackCount());
        couponRainRecordMapper.updateByPrimaryKey(record);
    }
}
