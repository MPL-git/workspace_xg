package com.jf.entity;

import java.util.Date;

public class IntellectualPropertyRightAppealCustomExample extends IntellectualPropertyRightAppealExample{

	// 商家声明状态
	private String complainStatus;
	// 当前时间
    private Date presentTime;

	public String getComplainStatus() {
		return complainStatus;
	}

	public void setComplainStatus(String complainStatus) {
		this.complainStatus = complainStatus;
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
	
	public class IntellectualPropertyRightAppealCustomCriteria extends Criteria {
	}
}