package com.jf.entity;

public class PlatformfpContact {
	private PlatformContact platformContact;
	
	private String fpData;
	
	private String fpStatus;
	
	private String mchtPlatformContactStatus;
	//平台对接人类型
	private String contactType;
	//平台对接人状态
	private String status;
	
	public PlatformContact getPlatformContact() {
		return platformContact;
	}

	public void setPlatformContact(PlatformContact platformContact) {
		this.platformContact = platformContact;
	}

	public String getFpData() {
		return fpData;
	}

	public void setFpData(String fpData) {
		this.fpData = fpData;
	}

	public String getFpStatus() {
		return fpStatus;
	}

	public void setFpStatus(String fpStatus) {
		this.fpStatus = fpStatus;
	}

	public String getMchtPlatformContactStatus() {
		return mchtPlatformContactStatus;
	}

	public void setMchtPlatformContactStatus(String mchtPlatformContactStatus) {
		this.mchtPlatformContactStatus = mchtPlatformContactStatus;
	}

	public String getContactType() {
		return contactType;
	}

	public void setContactType(String contactType) {
		this.contactType = contactType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
