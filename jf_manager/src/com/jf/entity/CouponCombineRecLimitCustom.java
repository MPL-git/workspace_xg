package com.jf.entity;


public class CouponCombineRecLimitCustom extends CouponCombineRecLimit{
	private String couponIdgroup;
	private String staffName;
	private String reclimittypeDesc;
	private Integer recEach;
	public String getCouponIdgroup() {
		return couponIdgroup;
	}
	public void setCouponIdgroup(String couponIdgroup) {
		this.couponIdgroup = couponIdgroup;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public String getReclimittypeDesc() {
		return reclimittypeDesc;
	}
	public void setReclimittypeDesc(String reclimittypeDesc) {
		this.reclimittypeDesc = reclimittypeDesc;
	}
	public Integer getRecEach() {
		return recEach;
	}
	public void setRecEach(Integer recEach) {
		this.recEach = recEach;
	}
	
}