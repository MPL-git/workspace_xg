package com.jf.entity;

public class PlatformContactCustom extends PlatformContact {
	private String statusDesc;
	private String contactTypeDesc;
	private String staffName;
	private String assistantcontactname;
	private Integer assistantStaffId;
	private String assistantStaffName;

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public String getContactTypeDesc() {
		return contactTypeDesc;
	}

	public void setContactTypeDesc(String contactTypeDesc) {
		this.contactTypeDesc = contactTypeDesc;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getAssistantcontactname() {
		return assistantcontactname;
	}

	public void setAssistantcontactname(String assistantcontactname) {
		this.assistantcontactname = assistantcontactname;
	}

	public Integer getAssistantStaffId() {
		return assistantStaffId;
	}

	public void setAssistantStaffId(Integer assistantStaffId) {
		this.assistantStaffId = assistantStaffId;
	}

	public String getAssistantStaffName() {
		return assistantStaffName;
	}

	public void setAssistantStaffName(String assistantStaffName) {
		this.assistantStaffName = assistantStaffName;
	}

}
