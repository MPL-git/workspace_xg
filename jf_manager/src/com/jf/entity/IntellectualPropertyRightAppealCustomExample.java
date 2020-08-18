package com.jf.entity;

import java.util.Date;



public class IntellectualPropertyRightAppealCustomExample extends IntellectualPropertyRightAppealExample{
	
	private String mobile;//申请人手机号码
	private String propertyRightType;//产权类型
	private String complainStatus;//商家声明状态
	private String prosecutionStatus;//起诉状态
	private String transmitStatus;//平台转发状态
	private Date presentTime;//现在时间
	
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPropertyRightType() {
		return propertyRightType;
	}

	public void setPropertyRightType(String propertyRightType) {
		this.propertyRightType = propertyRightType;
	}

	public String getComplainStatus() {
		return complainStatus;
	}

	public void setComplainStatus(String complainStatus) {
		this.complainStatus = complainStatus;
	}

	public String getProsecutionStatus() {
		return prosecutionStatus;
	}

	public void setProsecutionStatus(String prosecutionStatus) {
		this.prosecutionStatus = prosecutionStatus;
	}

	public String getTransmitStatus() {
		return transmitStatus;
	}

	public void setTransmitStatus(String transmitStatus) {
		this.transmitStatus = transmitStatus;
	}

	public Date getPresentTime() {
		return presentTime;
	}

	public void setPresentTime(Date presentTime) {
		this.presentTime = presentTime;
	}

	@Override
	public IntellectualPropertyRightAppealCustomCriteria createCriteria() {
		IntellectualPropertyRightAppealCustomCriteria criteria = new IntellectualPropertyRightAppealCustomCriteria();
		if(oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}
	
	public class IntellectualPropertyRightAppealCustomCriteria extends IntellectualPropertyRightAppealExample.Criteria {
	}
}