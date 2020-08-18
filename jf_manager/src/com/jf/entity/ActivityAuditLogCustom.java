package com.jf.entity;

public class ActivityAuditLogCustom extends ActivityAuditLog {
	
	private String statusDesc;//审核状态
	private String typeDesc;//审核人类型
	
	public String getStatusDesc() {
		return statusDesc;
	}
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	public String getTypeDesc() {
		return typeDesc;
	}
	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}

	
}
