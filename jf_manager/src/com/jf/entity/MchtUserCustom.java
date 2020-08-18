package com.jf.entity;


import java.util.Date;

public class MchtUserCustom extends MchtUser{
	private String mchtName;
	private String mchtCode;
	private String zsContactName;
	private String mchtinfocompanyname;
	private String mchtsettledapplycompanyname;
	private String totalAuditStatusDesc;
	private String settledType;
	private String mchtType;  //商家合作类型
	private String isManageSelf; //是否自营

	public String getMchtType() {
		return mchtType;
	}

	public void setMchtType(String mchtType) {
		this.mchtType = mchtType;
	}

	public String getIsManageSelf() {
		return isManageSelf;
	}

	public void setIsManageSelf(String isManageSelf) {
		this.isManageSelf = isManageSelf;
	}

	public String getMchtName() {
		return mchtName;
	}
	public void setMchtName(String mchtName) {
		this.mchtName = mchtName;
	}
	public String getZsContactName() {
		return zsContactName;
	}
	public void setZsContactName(String zsContactName) {
		this.zsContactName = zsContactName;
	}
	public String getMchtCode() {
		return mchtCode;
	}
	public void setMchtCode(String mchtCode) {
		this.mchtCode = mchtCode;
	}
	public String getMchtinfocompanyname() {
		return mchtinfocompanyname;
	}
	public void setMchtinfocompanyname(String mchtinfocompanyname) {
		this.mchtinfocompanyname = mchtinfocompanyname;
	}
	public String getMchtsettledapplycompanyname() {
		return mchtsettledapplycompanyname;
	}
	public void setMchtsettledapplycompanyname(String mchtsettledapplycompanyname) {
		this.mchtsettledapplycompanyname = mchtsettledapplycompanyname;
	}
	public String getTotalAuditStatusDesc() {
		return totalAuditStatusDesc;
	}
	public void setTotalAuditStatusDesc(String totalAuditStatusDesc) {
		this.totalAuditStatusDesc = totalAuditStatusDesc;
	}
	public String getSettledType() {
		return settledType;
	}
	public void setSettledType(String settledType) {
		this.settledType = settledType;
	}
	
}