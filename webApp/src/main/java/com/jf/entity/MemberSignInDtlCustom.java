package com.jf.entity;

import java.util.Date;

public class MemberSignInDtlCustom extends MemberSignInDtl{
	
	private Date beginDate;
	private Date endDate;
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
	
	
}
