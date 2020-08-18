package com.jf.entity;

import java.util.Date;

public class IntellectualPropertyRightAppealCustom extends IntellectualPropertyRightAppeal {

	private String identityType;//用户身份类型 1 个人 2 企业或者其他组织
	
	private String mobile;//权利人电话
	
	private String propertyRightBelong;//产权归属
	
	private String appealTypeDesc;//投诉类型
	
	private String acceptStatusDesc;//受理状态
	
	private String staffName;//领取人
	
	private String mchtCode;//商家序号
    
    private String propertyRightType;
    
    private Integer complainId;
    
    private String complainStatus;
    
    private Date complainDate;
    
    private Date prosecutionEndDate;
    
    private Integer prosecutionId;
    
    private String prosecutionStatus;
    
    private String propertyRightTypeDesc;
    
    private String statusDesc;
    
    private String complainStatusDesc;
    
    private String remarkToInner;//内部备注（起诉信息跟进）
    
    private String prosecutionInnerRemarks;//内部备注 起诉结果
    
    private String appealReasonDesc;//投诉
    
    private String prosecutionStatusDesc;//起诉状态

	public String getIdentityType() {
		return identityType;
	}

	public void setIdentityType(String identityType) {
		this.identityType = identityType;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPropertyRightBelong() {
		return propertyRightBelong;
	}

	public void setPropertyRightBelong(String propertyRightBelong) {
		this.propertyRightBelong = propertyRightBelong;
	}

	public String getAppealTypeDesc() {
		return appealTypeDesc;
	}

	public void setAppealTypeDesc(String appealTypeDesc) {
		this.appealTypeDesc = appealTypeDesc;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getAcceptStatusDesc() {
		return acceptStatusDesc;
	}

	public void setAcceptStatusDesc(String acceptStatusDesc) {
		this.acceptStatusDesc = acceptStatusDesc;
	}

	public String getMchtCode() {
		return mchtCode;
	}

	public void setMchtCode(String mchtCode) {
		this.mchtCode = mchtCode;
	}

	public String getPropertyRightType() {
		return propertyRightType;
	}

	public void setPropertyRightType(String propertyRightType) {
		this.propertyRightType = propertyRightType;
	}

	public Integer getComplainId() {
		return complainId;
	}

	public void setComplainId(Integer complainId) {
		this.complainId = complainId;
	}

	public String getComplainStatus() {
		return complainStatus;
	}

	public void setComplainStatus(String complainStatus) {
		this.complainStatus = complainStatus;
	}

	public Date getComplainDate() {
		return complainDate;
	}

	public void setComplainDate(Date complainDate) {
		this.complainDate = complainDate;
	}

	public Date getProsecutionEndDate() {
		return prosecutionEndDate;
	}

	public void setProsecutionEndDate(Date prosecutionEndDate) {
		this.prosecutionEndDate = prosecutionEndDate;
	}

	public Integer getProsecutionId() {
		return prosecutionId;
	}

	public void setProsecutionId(Integer prosecutionId) {
		this.prosecutionId = prosecutionId;
	}

	public String getProsecutionStatus() {
		return prosecutionStatus;
	}

	public void setProsecutionStatus(String prosecutionStatus) {
		this.prosecutionStatus = prosecutionStatus;
	}

	public String getPropertyRightTypeDesc() {
		return propertyRightTypeDesc;
	}

	public void setPropertyRightTypeDesc(String propertyRightTypeDesc) {
		this.propertyRightTypeDesc = propertyRightTypeDesc;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public String getComplainStatusDesc() {
		return complainStatusDesc;
	}

	public void setComplainStatusDesc(String complainStatusDesc) {
		this.complainStatusDesc = complainStatusDesc;
	}

	public String getRemarkToInner() {
		return remarkToInner;
	}

	public void setRemarkToInner(String remarkToInner) {
		this.remarkToInner = remarkToInner;
	}

	public String getProsecutionInnerRemarks() {
		return prosecutionInnerRemarks;
	}

	public void setProsecutionInnerRemarks(String prosecutionInnerRemarks) {
		this.prosecutionInnerRemarks = prosecutionInnerRemarks;
	}

	public String getAppealReasonDesc() {
		return appealReasonDesc;
	}

	public void setAppealReasonDesc(String appealReasonDesc) {
		this.appealReasonDesc = appealReasonDesc;
	}

	public String getProsecutionStatusDesc() {
		return prosecutionStatusDesc;
	}

	public void setProsecutionStatusDesc(String prosecutionStatusDesc) {
		this.prosecutionStatusDesc = prosecutionStatusDesc;
	}
	
}