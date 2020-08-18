package com.jf.entity;

import java.math.BigDecimal;

public class OrderProductSnapshotCustom extends OrderProductSnapshot {

	private BigDecimal salePrice;
	private BigDecimal tagPrice;
	private String productName;
	private String productPropDesc;
	private String brandName;
	private String artNo;
	private String suitGroupDesc;
	private String suitSexDesc;
	private String seasonDesc;
	private String productCode;

	public BigDecimal getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}

	public BigDecimal getTagPrice() {
		return tagPrice;
	}

	public void setTagPrice(BigDecimal tagPrice) {
		this.tagPrice = tagPrice;
	}

	public String getProductPropDesc() {
		return productPropDesc;
	}

	public void setProductPropDesc(String productPropDesc) {
		this.productPropDesc = productPropDesc;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getArtNo() {
		return artNo;
	}

	public void setArtNo(String artNo) {
		this.artNo = artNo;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getSuitGroupDesc() {
		return suitGroupDesc;
	}

	public void setSuitGroupDesc(String suitGroupDesc) {
		this.suitGroupDesc = suitGroupDesc;
	}

	public String getSuitSexDesc() {
		return suitSexDesc;
	}

	public void setSuitSexDesc(String suitSexDesc) {
		this.suitSexDesc = suitSexDesc;
	}

	public String getSeasonDesc() {
		return seasonDesc;
	}

	public void setSeasonDesc(String seasonDesc) {
		this.seasonDesc = seasonDesc;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	
}
