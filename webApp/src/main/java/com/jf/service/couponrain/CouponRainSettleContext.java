package com.jf.service.couponrain;

import com.google.common.collect.Lists;
import com.jf.common.utils.StringUtil;
import com.jf.entity.Coupon;
import com.jf.entity.CouponRain;
import com.jf.entity.CouponRainRecord;
import com.jf.entity.CouponRainSetup;

import java.util.List;

/**
 * @author luoyb
 * Created on 2019/12/12
 */
public class CouponRainSettleContext {

    private Integer memberId;
    private Integer originScore;
    private CouponRainSetup couponRainSetup; //红包雨配置
    private CouponRain couponRain; //红包雨活动
    private CouponRainRecord couponRainRecord; //结算记录

    private int recAblePackCount; //实际可领取红包数
    private int recPackCount; //实际领取红包数
    private int integralPackCount;
    private int couponPackCount;

    private int restPackCount;

    private int integral;
    private final List<Coupon> productCouponList = Lists.newArrayList();
    private final List<Coupon> platformCouponList = Lists.newArrayList();
    private final List<Coupon> areaCouponList = Lists.newArrayList();
    private final List<Integer> memberCouponIdList = Lists.newArrayList(); //领取成功的用户券idList

    private CouponRainSettleContext() {
    }

    public static CouponRainSettleContext of(Integer memberId, Integer originScore, CouponRainSetup couponRainSetup, CouponRain couponRain, CouponRainRecord couponRainRecord) {
        CouponRainSettleContext context = new CouponRainSettleContext();
        context.setMemberId(memberId);
        context.setOriginScore(originScore);
        context.setCouponRain(couponRain);
        context.setCouponRainSetup(couponRainSetup);
        context.setCouponRainRecord(couponRainRecord);
        return context;
    }

    //积分红包数 +1
    public void addIntegralPackCount() {
        integralPackCount++;
    }

    //券红包数 +1
    public void addCouponPackCount() {
        couponPackCount++;
    }

    //剩余可领红包数 -1
    public void reduceRestPackCount() {
        if (restPackCount > 0) {
            restPackCount--;
        } else {
            restPackCount = 0;
        }
    }

    public void addIntegralPackCount(Integer val) {
        integralPackCount += val;
    }

    public void reduceRestPackCount(Integer val) {
        if (restPackCount > val) {
            restPackCount -= val;
        } else {
            restPackCount = 0;
        }
    }

    //计算实际领取到的红包个数
    public void calculateRecPackCount() {
        recPackCount = recAblePackCount - restPackCount;
    }

    public String printInfo() {
        return StringUtil.buildMsg("【游戏积分：{}，已领取积分：{}，已领取红包个数：{}】", originScore, integral, recAblePackCount - restPackCount);
    }

    public Integer getOriginScore() {
        return originScore;
    }

    public void setOriginScore(Integer originScore) {
        this.originScore = originScore;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public int getRestPackCount() {
        return restPackCount;
    }

    public void setRestPackCount(int restPackCount) {
        this.restPackCount = restPackCount;
    }

    public CouponRainSetup getCouponRainSetup() {
        return couponRainSetup;
    }

    public void setCouponRainSetup(CouponRainSetup couponRainSetup) {
        this.couponRainSetup = couponRainSetup;
    }

    public CouponRain getCouponRain() {
        return couponRain;
    }

    public void setCouponRain(CouponRain couponRain) {
        this.couponRain = couponRain;
    }

    public CouponRainRecord getCouponRainRecord() {
        return couponRainRecord;
    }

    public void setCouponRainRecord(CouponRainRecord couponRainRecord) {
        this.couponRainRecord = couponRainRecord;
    }

    public int getRecAblePackCount() {
        return recAblePackCount;
    }

    public void setRecAblePackCount(int recAblePackCount) {
        this.recAblePackCount = recAblePackCount;
    }

    public int getIntegralPackCount() {
        return integralPackCount;
    }

    public void setIntegralPackCount(int integralPackCount) {
        this.integralPackCount = integralPackCount;
    }

    public int getCouponPackCount() {
        return couponPackCount;
    }

    public void setCouponPackCount(int couponPackCount) {
        this.couponPackCount = couponPackCount;
    }

    public int getIntegral() {
        return integral;
    }

    public void setIntegral(int integral) {
        this.integral = integral;
    }

    public List<Coupon> getProductCouponList() {
        return productCouponList;
    }

    public List<Coupon> getPlatformCouponList() {
        return platformCouponList;
    }

    public List<Coupon> getAreaCouponList() {
        return areaCouponList;
    }

    public int getRecPackCount() {
        return recPackCount;
    }

    public void setRecPackCount(int recPackCount) {
        this.recPackCount = recPackCount;
    }

    public List<Integer> getMemberCouponIdList() {
        return memberCouponIdList;
    }
}
