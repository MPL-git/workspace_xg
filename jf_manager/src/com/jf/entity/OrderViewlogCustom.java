package com.jf.entity;


public class OrderViewlogCustom extends OrderViewlog{
		
	private String staffName;
	private String combineOrdercode;
	private String subOrdercode;
	private String mchtCode;
	private String shopName;
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public String getCombineOrdercode() {
		return combineOrdercode;
	}
	public void setCombineOrdercode(String combineOrdercode) {
		this.combineOrdercode = combineOrdercode;
	}
	public String getSubOrdercode() {
		return subOrdercode;
	}
	public void setSubOrdercode(String subOrdercode) {
		this.subOrdercode = subOrdercode;
	}
	public String getMchtCode() {
		return mchtCode;
	}
	public void setMchtCode(String mchtCode) {
		this.mchtCode = mchtCode;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	
	
}