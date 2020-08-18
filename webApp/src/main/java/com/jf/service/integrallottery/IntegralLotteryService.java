package com.jf.service.integrallottery;

import com.google.common.collect.Maps;
import com.jf.common.constant.Const;
import com.jf.common.constant.StateConst;
import com.jf.common.ext.exception.BizException;
import com.jf.common.utils.CollectionUtil;
import com.jf.common.utils.Config;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.NumberUtil;
import com.jf.common.utils.StringUtil;
import com.jf.dao.CouponMapper;
import com.jf.dao.CouponRainCustomMapper;
import com.jf.dao.MemberCouponMapper;
import com.jf.dao.ProductCustomMapper;
import com.jf.entity.Coupon;
import com.jf.entity.IntegralDtl;
import com.jf.entity.LotterySettings;
import com.jf.entity.MemberAccount;
import com.jf.entity.MemberCoupon;
import com.jf.entity.MemberLottery;
import com.jf.entity.ProductCustom;
import com.jf.entity.SourceNiche;
import com.jf.entity.dto.MemberLotteryDTO;
import com.jf.service.CouponService;
import com.jf.service.IntegralDtlService;
import com.jf.service.MemberAccountService;
import com.jf.service.MemberShareService;
import com.jf.service.ProductItemService;
import com.jf.service.SourceNicheService;
import com.jf.service.SysParamCfgService;
import com.jf.vo.response.integrallottery.GetIntegralLotteryInfoResponse;
import com.jf.vo.response.integrallottery.IntegralLotteryDrawResponse;
import com.jf.vo.response.integrallottery.LotterySettingsView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author luoyb
 * Created on 2020/7/21
 */
@Service
public class IntegralLotteryService {

    private static Logger logger = LoggerFactory.getLogger(IntegralLotteryService.class);

    @Autowired
    private MemberAccountService memberAccountService;
    @Autowired
    private SysParamCfgService sysParamCfgService;
    @Autowired
    private MemberLotteryService memberLotteryService;
    @Autowired
    private LotterySettingsService lotterySettingsService;
    @Autowired
    private CouponService couponService;
    @Autowired
    private SourceNicheService sourceNicheService;
    @Autowired
    private MemberShareService memberShareService;
    @Autowired
    private IntegralDtlService integralDtlService;
    @Autowired
    private CouponRainCustomMapper couponRainCustomMapper;
    @Autowired
    private CouponMapper couponMapper;
    @Autowired
    private MemberCouponMapper memberCouponMapper;
    @Autowired
    private ProductCustomMapper productCustomMapper;
    @Autowired
    private ProductItemService productItemService;

    private final static String PAGE_INFO_EXPIRED_CODE = "5001";

    public GetIntegralLotteryInfoResponse getIntegralLotteryInfo(Integer memberId) {
        Map<String, String> lotteryConfigMap = sysParamCfgService.getNameMapByCode("INTEGRAL_LOTTERY_CONFIG");
        int memberIntegral = 0;
        int restFreeTimes = 0;
        boolean isFree = false;
        if (memberId != null) {
            int shareTimes = memberShareService.countMemberShareTimes(memberId, "1", null);
            int freeTimes = memberLotteryService.countMemberFreeTimes(memberId); //已免费抽奖次数
            int shareForFreeTimes = Integer.parseInt(lotteryConfigMap.get("SHARE_FOR_FREE_TIMES"));
            isFree = freeTimes < shareForFreeTimes && freeTimes < shareTimes;
            if (isFree) {
                if (shareTimes > shareForFreeTimes) {
                    restFreeTimes = shareForFreeTimes - freeTimes;
                } else {
                    restFreeTimes = shareTimes - freeTimes;
                }
            }
            MemberAccount memberAccount = memberAccountService.findAccountByMemberId(memberId);
            memberIntegral = memberAccount.getIntegral();
        }

        GetIntegralLotteryInfoResponse response = new GetIntegralLotteryInfoResponse();
        fillWithLotterySettings(response); // 填充转盘列表
        response.setMemberIntegral(memberIntegral);
        response.setInformationId(307); //规则ID
        response.setPrivacyPolicyUrl(StringUtil.buildMsg("{}/xgbuy/views/index.html?redirect_url=link/information.html?id={}", Config.getProperty("mUrl"), response.getInformationId())); //协议地址
        response.setPerIntegralSpend(isFree ? 0 : Integer.parseInt(lotteryConfigMap.get("PER_INTEGRAL_SPEND")));
        response.setFree(isFree);
        if (isFree) {
            response.setRestFreeTimes(restFreeTimes);
        }
        fillWithMemberLotteryLog(response);

        //分享信息
        response.setWxPath("page/activity/lottery/index");
        response.setOriginalId(Config.getProperty("originalId"));
        response.setXcxShareType(Config.getProperty("xcxShareType"));
        response.setWxShareContent("0元抽奖不来吗？免费抽上千好物立即参与。");
        response.setWxSharePic(StringUtil.getPic("/app/2020/integral_lottery_share_pic.jpg", "")); //积分转盘分享图片

        return response;
    }

