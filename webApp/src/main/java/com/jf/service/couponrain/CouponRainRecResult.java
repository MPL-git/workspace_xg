package com.jf.service.couponrain;

import com.jf.entity.Coupon;
import com.jf.entity.MemberCoupon;

/**
 * @author luoyb
 * Created on 2019/12/12
 */
public class CouponRainRecResult {

    private boolean success;
    private boolean currentCouponExhausted; //当前券已领完
    private Coupon coupon;
    private MemberCoupon memberCoupon;

    private CouponRainRecResult() {
    }

    public static CouponRainRecResult fail() {
        return new CouponRainRecResult();
    }

    public static CouponRainRecResult fail(boolean currentCouponExhausted) {
        CouponRainRecResult result = new CouponRainRecResult();
        result.setCurrentCouponExhausted(currentCouponExhausted);
        return result;
    }


    public static CouponRainRecResult success(Coupon coupon, MemberCoupon memberCoupon) {
        CouponRainRecResult result = new CouponRainRecResult();
        result.setSuccess(true);
        result.setCoupon(coupon);
        result.setMemberCoupon(memberCoupon);
        return result;
    }

    public boolean isCurrentCouponExhausted() {
        return currentCouponExhausted;
    }

    public void setCurrentCouponExhausted(boolean currentCouponExhausted) {
        this.currentCouponExhausted = currentCouponExhausted;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Coupon getCoupon() {
        return coupon;
    }

    public void setCoupon(Coupon coupon) {
        this.coupon = coupon;
    }

    public MemberCoupon getMemberCoupon() {
        return memberCoupon;
    }

    public void setMemberCoupon(MemberCoupon memberCoupon) {
        this.memberCoupon = memberCoupon;
    }
}
