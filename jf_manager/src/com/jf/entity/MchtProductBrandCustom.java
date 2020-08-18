package com.jf.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;


public class MchtProductBrandCustom extends MchtProductBrand {
	private String brandName;
	private String aptitudeTypeDesc;
	private String auditStatusDesc;
	private String statusDesc;
	private String shortName;
	private Integer aptitudePicCount;
	private Integer platformAuthPicCount;
	private String brandLogo;
	private String priceModelStatusDesc;
	private String zsContactName;
	private String fwContactName;
	private String yyContactName;
	private String companyName;
	private String shopName;
	private String isSpeciallyApprovedDesc;
	private String mchtCode;
	private Integer fwStaffId;
	private Integer singleProductActivityCount;
	private Integer activityCount;
	private Integer productCount;
	private BigDecimal feeRate;
	private Integer zsStaffId;
    private String assistantContact;
    private Integer yyStaffId;
    private String mchtStatus;//商家合作状态
	private List<MchtBrandAptitudeCustom> mchtBrandAptitudeCustoms;
	
	private List<Map<String, Object>> mchtPlatformAuthPics;
	
	private List<MchtPlatformAuthPic> mchtPlatformAuthPicList;
	
	private List<Map<String, Object>> mchtBrandInvoicePics;
	
	private List<MchtBrandInvoicePic> mchtBrandInvoicePicList;
	
	private List<Map<String, Object>> mchtBrandInspectionPics;
	
	private List<MchtBrandInspectionPic> mchtBrandInspectionPicList;
	
	private List<Map<String, Object>> mchtBrandOtherPics;
	
	private List<MchtBrandOtherPic> mchtBrandOtherPicList;
	
	private List<MchtBrandProductTypeCustom> mchtBrandProductTypeCustoms;
	
	private Date cooperateBeginDate;
	
	private String productTypeName;

	public String getMchtStatus() {
		return mchtStatus;
	}

	public void setMchtStatus(String mchtStatus) {
		this.mchtStatus = mchtStatus;
	}

	public String getYyContactName() {
		return yyContactName;
	}

	public void setYyContactName(String yyContactName) {
		this.yyContactName = yyContactName;
	}

	private BigDecimal brandGMV;
	
	public BigDecimal getBrandGMV() {
		return brandGMV;
	}