    private boolean freeAble(int memberId, int shareForFreeTimes, int shareTimes) {
        int freeTimes = memberLotteryService.countMemberFreeTimes(memberId); //已免费抽奖次数
        return freeTimes < shareForFreeTimes && freeTimes < shareTimes;
    }

    private void fillWithLotterySettings(GetIntegralLotteryInfoResponse response) {
        Map<Integer, LotterySettings> settingsMap = lotterySettingsService.getLotterySettingsMap();
        int productIndex = 1; //第N件商品
        for (int i = 1; i <= 8; i++) {
            LotterySettings settings = settingsMap.get(i);
            if (settings == null) {
                logger.warn("积分转盘配置异常，存在未配置的盘位");
                throw new BizException("系统异常，请稍后重试");
            }
            LotterySettingsView view = new LotterySettingsView();
            view.setPosition(i);
            view.setType(settings.getType());
            if ("1".equals(settings.getType())) { //积分
                view.setIntegral(settings.getIntegral());
            } else if ("2".equals(settings.getType())) { //优惠券（商品券）
                Coupon coupon = couponService.popOneLotteryCoupon(settings.getMinAmount(), settings.getMaxAmount());
                if (coupon != null) {
                    ProductCustom productCustom = productCustomMapper.getProductInfo(Integer.valueOf(coupon.getTypeIds()));
                    view.setCouponId(coupon.getId());
                    view.setDenomination(coupon.getMoney().stripTrailingZeros().toPlainString());
                    view.setProductPic(StringUtil.getPic(productCustom.getPic(), ""));
                } else {
                    view.setNoStock(true);  //无库存，也显示谢谢惠顾
                }
            } else if ("3".equals(settings.getType())) { //商品
                ProductCustom productCustom = getLotteryProductInfo(productIndex);
                if (productCustom != null) {
                    view.setProductId(productCustom.getId());
//                    view.setProductName(memberLotteryService.decorateProductName(productCustom.getName()));
                    view.setProductName("抽奖免费拿");
                    view.setProductPic(StringUtil.getPic(productCustom.getPic(), ""));
                } else {
                    view.setNoStock(true); //无库存，也显示谢谢惠顾
                }
                productIndex++;
            }
            response.getSettingList().add(view);
        }
    }

    private ProductCustom getLotteryProductInfo(int productIndex) {
        SourceNiche sourceNiche = sourceNicheService.getLotteryProduct(productIndex);
        if (sourceNiche == null) {
            return null;
        }
        ProductCustom productCustom = productCustomMapper.getProductInfo(sourceNiche.getLinkId());
        int stock = productItemService.getProductStock(productCustom.getId());
        if (stock < 5 || "0".equals(productCustom.getStatus())) { //库存小于5 或 已下架商品 要移除商品奖池
            sourceNicheService.removeLotteryProduct(sourceNiche);
            return getLotteryProductInfo(productIndex);
        }
        return productCustom;
    }

    private void fillWithMemberLotteryLog(GetIntegralLotteryInfoResponse response) {
        List<MemberLotteryDTO> list = memberLotteryService.findLatest20WinningLog();
        if (!CollectionUtils.isEmpty(list)) {
            for (MemberLotteryDTO dto : list) {
                String nick = codeLotteryMemberNick(dto.getNickName());
                String content = StringUtil.buildMsg("{}在积分抽奖中获得\"{}\"", nick, memberLotteryService.getPrizeName(dto));
                response.getPrizeDescList().add(content);
            }
        }
    }

    private String codeLotteryMemberNick(String nickName) {
        if (!StringUtils.hasText(nickName)) {
            return "用户**";
        }
        int length = nickName.length();
        if (length <= 2) {
            return nickName;
        }
        return nickName.substring(0, 1) + "**" + nickName.substring(length - 1, length);
    }

