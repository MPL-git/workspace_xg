package com.jf.vo;

import java.math.BigDecimal;

public class DebitRecord {
	private Integer activityId;
	private String name;
	private String companyName;
	private String mchtCode;
	private BigDecimal totalDeductions;
	public Integer getActivityId() {
		return activityId;
	}
	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getMchtCode() {
		return mchtCode;
	}
	public void setMchtCode(String mchtCode) {
		this.mchtCode = mchtCode;
	}
	public BigDecimal getTotalDeductions() {
		return totalDeductions;
	}
	public void setTotalDeductions(BigDecimal totalDeductions) {
		this.totalDeductions = totalDeductions;
	}
	
	
}
