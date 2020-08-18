package com.jf.entity;

import java.util.Date;


public class PropertyRightProsecutionCustom extends PropertyRightProsecution {
	
    private Integer rightAppealId;
    private Integer staffId;
    private String mobile;
    private String identityType;
    private String staffName;
    private String identityTypeDesc;
    private String statusDesc;
    private Date prosecutionEndDate;
    private String acceptStatus;
    
	public Integer getRightAppealId() {
		return rightAppealId;
	}
	public void setRightAppealId(Integer rightAppealId) {
		this.rightAppealId = rightAppealId;
	}
	public Integer getStaffId() {
		return staffId;
	}
	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getIdentityType() {
		return identityType;
	}
	public void setIdentityType(String identityType) {
		this.identityType = identityType;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public String getIdentityTypeDesc() {
		return identityTypeDesc;
	}
	public void setIdentityTypeDesc(String identityTypeDesc) {
		this.identityTypeDesc = identityTypeDesc;
	}
	public String getStatusDesc() {
		return statusDesc;
	}
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	public Date getProsecutionEndDate() {
		return prosecutionEndDate;
	}
	public void setProsecutionEndDate(Date prosecutionEndDate) {
		this.prosecutionEndDate = prosecutionEndDate;
	}
	public String getAcceptStatus() {
		return acceptStatus;
	}
	public void setAcceptStatus(String acceptStatus) {
		this.acceptStatus = acceptStatus;
	}
    
	
}