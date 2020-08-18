package com.jf.entity;

import java.math.BigDecimal;


public class MchtSettleOrderCustom extends MchtSettleOrder{
	private String confirmStatusDesc;
	private String mchtCode;
	private String shortName;
	private String platformInvoiceStatusDesc;
	private String platformCollectStatusDesc;
	private String payStatusDesc;
	private int quantity;
	private BigDecimal amount;
	private BigDecimal mchtPreferential;
	private BigDecimal freight;
	private BigDecimal platformPreferential;
	private BigDecimal integralPreferential;
	private BigDecimal payAmount;
	private BigDecimal productSettleAmount;
	private String companyName;
	private String shopName;
	private String openProductType;
	private String openProductBrand;
	private BigDecimal needSettleAmount;
	private String mchtCollectTypeName;
	private String grossRate;
	private BigDecimal deposit;
	private BigDecimal exceedAmount;//超期金额
	private String isManageSelf;

	public String getIsManageSelf() {
		return isManageSelf;
	}

	public void setIsManageSelf(String isManageSelf) {
		this.isManageSelf = isManageSelf;
	}

	public BigDecimal getExceedAmount() {
		return exceedAmount;
	}
	public void setExceedAmount(BigDecimal exceedAmount) {
		this.exceedAmount = exceedAmount;
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
	public String getPlatformInvoiceStatusDesc() {
		return platformInvoiceStatusDesc;
	}
	public void setPlatformInvoiceStatusDesc(String platformInvoiceStatusDesc) {
		this.platformInvoiceStatusDesc = platformInvoiceStatusDesc;
	}
	public String getPayStatusDesc() {
		return payStatusDesc;
	}
	public void setPayStatusDesc(String payStatusDesc) {
		this.payStatusDesc = payStatusDesc;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
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
	public BigDecimal getPayAmount() {
		return payAmount;
	}
	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}
	public BigDecimal getProductSettleAmount() {
		return productSettleAmount;
	}
	public void setProductSettleAmount(BigDecimal productSettleAmount) {
		this.productSettleAmount = productSettleAmount;
	}
	public String getPlatformCollectStatusDesc() {
		return platformCollectStatusDesc;
	}
	public void setPlatformCollectStatusDesc(String platformCollectStatusDesc) {
		this.platformCollectStatusDesc = platformCollectStatusDesc;
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
	public String getOpenProductType() {
		return openProductType;
	}
	public void setOpenProductType(String openProductType) {
		this.openProductType = openProductType;
	}
	public String getOpenProductBrand() {
		return openProductBrand;
	}
	public void setOpenProductBrand(String openProductBrand) {
		this.openProductBrand = openProductBrand;
	}
	public BigDecimal getNeedSettleAmount() {
		return needSettleAmount;
	}
	public void setNeedSettleAmount(BigDecimal needSettleAmount) {
		this.needSettleAmount = needSettleAmount;
	}
	public String getMchtCollectTypeName() {
		return mchtCollectTypeName;
	}
	public void setMchtCollectTypeName(String mchtCollectTypeName) {
		this.mchtCollectTypeName = mchtCollectTypeName;
	}
	public BigDecimal getFreight() {
		return freight;
	}
	public void setFreight(BigDecimal freight) {
		this.freight = freight;
	}
	public String getGrossRate() {
		return grossRate;
	}
	public void setGrossRate(String grossRate) {
		this.grossRate = grossRate;
	}
	public BigDecimal getDeposit() {
		return deposit;
	}
	public void setDeposit(BigDecimal deposit) {
		this.deposit = deposit;
	}
	
}
