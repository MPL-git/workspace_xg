package com.jf.entity;

public class AppealOrderCustom extends AppealOrder{
	private String subOrderCode;
	private String appealTypeDesc;
	private String statusDesc;
	private String liabilityDesc;
	private String userTypeDesc;
	
	public String getSubOrderCode() {
		return subOrderCode;
	}
	public void setSubOrderCode(String subOrderCode) {
		this.subOrderCode = subOrderCode;
	}
	public String getAppealTypeDesc() {
		return appealTypeDesc;
	}
	public void setAppealTypeDesc(String appealTypeDesc) {
		this.appealTypeDesc = appealTypeDesc;
	}
	public String getStatusDesc() {
		return statusDesc;
	}
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	public String getLiabilityDesc() {
		return liabilityDesc;
	}
	public void setLiabilityDesc(String liabilityDesc) {
		this.liabilityDesc = liabilityDesc;
	}
	public String getUserTypeDesc() {
		return userTypeDesc;
	}
	public void setUserTypeDesc(String userTypeDesc) {
		this.userTypeDesc = userTypeDesc;
	}
	
}
