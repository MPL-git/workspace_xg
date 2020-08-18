package com.jf.entity;

public class MemberFeedbackCustom extends MemberFeedback{
	private String typeDesc;
	private String phoneSystemName;
	private String mobile;
	private String nick;
	private Integer picQuantity;
    private String staffName;
    private String assistantContact;
	public String getTypeDesc() {
		return typeDesc;
	}
	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}
	public String getPhoneSystemName() {
		return phoneSystemName;
	}
	public void setPhoneSystemName(String phoneSystemName) {
		this.phoneSystemName = phoneSystemName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
    public Integer getPicQuantity() {
        return picQuantity;
    }
    public void setPicQuantity(Integer picQuantity) {
        this.picQuantity = picQuantity;
    }
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public String getAssistantContact() {
		return assistantContact;
	}
	public void setAssistantContact(String assistantContact) {
		this.assistantContact = assistantContact;
	}
	
}
