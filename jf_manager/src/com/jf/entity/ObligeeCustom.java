package com.jf.entity;

public class ObligeeCustom extends Obligee {

	private String obligeeLocationDesc;//权利人所在地
	
	private String identityTypeDesc;//用户身份类型 

	public String getObligeeLocationDesc() {
		return obligeeLocationDesc;
	}

	public void setObligeeLocationDesc(String obligeeLocationDesc) {
		this.obligeeLocationDesc = obligeeLocationDesc;
	}

	public String getIdentityTypeDesc() {
		return identityTypeDesc;
	}

	public void setIdentityTypeDesc(String identityTypeDesc) {
		this.identityTypeDesc = identityTypeDesc;
	}
	
	
}