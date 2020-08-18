package com.jf.entity;

public class SupplementarySignInSettingCustom extends SupplementarySignInSetting{
	private String statusDesc;
	
	private String staffName;
	
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
}