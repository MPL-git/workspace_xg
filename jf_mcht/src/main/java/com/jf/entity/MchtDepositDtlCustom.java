package com.jf.entity;


public class MchtDepositDtlCustom extends MchtDepositDtl{
	private String changeDetail;
	
	private String bizTypeDesc;
	
	private String violateTypeDesc;
	
	private String violateActionDesc;
	
	private String content;
	
	public String getChangeDetail() {
		return changeDetail;
	}

	public void setChangeDetail(String changeDetail) {
		this.changeDetail = changeDetail;
	}

	public String getBizTypeDesc() {
		return bizTypeDesc;
	}

	public void setBizTypeDesc(String bizTypeDesc) {
		this.bizTypeDesc = bizTypeDesc;
	}

	public String getViolateTypeDesc() {
		return violateTypeDesc;
	}

	public void setViolateTypeDesc(String violateTypeDesc) {
		this.violateTypeDesc = violateTypeDesc;
	}

	public String getViolateActionDesc() {
		return violateActionDesc;
	}

	public void setViolateActionDesc(String violateActionDesc) {
		this.violateActionDesc = violateActionDesc;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}