package com.jf.entity.dto;

/**
 * @author luoyb
 * Created on 2019/12/7
 */
public class MemberCouponCountDTO {

    private Integer platformCouponCount; //平台券
    private Integer areaCouponCount; //专场券
    private Integer shopCouponCount; //商家券

    public Integer getPlatformCouponCount() {
        return platformCouponCount;
    }

    public void setPlatformCouponCount(Integer platformCouponCount) {
        this.platformCouponCount = platformCouponCount;
    }

    public Integer getAreaCouponCount() {
        return areaCouponCount;
    }

    public void setAreaCouponCount(Integer areaCouponCount) {
        this.areaCouponCount = areaCouponCount;
    }

    public Integer getShopCouponCount() {
        return shopCouponCount;
    }

    public void setShopCouponCount(Integer shopCouponCount) {
        this.shopCouponCount = shopCouponCount;
    }
}
