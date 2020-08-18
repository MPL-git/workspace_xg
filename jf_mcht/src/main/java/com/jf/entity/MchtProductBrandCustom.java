package com.jf.entity;

import java.util.List;


public class MchtProductBrandCustom extends MchtProductBrand{
	private String brandName;
	private String aptitudeTypeDesc;
	private String auditStatusDesc;
	private String statusDesc;
	private String shortName;
	private Integer aptitudePicCount;
	private Integer platformAuthPicCount;
	private String productBrandLogo;
	private String priceModelStatusDesc;
	private List<MchtBrandAptitudeCustom> mchtBrandAptitudeCustoms;
	private List<MchtPlatformAuthPic> mchtPlatformAuthPics;
	private List<MchtBrandInvoicePic> mchtBrandInvoicePics;
	private List<MchtBrandInspectionPic> mchtBrandInspectionPics;
	private List<MchtBrandOtherPic> mchtBrandOtherPics;
	private String platformAuthExpDates;
	private String otherExpDates;

	public String getPlatformAuthExpDates() {
		return platformAuthExpDates;
	}
	public void setPlatformAuthExpDates(String platformAuthExpDates) {
		this.platformAuthExpDates = platformAuthExpDates;
	}
	public String getOtherExpDates() {
		return otherExpDates;
	}
	public void setOtherExpDates(String otherExpDates) {
		this.otherExpDates = otherExpDates;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String getAptitudeTypeDesc() {
		return aptitudeTypeDesc;
	}
	public void setAptitudeTypeDesc(String aptitudeTypeDesc) {
		this.aptitudeTypeDesc = aptitudeTypeDesc;
	}
	public String getAuditStatusDesc() {
		return auditStatusDesc;
	}
	public void setAuditStatusDesc(String auditStatusDesc) {
		this.auditStatusDesc = auditStatusDesc;
	}
	public String getStatusDesc() {
		return statusDesc;
	}
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	public Integer getAptitudePicCount() {
		return aptitudePicCount;
	}
	public void setAptitudePicCount(Integer aptitudePicCount) {
		this.aptitudePicCount = aptitudePicCount;
	}
	public Integer getPlatformAuthPicCount() {
		return platformAuthPicCount;
	}
	public void setPlatformAuthPicCount(Integer platformAuthPicCount) {
		this.platformAuthPicCount = platformAuthPicCount;
	}
	
	public String getProductBrandLogo() {
		return productBrandLogo;
	}
	public void setProductBrandLogo(String productBrandLogo) {
		this.productBrandLogo = productBrandLogo;
	}
	public String getPriceModelStatusDesc() {
		return priceModelStatusDesc;
	}
	public void setPriceModelStatusDesc(String priceModelStatusDesc) {
		this.priceModelStatusDesc = priceModelStatusDesc;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public List<MchtBrandAptitudeCustom> getMchtBrandAptitudeCustoms() {
		return mchtBrandAptitudeCustoms;
	}
	public void setMchtBrandAptitudeCustoms(
			List<MchtBrandAptitudeCustom> mchtBrandAptitudeCustoms) {
		this.mchtBrandAptitudeCustoms = mchtBrandAptitudeCustoms;
	}
	public List<MchtPlatformAuthPic> getMchtPlatformAuthPics() {
		return mchtPlatformAuthPics;
	}
	public void setMchtPlatformAuthPics(
			List<MchtPlatformAuthPic> mchtPlatformAuthPics) {
		this.mchtPlatformAuthPics = mchtPlatformAuthPics;
	}
	public List<MchtBrandInvoicePic> getMchtBrandInvoicePics() {
		return mchtBrandInvoicePics;
	}
	public void setMchtBrandInvoicePics(
			List<MchtBrandInvoicePic> mchtBrandInvoicePics) {
		this.mchtBrandInvoicePics = mchtBrandInvoicePics;
	}
	public List<MchtBrandInspectionPic> getMchtBrandInspectionPics() {
		return mchtBrandInspectionPics;
	}
	public void setMchtBrandInspectionPics(
			List<MchtBrandInspectionPic> mchtBrandInspectionPics) {
		this.mchtBrandInspectionPics = mchtBrandInspectionPics;
	}
	public List<MchtBrandOtherPic> getMchtBrandOtherPics() {
		return mchtBrandOtherPics;
	}
	public void setMchtBrandOtherPics(List<MchtBrandOtherPic> mchtBrandOtherPics) {
		this.mchtBrandOtherPics = mchtBrandOtherPics;
	}
	
}