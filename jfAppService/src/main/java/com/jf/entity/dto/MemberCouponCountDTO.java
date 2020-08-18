package com.jf.entity.dto;

/**
 * @author luoyb
 * Created on 2019/12/7
 */
public class MemberCouponCountDTO {

    private Integer platformCouponCount; //包括平台优惠券和SVIP优惠券，不包括官方会场券

    private Integer notPlatformCouponCount;

    public Integer getPlatformCouponCount() {
        return platformCouponCount;
    }

    public void setPlatformCouponCount(Integer platformCouponCount) {
        this.platformCouponCount = platformCouponCount;
    }

    public Integer getNotPlatformCouponCount() {
        return notPlatformCouponCount;
    }

    public void setNotPlatformCouponCount(Integer notPlatformCouponCount) {
        this.notPlatformCouponCount = notPlatformCouponCount;
    }
}