    /**
     * 抽奖前校验，并构造抽奖上下文
     */
    public DrawContext validateBeforeDraw(List<LotterySettingsView> pageSettingsList, boolean free, Integer memberId) {
        MemberAccount memberAccount = memberAccountService.findAccountByMemberId(memberId);
        Map<String, String> lotteryConfigMap = sysParamCfgService.getNameMapByCode("INTEGRAL_LOTTERY_CONFIG");
        int shareForFreeTimes = Integer.parseInt(lotteryConfigMap.get("SHARE_FOR_FREE_TIMES"));
        int shareTimes = memberShareService.countMemberShareTimes(memberId, "1", null);
        if (free && !freeAble(memberId, shareForFreeTimes, shareTimes)) {
            throw new BizException("页面信息已过期，请刷新页面后重试！", PAGE_INFO_EXPIRED_CODE);
        }
        int integralSpend = free ? 0 : Integer.parseInt(lotteryConfigMap.get("PER_INTEGRAL_SPEND"));
        if (memberAccount.getIntegral() < integralSpend) {
            throw new BizException("您的积分不足！");
        }
        Map<Integer, LotterySettings> settingsMap = lotterySettingsService.getLotterySettingsMap();
        Map<Integer, LotterySettingsView> pageSettingsMap = buildSettingsMap(pageSettingsList);

        DrawContext context = new DrawContext();
        context.setMemberId(memberId);
        context.setFree(free);
        context.setMemberAccount(memberAccount);
        context.setIntegralSpend(integralSpend);
        context.setSettingsMap(settingsMap);
        context.setPageSettingsMap(pageSettingsMap);
        context.setCurrentPoolIntegral(Integer.parseInt(lotteryConfigMap.get("INTEGRAL_POOL")));
        context.setShareForFreeTimes(Integer.parseInt(lotteryConfigMap.get("SHARE_FOR_FREE_TIMES")));
        context.setShareTimes(shareTimes);
        checkDrawData(context); //校验数据格式
        if (context.isContainProduct()) {
            SourceNiche lotteryProduct = sourceNicheService.getLotteryProduct(1); //从第一件商品开始抽取
            ProductCustom lotteryProductInfo = productCustomMapper.getProductInfo(lotteryProduct.getLinkId());
            context.setLotteryProduct(lotteryProduct);
            context.setLotteryProductInfo(lotteryProductInfo);
        }
        return context;
    }

    /**
     * 转盘抽奖
     */
    @Transactional
    public IntegralLotteryDrawResponse draw(DrawContext context) {
        IntegralLotteryDrawResponse response = new IntegralLotteryDrawResponse();

        // 使用积分
        integralLotterySpend(context);
        // 获取奖励
        getPrize(response, context);

        if (context.getShareTimes() < context.getShareForFreeTimes()) {
            response.setShareTip("分享后可免费获得一次抽奖机会");
        }
        return response;
    }

    private void getPrize(IntegralLotteryDrawResponse response, DrawContext context) {
        int integralSpend = context.getIntegralSpend(); //若为0表示本次免费
        Integer currentPoolIntegral = context.getCurrentPoolIntegral(); //当前积分池积分
        SourceNiche lotteryProduct = context.getLotteryProduct(); //当前可中奖的商品

        // 当转盘包含商品，非免费抽奖且本次抽奖的积分池积分足够换取商品时(C > T)，尝试竞争商品
        if (!context.isFree() && context.isContainProduct() && lotteryProduct != null
                && currentPoolIntegral > lotteryProduct.getIntegralCount()) {
            if (sysParamCfgService.increasePoolIntegral(currentPoolIntegral, integralSpend)) { //竞争成功
                // 奖励商品
                prizeProduct(response, context);
            } else { //竞争失败
                sysParamCfgService.increasePoolIntegral(null, integralSpend);
                // 随机奖励
                prizeRandom(response, context);
            }
        } else {
            if (integralSpend > 0) {
                sysParamCfgService.increasePoolIntegral(null, integralSpend);
            }
            // 随机奖励
            prizeRandom(response, context);
        }
    }


    /**
     * 奖励随机
     */
    private void prizeRandom(IntegralLotteryDrawResponse response, DrawContext context) {
        int prizePosition = randomPrizePosition(context.getSettingsMap());
        if (prizePosition == 0) {
            //奖励最低
            prizeMin(response, context);
            return;
        }

        LotterySettingsView pageSettings = context.getPageSettingsMap().get(prizePosition);
        if ("1".equals(pageSettings.getType())) { //积分
            //奖励积分
            prizeIntegral(response, context, prizePosition, pageSettings.getIntegral());
        } else { //优惠券
            //奖励优惠券
            prizeCoupon(response, context, prizePosition, pageSettings.getCouponId());
        }
    }

