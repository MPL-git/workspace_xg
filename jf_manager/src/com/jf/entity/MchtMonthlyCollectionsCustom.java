package com.jf.entity;

import java.math.BigDecimal;



public class MchtMonthlyCollectionsCustom extends MchtMonthlyCollections {
	private String mchtCode;
	private String mchtShortName;
	private String mchtShopName;
	private String mchtCompanyName;
	private String mchtStatusDesc;
	private Integer productCount;
	private BigDecimal productAmount;
	private BigDecimal mchtPreferential;
	private BigDecimal platformPreferential;
	private BigDecimal integralPreferential;
	private BigDecimal orderClientPayAmount;
	private BigDecimal commissionAmount;
	private BigDecimal totalAmt;
	private BigDecimal payAmt;
	
	public String getMchtCode() {
		return mchtCode;
	}
	public void setMchtCode(String mchtCode) {
		this.mchtCode = mchtCode;
	}
	public String getMchtShortName() {
		return mchtShortName;
	}
	public void setMchtShortName(String mchtShortName) {
		this.mchtShortName = mchtShortName;
	}
	public String getMchtShopName() {
		return mchtShopName;
	}
	public void setMchtShopName(String mchtShopName) {
		this.mchtShopName = mchtShopName;
	}
	public String getMchtCompanyName() {
		return mchtCompanyName;
	}
	public void setMchtCompanyName(String mchtCompanyName) {
		this.mchtCompanyName = mchtCompanyName;
	}
	public Integer getProductCount() {
		return productCount;
	}
	public void setProductCount(Integer productCount) {
		this.productCount = productCount;
	}
	public BigDecimal getProductAmount() {
		return productAmount;
	}
	public void setProductAmount(BigDecimal productAmount) {
		this.productAmount = productAmount;
	}
	public BigDecimal getMchtPreferential() {
		return mchtPreferential;
	}
	public void setMchtPreferential(BigDecimal mchtPreferential) {
		this.mchtPreferential = mchtPreferential;
	}
	public BigDecimal getPlatformPreferential() {
		return platformPreferential;
	}
	public void setPlatformPreferential(BigDecimal platformPreferential) {
		this.platformPreferential = platformPreferential;
	}
	public BigDecimal getIntegralPreferential() {
		return integralPreferential;
	}
	public void setIntegralPreferential(BigDecimal integralPreferential) {
		this.integralPreferential = integralPreferential;
	}
	public BigDecimal getOrderClientPayAmount() {
		return orderClientPayAmount;
	}
	public void setOrderClientPayAmount(BigDecimal orderClientPayAmount) {
		this.orderClientPayAmount = orderClientPayAmount;
	}
	public BigDecimal getCommissionAmount() {
		return commissionAmount;
	}
	public void setCommissionAmount(BigDecimal commissionAmount) {
		this.commissionAmount = commissionAmount;
	}
	public BigDecimal getTotalAmt() {
		return totalAmt;
	}
	public void setTotalAmt(BigDecimal totalAmt) {
		this.totalAmt = totalAmt;
	}
	public BigDecimal getPayAmt() {
		return payAmt;
	}
	public void setPayAmt(BigDecimal payAmt) {
		this.payAmt = payAmt;
	}
	public String getMchtStatusDesc() {
		return mchtStatusDesc;
	}
	public void setMchtStatusDesc(String mchtStatusDesc) {
		this.mchtStatusDesc = mchtStatusDesc;
	}
	
}