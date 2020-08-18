package com.jf.entity;

public class ImpeachMemberCustom extends ImpeachMember {
	private String subOrderCodes;//相关订单号
	private String typeDesc;//举报类型
	//private String memberIds;//被举报会员id集合
	
	
	
	public String getSubOrderCodes() {
		return subOrderCodes;
	}

	public void setSubOrderCodes(String subOrderCodes) {
		this.subOrderCodes = subOrderCodes;
	}



	public String getTypeDesc() {
		return typeDesc;
	}

	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}

	
}