	public void setBrandGMV(BigDecimal brandGMV) {
		this.brandGMV = brandGMV;
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

	public String getBrandLogo() {
		return brandLogo;
	}

	public void setBrandLogo(String brandLogo) {
		this.brandLogo = brandLogo;
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

	public String getZsContactName() {
		return zsContactName;
	}

	public void setZsContactName(String zsContactName) {
		this.zsContactName = zsContactName;
	}

	public String getFwContactName() {
		return fwContactName;
	}

	public void setFwContactName(String fwContactName) {
		this.fwContactName = fwContactName;
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

	public String getMchtCode() {
		return mchtCode;
	}

	public void setMchtCode(String mchtCode) {
		this.mchtCode = mchtCode;
	}

	public String getIsSpeciallyApprovedDesc() {
		return isSpeciallyApprovedDesc;
	}

	public void setIsSpeciallyApprovedDesc(String isSpeciallyApprovedDesc) {
		this.isSpeciallyApprovedDesc = isSpeciallyApprovedDesc;
	}

	public Integer getFwStaffId() {
		return fwStaffId;
	}

	public void setFwStaffId(Integer fwStaffId) {
		this.fwStaffId = fwStaffId;
	}
	public List<MchtBrandAptitudeCustom> getMchtBrandAptitudeCustoms() {
		return mchtBrandAptitudeCustoms;
	}
	public void setMchtBrandAptitudeCustoms(
			List<MchtBrandAptitudeCustom> mchtBrandAptitudeCustoms) {
		this.mchtBrandAptitudeCustoms = mchtBrandAptitudeCustoms;
	}
	
	public List<Map<String, Object>> getMchtPlatformAuthPics() {
		return mchtPlatformAuthPics;
	}
	public void setMchtPlatformAuthPics(
			List<Map<String, Object>> mchtPlatformAuthPics) {
		this.mchtPlatformAuthPics = mchtPlatformAuthPics;
	}
	public List<Map<String, Object>> getMchtBrandInvoicePics() {
		return mchtBrandInvoicePics;
	}
	public void setMchtBrandInvoicePics(
			List<Map<String, Object>> mchtBrandInvoicePics) {
		this.mchtBrandInvoicePics = mchtBrandInvoicePics;
	}
	public List<Map<String, Object>> getMchtBrandInspectionPics() {
		return mchtBrandInspectionPics;
	}
	public void setMchtBrandInspectionPics(
			List<Map<String, Object>> mchtBrandInspectionPics) {
		this.mchtBrandInspectionPics = mchtBrandInspectionPics;
	}
	public List<Map<String, Object>> getMchtBrandOtherPics() {
		return mchtBrandOtherPics;
	}
	public void setMchtBrandOtherPics(List<Map<String, Object>> mchtBrandOtherPics) {
		this.mchtBrandOtherPics = mchtBrandOtherPics;
	}
	public List<MchtBrandProductTypeCustom> getMchtBrandProductTypeCustoms() {
		return mchtBrandProductTypeCustoms;
	}
	public void setMchtBrandProductTypeCustoms(
			List<MchtBrandProductTypeCustom> mchtBrandProductTypeCustoms) {
		this.mchtBrandProductTypeCustoms = mchtBrandProductTypeCustoms;
	}
	public BigDecimal getFeeRate() {
		return feeRate;
	}
	public void setFeeRate(BigDecimal feeRate) {
		this.feeRate = feeRate;
	}
	public Integer getSingleProductActivityCount() {
		return singleProductActivityCount;
	}

	public void setSingleProductActivityCount(Integer singleProductActivityCount) {
		this.singleProductActivityCount = singleProductActivityCount;
	}

	public Integer getActivityCount() {
		return activityCount;
	}

	public void setActivityCount(Integer activityCount) {
		this.activityCount = activityCount;
	}

	public Integer getProductCount() {
		return productCount;
	}

	public void setProductCount(Integer productCount) {
		this.productCount = productCount;
	}

	public List<MchtPlatformAuthPic> getMchtPlatformAuthPicList() {
		return mchtPlatformAuthPicList;
	}

	public void setMchtPlatformAuthPicList(
			List<MchtPlatformAuthPic> mchtPlatformAuthPicList) {
		this.mchtPlatformAuthPicList = mchtPlatformAuthPicList;
	}

	public List<MchtBrandInvoicePic> getMchtBrandInvoicePicList() {
		return mchtBrandInvoicePicList;
	}

	public void setMchtBrandInvoicePicList(
			List<MchtBrandInvoicePic> mchtBrandInvoicePicList) {
		this.mchtBrandInvoicePicList = mchtBrandInvoicePicList;
	}

	public List<MchtBrandInspectionPic> getMchtBrandInspectionPicList() {
		return mchtBrandInspectionPicList;
	}

	public void setMchtBrandInspectionPicList(
			List<MchtBrandInspectionPic> mchtBrandInspectionPicList) {
		this.mchtBrandInspectionPicList = mchtBrandInspectionPicList;
	}

	public List<MchtBrandOtherPic> getMchtBrandOtherPicList() {
		return mchtBrandOtherPicList;
	}

	public void setMchtBrandOtherPicList(
			List<MchtBrandOtherPic> mchtBrandOtherPicList) {
		this.mchtBrandOtherPicList = mchtBrandOtherPicList;
	}

	public Integer getZsStaffId() {
		return zsStaffId;
	}

	public void setZsStaffId(Integer zsStaffId) {
		this.zsStaffId = zsStaffId;
	}

	public String getAssistantContact() {
		return assistantContact;
	}

	public void setAssistantContact(String assistantContact) {
		this.assistantContact = assistantContact;
	}

	public Integer getYyStaffId() {
		return yyStaffId;
	}

	public void setYyStaffId(Integer yyStaffId) {
		this.yyStaffId = yyStaffId;
	}

	public Date getCooperateBeginDate() {
		return cooperateBeginDate;
	}

	public void setCooperateBeginDate(Date cooperateBeginDate) {
		this.cooperateBeginDate = cooperateBeginDate;
	}

	public String getProductTypeName() {
		return productTypeName;
	}

	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}
	
}