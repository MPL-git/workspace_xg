package com.jf.entity;

public class SubOrderAttachmentCustom extends SubOrderAttachment{
	private String sysCreatorName;
	private String mchtCreatorName;
	private String creatorTypeDesc;
	
	public String getSysCreatorName() {
		return sysCreatorName;
	}
	public void setSysCreatorName(String sysCreatorName) {
		this.sysCreatorName = sysCreatorName;
	}
	public String getMchtCreatorName() {
		return mchtCreatorName;
	}
	public void setMchtCreatorName(String mchtCreatorName) {
		this.mchtCreatorName = mchtCreatorName;
	}
	public String getCreatorTypeDesc() {
		return creatorTypeDesc;
	}
	public void setCreatorTypeDesc(String creatorTypeDesc) {
		this.creatorTypeDesc = creatorTypeDesc;
	}
	
}
