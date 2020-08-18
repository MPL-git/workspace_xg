package com.jf.entity;

import java.math.BigDecimal;
import java.util.Date;

public class MchtPvStatisticalCustom extends MchtPvStatistical{
	private String mchtCode;
	private String companyName;
	private String shopName;
	private String productName;
	private String browseQuantity;
	private String paymentOfBuyers;
	private String browseQuantityTourist;
	private String mchtProductBrandName;
	private String mchtType;

	public String getMchtCode() {
		return mchtCode;
	}

	public void setMchtCode(String mchtCode) {
		this.mchtCode = mchtCode;
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

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getBrowseQuantity() {
		return browseQuantity;
	}

	public void setBrowseQuantity(String browseQuantity) {
		this.browseQuantity = browseQuantity;
	}

	public String getPaymentOfBuyers() {
		return paymentOfBuyers;
	}

	public void setPaymentOfBuyers(String paymentOfBuyers) {
		this.paymentOfBuyers = paymentOfBuyers;
	}

	public String getBrowseQuantityTourist() {
		return browseQuantityTourist;
	}

	public void setBrowseQuantityTourist(String browseQuantityTourist) {
		this.browseQuantityTourist = browseQuantityTourist;
	}

	public String getMchtProductBrandName() {
		return mchtProductBrandName;
	}

	public void setMchtProductBrandName(String mchtProductBrandName) {
		this.mchtProductBrandName = mchtProductBrandName;
	}

	public String getMchtType() {
		return mchtType;
	}

	public void setMchtType(String mchtType) {
		this.mchtType = mchtType;
	}
}