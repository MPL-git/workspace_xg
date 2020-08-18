package com.jf.entity;

import java.math.BigDecimal;



public class MchtSettleSituationCustom extends MchtSettleSituation{
	private String mchtCode;
	private String shortName;
	private String companyName;
	private String shopName;
	private BigDecimal currentNotOutAccount;
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
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public BigDecimal getCurrentNotOutAccount() {
		return currentNotOutAccount;
	}
	public void setCurrentNotOutAccount(BigDecimal currentNotOutAccount) {
		this.currentNotOutAccount = currentNotOutAccount;
	}
	
}
