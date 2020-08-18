package com.jf.service.integrallottery;

import com.jf.entity.LotterySettings;
import com.jf.entity.MemberAccount;
import com.jf.entity.ProductCustom;
import com.jf.entity.SourceNiche;
import com.jf.vo.response.integrallottery.LotterySettingsView;

import java.util.Date;
import java.util.Map;

/**
 * @author luoyb
 * Created on 2020/7/22
 */
public class DrawContext {

    private final Date now = new Date();

    private Integer memberId;
    private boolean free;
    private MemberAccount memberAccount;
    private Integer integralSpend; //本次抽奖消耗积分
    private Map<Integer, LotterySettings> settingsMap; //当前转盘配置map
    private Map<Integer, LotterySettingsView> pageSettingsMap; //页面转盘配置map
    private Integer currentPoolIntegral; //当前积分池积分
    private int shareForFreeTimes; //分享可获取免费抽奖次数上限
    private int shareTimes; //已分享次数
    private SourceNiche lotteryProduct; //当前可中奖的商品
    private ProductCustom lotteryProductInfo;

    private boolean containProduct;
    private Integer productPrizePosition; //第一个商品位置
    private Integer minIntegralPosition;
    private Integer minIntegral;
    private Integer nonePrizePosition; //谢谢惠顾位置（最后一个）

    public ProductCustom getLotteryProductInfo() {
        return lotteryProductInfo;
    }

    public void setLotteryProductInfo(ProductCustom lotteryProductInfo) {
        this.lotteryProductInfo = lotteryProductInfo;
    }

    public int getShareTimes() {
        return shareTimes;
    }

    public void setShareTimes(int shareTimes) {
        this.shareTimes = shareTimes;
    }

    public int getShareForFreeTimes() {
        return shareForFreeTimes;
    }

    public void setShareForFreeTimes(int shareForFreeTimes) {
        this.shareForFreeTimes = shareForFreeTimes;
    }

    public Integer getProductPrizePosition() {
        return productPrizePosition;
    }

    public void setProductPrizePosition(Integer productPrizePosition) {
        this.productPrizePosition = productPrizePosition;
    }

    public Date getNow() {
        return now;
    }

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    public SourceNiche getLotteryProduct() {
        return lotteryProduct;
    }

    public void setLotteryProduct(SourceNiche lotteryProduct) {
        this.lotteryProduct = lotteryProduct;
    }

    public Integer getCurrentPoolIntegral() {
        return currentPoolIntegral;
    }

    public void setCurrentPoolIntegral(Integer currentPoolIntegral) {
        this.currentPoolIntegral = currentPoolIntegral;
    }

    public Map<Integer, LotterySettings> getSettingsMap() {
        return settingsMap;
    }

    public void setSettingsMap(Map<Integer, LotterySettings> settingsMap) {
        this.settingsMap = settingsMap;
    }

    public Map<Integer, LotterySettingsView> getPageSettingsMap() {
        return pageSettingsMap;
    }

    public void setPageSettingsMap(Map<Integer, LotterySettingsView> pageSettingsMap) {
        this.pageSettingsMap = pageSettingsMap;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public MemberAccount getMemberAccount() {
        return memberAccount;
    }

    public void setMemberAccount(MemberAccount memberAccount) {
        this.memberAccount = memberAccount;
    }

    public Integer getIntegralSpend() {
        return integralSpend;
    }

    public void setIntegralSpend(Integer integralSpend) {
        this.integralSpend = integralSpend;
    }

    public boolean isContainProduct() {
        return containProduct;
    }

    public void setContainProduct(boolean containProduct) {
        this.containProduct = containProduct;
    }

    public Integer getMinIntegralPosition() {
        return minIntegralPosition;
    }

    public void setMinIntegralPosition(Integer minIntegralPosition) {
        this.minIntegralPosition = minIntegralPosition;
    }

    public Integer getMinIntegral() {
        return minIntegral;
    }

    public void setMinIntegral(Integer minIntegral) {
        this.minIntegral = minIntegral;
    }

    public Integer getNonePrizePosition() {
        return nonePrizePosition;
    }

    public void setNonePrizePosition(Integer nonePrizePosition) {
        this.nonePrizePosition = nonePrizePosition;
    }
}
