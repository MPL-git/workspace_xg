package com.jf.entity;

import java.math.BigDecimal;
import java.util.Date;

public class MchtHomeInfo {
	private Integer id;

	private String mchtCode;

	private String isCompanyInfPerfect;

	private String companyName;

	private String shopName;

	private BigDecimal totalAmount;

	private Integer brandImperfectCount;

	private Integer taxImperfectCount;

	private Integer overtimeCount;

	private Integer serviceCount;

	private Integer todayCount;

	private BigDecimal todayAmount;

	private Integer unaddCount;

	private Integer unsubmitCount;

	private Integer auditCount;

	private Integer activeCount;

	private Integer violateOrderCount;

	private Date yearlyInspectionDate;
	    
	private Date corporationIdcardDate;
	
	public Date getCorporationIdcardDate() {
		return corporationIdcardDate;
	}
	private String yearlyInspectionDates;
	
	private String corporationIdcardDates;
	
	
	public String getYearlyInspectionDates() {
		return yearlyInspectionDates;
	}

	public void setYearlyInspectionDates(String yearlyInspectionDates) {
		this.yearlyInspectionDates = yearlyInspectionDates;
	}

	public String getCorporationIdcardDates() {
		return corporationIdcardDates;
	}

	public void setCorporationIdcardDates(String corporationIdcardDates) {
		this.corporationIdcardDates = corporationIdcardDates;
	}

	public void setCorporationIdcardDate(Date corporationIdcardDate) {
		this.corporationIdcardDate = corporationIdcardDate;
	}

	public Date getYearlyInspectionDate() {
		return yearlyInspectionDate;
	}

	public void setYearlyInspectionDate(Date yearlyInspectionDate) {
		this.yearlyInspectionDate = yearlyInspectionDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMchtCode() {
		return mchtCode;
	}

	public void setMchtCode(String mchtCode) {
		this.mchtCode = mchtCode == null ? null : mchtCode.trim();
	}

	public String getIsCompanyInfPerfect() {
		return isCompanyInfPerfect;
	}

	public void setIsCompanyInfPerfect(String isCompanyInfPerfect) {
		this.isCompanyInfPerfect = isCompanyInfPerfect == null ? null
				: isCompanyInfPerfect.trim();
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName == null ? null : companyName.trim();
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName == null ? null : shopName.trim();
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Integer getBrandImperfectCount() {
		return brandImperfectCount;
	}

	public void setBrandImperfectCount(Integer brandImperfectCount) {
		this.brandImperfectCount = brandImperfectCount;
	}

	public Integer getTaxImperfectCount() {
		return taxImperfectCount;
	}

	public void setTaxImperfectCount(Integer taxImperfectCount) {
		this.taxImperfectCount = taxImperfectCount;
	}

	public Integer getOvertimeCount() {
		return overtimeCount;
	}

	public void setOvertimeCount(Integer overtimeCount) {
		this.overtimeCount = overtimeCount;
	}

	public Integer getServiceCount() {
		return serviceCount;
	}

	public void setServiceCount(Integer serviceCount) {
		this.serviceCount = serviceCount;
	}

	public Integer getTodayCount() {
		return todayCount;
	}

	public void setTodayCount(Integer todayCount) {
		this.todayCount = todayCount;
	}

	public BigDecimal getTodayAmount() {
		return todayAmount;
	}

	public void setTodayAmount(BigDecimal todayAmount) {
		this.todayAmount = todayAmount;
	}

	public Integer getUnaddCount() {
		return unaddCount;
	}

	public void setUnaddCount(Integer unaddCount) {
		this.unaddCount = unaddCount;
	}

	public Integer getUnsubmitCount() {
		return unsubmitCount;
	}

	public void setUnsubmitCount(Integer unsubmitCount) {
		this.unsubmitCount = unsubmitCount;
	}

	public Integer getAuditCount() {
		return auditCount;
	}

	public void setAuditCount(Integer auditCount) {
		this.auditCount = auditCount;
	}

	public Integer getActiveCount() {
		return activeCount;
	}

	public void setActiveCount(Integer activeCount) {
		this.activeCount = activeCount;
	}

	public Integer getViolateOrderCount() {
		return violateOrderCount;
	}

	public void setViolateOrderCount(Integer violateOrderCount) {
		this.violateOrderCount = violateOrderCount;
	}

}