    /**
     * 奖励优惠券
     */
    private void prizeCoupon(IntegralLotteryDrawResponse response, DrawContext context, int position, Integer couponId) {
        if (couponId == null) { //该券已被领完
            prizeMin(response, context);
            return;
        }
        int successCount = couponRainCustomMapper.increaseCouponRecCount(couponId);
        if (successCount == 0) { //该券已被领完
            //奖励最低
            prizeMin(response, context);
            return;
        }
        Coupon coupon = couponMapper.selectByPrimaryKey(couponId);
        ProductCustom productCustom = productCustomMapper.getProductInfo(Integer.valueOf(coupon.getTypeIds()));
        MemberCoupon memberCoupon = saveMemberCoupon(coupon, context);
        MemberLottery memberLottery = new MemberLottery();
        memberLottery.setMemberId(context.getMemberId());
        memberLottery.setConsumeIntegral(context.getIntegralSpend());
        memberLottery.setType("2");
        memberLottery.setRelevantId(memberCoupon.getId());
        memberLottery.setCouponId(coupon.getId());
        memberLottery.setCouponAmount(coupon.getMoney());
        memberLottery.setProductPic(productCustom.getPic());
        memberLottery.setCreateBy(context.getMemberId());
        memberLottery.setCreateDate(context.getNow());
        memberLottery.setDelFlag(StateConst.FALSE);
        memberLotteryService.insert(memberLottery);

        response.setWin(true);
        response.setPosition(position);
        response.setRecordId(memberLottery.getId());
        response.setProductPic(StringUtil.getPic(productCustom.getPic(), ""));
        response.setType("2");
        response.setPrizeTitle("恭喜您中奖啦！");
        response.setPrizeDesc(StringUtil.buildMsg("{}元", coupon.getMoney().stripTrailingZeros().toPlainString()));
        response.setPrizeSubDesc(StringUtil.buildMsg("{}-{}", DateUtil.getFormatDate(coupon.getExpiryBeginDate(), DateUtil.DATE_FORMAT_POINT), DateUtil.getFormatDate(coupon.getExpiryEndDate(), DateUtil.DATE_FORMAT_POINT)));

    }

    /**
     * 奖励积分
     */
    private void prizeIntegral(IntegralLotteryDrawResponse response, DrawContext context, int position, Integer integral) {
        // 获取积分
        MemberAccount memberAccount = context.getMemberAccount();
        memberAccountService.increaseIntegral(memberAccount.getId(), integral);
        memberAccount.setIntegral(memberAccount.getIntegral() + integral);
        IntegralDtl integralDtl = integralDtlService.addIntegralDtl(memberAccount.getId(), Const.INTEGRAL_TALLY_MODE_INCOME, Const.INTEGRAL_TYPE_INTEGRAL_LOTTERY_IN, integral, memberAccount.getIntegral(), null, context.getMemberId(), "");

        MemberLottery memberLottery = new MemberLottery();
        memberLottery.setMemberId(context.getMemberId());
        memberLottery.setConsumeIntegral(context.getIntegralSpend());
        memberLottery.setType("1");
        memberLottery.setIntegral(integral);
        memberLottery.setRelevantId(integralDtl.getId());
        memberLottery.setCreateBy(context.getMemberId());
        memberLottery.setCreateDate(context.getNow());
        memberLottery.setDelFlag(StateConst.FALSE);
        memberLotteryService.insert(memberLottery);

        response.setWin(true);
        response.setRecordId(memberLottery.getId());
        response.setPosition(position);
        response.setType("1");
        response.setPrizeTitle("恭喜您中奖啦！");
        response.setPrizeDesc(StringUtil.buildMsg("{}积分", integral));
        response.setPrizeSubDesc(StringUtil.buildMsg("{}积分已放入您的账户", integral));
    }

