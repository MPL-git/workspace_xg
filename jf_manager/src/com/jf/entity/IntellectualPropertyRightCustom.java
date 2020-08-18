package com.jf.entity;

public class IntellectualPropertyRightCustom extends IntellectualPropertyRight {

	private String identityType;//用户身份类型 1 个人 2 企业或者其他组织
	
	private String statusDesc;//审核状态
	
	private String propertyRightTypeDesc;// 产权类型

	public String getIdentityType() {
		return identityType;
	}

	public void setIdentityType(String identityType) {
		this.identityType = identityType;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public String getPropertyRightTypeDesc() {
		return propertyRightTypeDesc;
	}

	public void setPropertyRightTypeDesc(String propertyRightTypeDesc) {
		this.propertyRightTypeDesc = propertyRightTypeDesc;
	}
	
	
	
}