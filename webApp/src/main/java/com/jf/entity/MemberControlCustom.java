package com.jf.entity;

import java.util.Date;

public class MemberControlCustom extends MemberControl{
	private Date beginDate;
	private Date endDate;
	private Integer inviteLimit;
	private String updateType; 
	public Date getBeginDate() {
		return beginDate;
	}
	
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	
	public Date getEndDate() {
		return endDate;
	}
	
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getInviteLimit() {
		return inviteLimit;
	}
	

	public void setInviteLimit(Integer inviteLimit) {
		this.inviteLimit = inviteLimit;
	}

	public String getUpdateType() {
		return updateType;
	}
	

	public void setUpdateType(String updateType) {
		this.updateType = updateType;
	}
	
	
}