    /**
     * 奖励商品
     */
    private void prizeProduct(IntegralLotteryDrawResponse response, DrawContext context) {
        SourceNiche lotteryProduct = context.getLotteryProduct();
        // 扣除库存
        if (!sourceNicheService.decreaseLotteryProductStock(lotteryProduct.getId(), lotteryProduct.getStock())) {
            throw new BizException("系统繁忙中，请稍后重试！");
        }
        //重置积分池积分
        sysParamCfgService.resetPoolIntegral();

        ProductCustom product = context.getLotteryProductInfo();

        MemberLottery memberLottery = new MemberLottery();
        memberLottery.setMemberId(context.getMemberId());
        memberLottery.setConsumeIntegral(context.getIntegralSpend());
        memberLottery.setType("3");
        memberLottery.setProductId(product.getId());
        memberLottery.setProductName(product.getName());
        memberLottery.setBrand(product.getBrandName());
        memberLottery.setProductArtNo(product.getArtNo());
        memberLottery.setProductPic(product.getPic());
        memberLottery.setProductCode(product.getCode());
        memberLottery.setCreateBy(context.getMemberId());
        memberLottery.setCreateDate(context.getNow());
        memberLottery.setDelFlag(StateConst.FALSE);
        memberLotteryService.insert(memberLottery);

        response.setWin(true);
        response.setRecordId(memberLottery.getId());
        response.setPosition(context.getProductPrizePosition());
        response.setType("3");
        response.setPrizeTitle("恭喜您中奖啦！");
        response.setProductId(product.getId());
        response.setPrizeDesc(memberLotteryService.decorateProductName(product.getName()));
        response.setProductPic(StringUtil.getPic(product.getPic(), ""));
    }

    /**
     * 奖励为空
     */
    private void prizeNone(IntegralLotteryDrawResponse response, DrawContext context) {
        MemberLottery memberLottery = new MemberLottery();
        memberLottery.setMemberId(context.getMemberId());
        memberLottery.setConsumeIntegral(context.getIntegralSpend());
        memberLottery.setType("4");
        memberLottery.setCreateBy(context.getMemberId());
        memberLottery.setCreateDate(context.getNow());
        memberLottery.setDelFlag(StateConst.FALSE);
        memberLotteryService.insert(memberLottery);

        response.setWin(false);
        response.setPosition(context.getNonePrizePosition());
        response.setType("4");
        response.setPrizeTitle("很遗憾，您未中奖！");
    }

    /**
     * 奖励最低
     */
    private void prizeMin(IntegralLotteryDrawResponse response, DrawContext context) {
        if (context.getNonePrizePosition() != null) {
            prizeNone(response, context);
        } else if (context.getMinIntegralPosition() != null) {
            prizeIntegral(response, context, context.getMinIntegralPosition(), context.getMinIntegral());
        } else {
            logger.warn("未配置积分盘位");
            throw new BizException("系统异常，请稍后重试！");
        }
    }

    private void integralLotterySpend(DrawContext context) {
        int integralSpend = context.getIntegralSpend();
        if (integralSpend > 0) {
            MemberAccount memberAccount = context.getMemberAccount();
            if (!memberAccountService.decreaseIntegral(memberAccount.getId(), memberAccount.getIntegral(), integralSpend)) {
                throw new BizException("您的积分不足！");
            }

            memberAccount.setIntegral(memberAccount.getIntegral() - integralSpend);
            integralDtlService.addIntegralDtl(memberAccount.getId(), Const.INTEGRAL_TALLY_MODE_ACCOUNT, Const.INTEGRAL_TYPE_INTEGRAL_LOTTERY_OUT, integralSpend, memberAccount.getIntegral(), null, context.getMemberId(), "");
        }
    }

    //记录用户商品券
    private MemberCoupon saveMemberCoupon(Coupon coupon, DrawContext context) {
        MemberCoupon memberCoupon = new MemberCoupon();
        memberCoupon.setMemberId(context.getMemberId());
        memberCoupon.setCouponId(coupon.getId());
        memberCoupon.setRecDate(context.getNow());
        memberCoupon.setExpiryBeginDate(coupon.getExpiryBeginDate());
        memberCoupon.setExpiryEndDate(coupon.getExpiryEndDate());
        memberCoupon.setDelFlag(StateConst.FALSE);
        memberCoupon.setReceiveType("10");
        memberCoupon.setCreateBy(context.getMemberId());
        memberCoupon.setCreateDate(context.getNow());
        memberCouponMapper.insertSelective(memberCoupon);
        return memberCoupon;
    }

    /**
     * 根据转盘各自中奖概率计算中奖的位置。（积分、优惠券类型参与随机）
     * 基数为10000份，所有不满足条件均返回0，表示取最小
     */
    private int randomPrizePosition(Map<Integer, LotterySettings> settingsMap) {
        int randomPoint = NumberUtil.rand(0, 10000);
        int pointCount = 0;
        for (int i = 1; i <= 8; i++) {
            LotterySettings settings = settingsMap.get(i);
            if (Integer.parseInt(settings.getType()) <= 2) {
                pointCount += settings.getWinningProbability().multiply(NumberUtil.TEN_THOUSAND).intValue();
                if (randomPoint <= pointCount) {
                    return i;
                }
            }
        }
        return 0;
    }

