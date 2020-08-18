package com.jf.entity;


public class MchtLicenseChgCustom extends MchtLicenseChg {
	private String mchtCode;
	private String companyName;
	private String shopName;
	private String productTypeName;
	private String fwName;
	private String archiveStatusDesc;
	
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getArchiveStatusDesc() {
		return archiveStatusDesc;
	}
	public void setArchiveStatusDesc(String archiveStatusDesc) {
		this.archiveStatusDesc = archiveStatusDesc;
	}
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
	public String getProductTypeName() {
		return productTypeName;
	}
	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}
	public String getFwName() {
		return fwName;
	}
	public void setFwName(String fwName) {
		this.fwName = fwName;
	}
	
}