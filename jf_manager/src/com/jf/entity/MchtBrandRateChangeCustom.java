package com.jf.entity;

import java.math.BigDecimal;

public class MchtBrandRateChangeCustom extends MchtBrandRateChange{
	private String brandName;
	private String productBrandName;
	private BigDecimal commissionRate;

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getProductBrandName() {
		return productBrandName;
	}

	public void setProductBrandName(String productBrandName) {
		this.productBrandName = productBrandName;
	}

	public BigDecimal getCommissionRate() {
		return commissionRate;
	}

	public void setCommissionRate(BigDecimal commissionRate) {
		this.commissionRate = commissionRate;
	}
	
}