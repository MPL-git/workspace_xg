package com.jf.entity;



public class MchtPlatformContactCustom extends MchtPlatformContact{  
    private String statusDesc;
    
    private String contactStatusDesc;

    private Integer staffId;

    private String contactName;
    
    private String contactTypeDesc;
    
    private String contactType;

    private String mobile;

    private String tel;

    private String qq;

    private String email;
    
    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc == null ? null : statusDesc.trim();
    }
    
    public String getContactStatusDesc() {
        return contactStatusDesc;
    }

    public void setContactStatusDesc(String contactStatusDesc) {
        this.contactStatusDesc = contactStatusDesc == null ? null :contactStatusDesc.trim();
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName == null ? null : contactName.trim();
    }
    
    public String getContactTypeDesc() {
        return contactTypeDesc;
    }

    public void setContactTypeDesc(String contactTypeDesc) {
        this.contactTypeDesc = contactTypeDesc == null ? null : contactTypeDesc.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq == null ? null : qq.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

	public String getContactType() {
		return contactType;
	}

	public void setContactType(String contactType) {
		this.contactType = contactType;
	}
}