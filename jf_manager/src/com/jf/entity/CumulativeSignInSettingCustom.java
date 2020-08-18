package com.jf.entity;

public class CumulativeSignInSettingCustom extends CumulativeSignInSetting{
	private String statusDesc;
	
	private String staffName;
	
	private String productName;
	
	private String couponNames;
	
	public String getStatusDesc() {
		return statusDesc;
	}
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public String getCouponNames() {
		return couponNames;
	}
	public void setCouponNames(String couponNames) {
		this.couponNames = couponNames;
	}
	
}