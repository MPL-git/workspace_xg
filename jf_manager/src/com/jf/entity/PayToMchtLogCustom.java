package com.jf.entity;

import java.util.Date;

public class PayToMchtLogCustom extends PayToMchtLog{
	private String typeDesc;
	private String shopName;
	private String companyName;
	private String payStaffName;
	private String confirmStatusDesc;
	private String mchtCode;
	private String shortName;
	private Date beginDate;
	private Date endDate;
	private String productName;
	public String getTypeDesc() {
		return typeDesc;
	}
	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getPayStaffName() {
		return payStaffName;
	}
	public void setPayStaffName(String payStaffName) {
		this.payStaffName = payStaffName;
	}
	public String getConfirmStatusDesc() {
		return confirmStatusDesc;
	}
	public void setConfirmStatusDesc(String confirmStatusDesc) {
		this.confirmStatusDesc = confirmStatusDesc;
	}
	public String getMchtCode() {
		return mchtCode;
	}
	public void setMchtCode(String mchtCode) {
		this.mchtCode = mchtCode;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
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

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
}
