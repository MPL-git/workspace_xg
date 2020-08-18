package com.jf.entity;

public class MemberCumulativeSignInCustom extends MemberCumulativeSignIn{
	private int month;
	
	private int cumulativeSignInCount;
	
	private int integral;
	
	private String couponNames;
	
	private String productName;

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getCumulativeSignInCount() {
		return cumulativeSignInCount;
	}

	public void setCumulativeSignInCount(int cumulativeSignInCount) {
		this.cumulativeSignInCount = cumulativeSignInCount;
	}

	public int getIntegral() {
		return integral;
	}

	public void setIntegral(int integral) {
		this.integral = integral;
	}

	public String getCouponNames() {
		return couponNames;
	}

	public void setCouponNames(String couponNames) {
		this.couponNames = couponNames;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	
}