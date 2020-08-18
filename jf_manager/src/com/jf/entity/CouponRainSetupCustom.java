package com.jf.entity;


public class CouponRainSetupCustom extends CouponRainSetup{
    private Integer effectiveCouponCount;
    
    private Integer platformSum;
    
    private Integer commoditySum;

	public Integer getEffectiveCouponCount() {
		return effectiveCouponCount;
	}

	public void setEffectiveCouponCount(Integer effectiveCouponCount) {
		this.effectiveCouponCount = effectiveCouponCount;
	}

	public Integer getPlatformSum() {
		return platformSum;
	}

	public void setPlatformSum(Integer platformSum) {
		this.platformSum = platformSum;
	}

	public Integer getCommoditySum() {
		return commoditySum;
	}

	public void setCommoditySum(Integer commoditySum) {
		this.commoditySum = commoditySum;
	}
	
	
}