    private void checkDrawData(DrawContext context) {
        Map<Integer, LotterySettings> settingsMap = context.getSettingsMap();
        Map<Integer, LotterySettingsView> pageSettingsMap = context.getPageSettingsMap();
        int productIndex = 1; //第N件商品
        for (int i = 1; i <= 8; i++) {
            LotterySettings settings = settingsMap.get(i);
            LotterySettingsView pageSettings = pageSettingsMap.get(i);
            if (settings == null) {
                logger.warn("积分转盘配置异常，存在未配置的盘位");
                throw new BizException("系统异常，请稍后重试！");
            }
            checkDataNotNull(pageSettings);

            if (!settings.getType().equals(pageSettings.getType())) {
                throw new BizException("页面信息已过期，请刷新页面后重试！", PAGE_INFO_EXPIRED_CODE);
            }

            if ("1".equals(settings.getType())) { //积分
                checkDataNotNull(pageSettings.getIntegral());

                if (!settings.getIntegral().equals(pageSettings.getIntegral())) {
                    throw new BizException("页面信息已过期，请刷新页面后重试！", PAGE_INFO_EXPIRED_CODE);
                }
                // 获取最低积分奖励
                if (context.getMinIntegral() == null || pageSettings.getIntegral() < context.getMinIntegral()) {
                    context.setMinIntegralPosition(i);
                    context.setMinIntegral(pageSettings.getIntegral());
                }
            } else if ("2".equals(settings.getType())) { //优惠券（商品券）
                if (pageSettings.getCouponId() == null) {
                    context.setNonePrizePosition(i); //谢谢惠顾
                } else {
                    Coupon coupon = couponService.selectByPrimaryKey(pageSettings.getCouponId());
                    if (coupon == null || !"4".equals(coupon.getCouponType()) || "0".equals(coupon.getIsIntegralTurntable())
                            || coupon.getMoney().compareTo(settings.getMinAmount()) < 0 || coupon.getMoney().compareTo(settings.getMaxAmount()) > 0) {
                        throw new BizException("页面信息已过期，请刷新页面后重试！", PAGE_INFO_EXPIRED_CODE);
                    }
                }
            } else if ("3".equals(settings.getType())) { //商品
                if (pageSettings.getProductId() == null) {
                    context.setNonePrizePosition(i); //谢谢惠顾
                } else {
                    ProductCustom productCustom = sourceNicheService.getLotteryProductInfo(productIndex);
                    if (!pageSettings.getProductId().equals(productCustom.getId())) {
                        throw new BizException("页面信息已过期，请刷新页面后重试！", PAGE_INFO_EXPIRED_CODE);
                    }
                    context.setContainProduct(true);
                    if (context.getProductPrizePosition() == null) {
                        context.setProductPrizePosition(i);
                    }
                }
                productIndex++;
            }
        }
    }

    private void checkDataNotNull(Object val) {
        if (val == null) {
            throw new BizException("数据异常，抽奖失败！");
        }
    }

    private Map<Integer, LotterySettingsView> buildSettingsMap(List<LotterySettingsView> pageSettingsList) {
        if (CollectionUtils.isEmpty(pageSettingsList)) {
            return Collections.emptyMap();
        }
        Map<Integer, LotterySettingsView> map = Maps.newHashMap();
        for (LotterySettingsView settings : pageSettingsList) {
            map.put(settings.getPosition(), settings);
        }
        return map;
    }

    /**
     * 重置商品奖池排序
     */
    @Transactional
    public void reorderLotteryProduct(Integer productId) {
        List<SourceNiche> list = sourceNicheService.findLotteryProduct();
        if (CollectionUtil.isEmpty(list)) {
            return;
        }
        int index = 1;
        for (SourceNiche sourceNiche : list) {
            // 刚被抽中过的商品排到最后
            if (sourceNiche.getLinkId().equals(productId)) {
                sourceNiche.setSeqNo(list.size());
            } else {
                sourceNiche.setSeqNo(index++);
            }
            sourceNiche.setUpdateDate(new Date());
            sourceNicheService.updateByPrimaryKeySelective(sourceNiche);
        }
    }

    @Transactional
    public void logShare(Integer memberId) {
        memberShareService.saveMemberShare(memberId, "1", null);
    }
}
