package com.jf.entity;

public class MchtFeedbackCustom extends MchtFeedback{
	private String mchtinfocode;
	private String mchtshopname;
	private Integer picQuantity;
    private String staffName;
    private String assistantContact;
    
    public String getMchtinfocode() {
		return mchtinfocode;
	}
	public void setMchtinfocode(String mchtinfocode) {
		this.mchtinfocode = mchtinfocode;
	}

	public String getMchtshopname() {
		return mchtshopname;
	}
	public void setMchtshopname(String mchtshopname) {
		this.mchtshopname = mchtshopname;
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
