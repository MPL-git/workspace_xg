package com.jf.entity;

import java.util.Date;


@SuppressWarnings("serial")
public class MchtContractCustom extends MchtContract {
	private Date cooperateBeginDate;
	
	private String zsContact;
	
	private String mchtCode;
	
	private String companyName;
	
	private String shopName;
	
	private String archiveStatusDesc;
	
	private String corporationIdcardNo;
	
	private String uscc;
	
	private String mchtInfoArchiveStatus;
	
	private String companyInfoArchiveStatus;
	
	private String mchtBrandArchiveStatusHtml;
	
	private String fwContact;
	
	private String settledType;
	
	private String businessLicensePic;
	
	private String licenseStatus;
	
	private String licenseArchiveStatus;
	
	private String platformContactName;
	
	private Integer parentContractId;
	private Integer newMchtContractId;
	private Date logCreateDate;
	private Date zsLogCreateDate;
	private String productName;
	private String mchtProductBrandName;
	private String zsRenewProStatusDesc;
	private String fwRenewProStatusDesc;
	private String mchtInfoStatus;
	private Integer fwStaffId;
	private Integer fwAssistantId;
	private String auditStatusDesc;
	private String yyContact;
	private Integer yyStaffId;
	private Integer yyAssistantId;
	private String shopCloseReasonDesc;
	private Date logCreateDate1;
	private Date logCreateDate4;
	private Date logCreateDate5;
	private Date submitDate;//商家不续签管理的提交时间
	private Integer days;
	
	public Date getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
	}

	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
	}

	public Date getLogCreateDate1() {
		return logCreateDate1;
	}

	public void setLogCreateDate1(Date logCreateDate1) {
		this.logCreateDate1 = logCreateDate1;
	}

	public Date getLogCreateDate4() {
		return logCreateDate4;
	}

	public void setLogCreateDate4(Date logCreateDate4) {
		this.logCreateDate4 = logCreateDate4;
	}

	public Date getLogCreateDate5() {
		return logCreateDate5;
	}

	public void setLogCreateDate5(Date logCreateDate5) {
		this.logCreateDate5 = logCreateDate5;
	}

	public Integer getFwStaffId() {
		return fwStaffId;
	}

	public void setFwStaffId(Integer fwStaffId) {
		this.fwStaffId = fwStaffId;
	}

	public String getShopCloseReasonDesc() {
		return shopCloseReasonDesc;
	}

	public void setShopCloseReasonDesc(String shopCloseReasonDesc) {
		this.shopCloseReasonDesc = shopCloseReasonDesc;
	}

	public Integer getYyStaffId() {
		return yyStaffId;
	}

	public void setYyStaffId(Integer yyStaffId) {
		this.yyStaffId = yyStaffId;
	}

	public Integer getYyAssistantId() {
		return yyAssistantId;
	}

	public void setYyAssistantId(Integer yyAssistantId) {
		this.yyAssistantId = yyAssistantId;
	}

	public String getYyContact() {
		return yyContact;
	}

	public void setYyContact(String yyContact) {
		this.yyContact = yyContact;
	}

	public String getAuditStatusDesc() {
		return auditStatusDesc;
	}

	public void setAuditStatusDesc(String auditStatusDesc) {
		this.auditStatusDesc = auditStatusDesc;
	}

	public Integer getFwAssistantId() {
		return fwAssistantId;
	}

	public void setFwAssistantId(Integer fwAssistantId) {
		this.fwAssistantId = fwAssistantId;
	}

	public Integer getParentContractId() {
		return parentContractId;
	}

	public void setParentContractId(Integer parentContractId) {
		this.parentContractId = parentContractId;
	}

	public Date getZsLogCreateDate() {
		return zsLogCreateDate;
	}

	public void setZsLogCreateDate(Date zsLogCreateDate) {
		this.zsLogCreateDate = zsLogCreateDate;
	}

	public Integer getNewMchtContractId() {
		return newMchtContractId;
	}

	public void setNewMchtContractId(Integer newMchtContractId) {
		this.newMchtContractId = newMchtContractId;
	}

	public String getMchtInfoStatus() {
		return mchtInfoStatus;
	}

	public void setMchtInfoStatus(String mchtInfoStatus) {
		this.mchtInfoStatus = mchtInfoStatus;
	}

	public Date getLogCreateDate() {
		return logCreateDate;
	}

	public void setLogCreateDate(Date logCreateDate) {
		this.logCreateDate = logCreateDate;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getMchtProductBrandName() {
		return mchtProductBrandName;
	}

	public void setMchtProductBrandName(String mchtProductBrandName) {
		this.mchtProductBrandName = mchtProductBrandName;
	}

	public String getZsRenewProStatusDesc() {
		return zsRenewProStatusDesc;
	}

	public void setZsRenewProStatusDesc(String zsRenewProStatusDesc) {
		this.zsRenewProStatusDesc = zsRenewProStatusDesc;
	}

	public String getFwRenewProStatusDesc() {
		return fwRenewProStatusDesc;
	}

	public void setFwRenewProStatusDesc(String fwRenewProStatusDesc) {
		this.fwRenewProStatusDesc = fwRenewProStatusDesc;
	}

	public String getPlatformContactName() {
		return platformContactName;
	}

	public void setPlatformContactName(String platformContactName) {
		this.platformContactName = platformContactName;
	}

	public String getZsContact() {
		return zsContact;
	}

	public void setZsContact(String zsContact) {
		this.zsContact = zsContact;
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

	public String getCorporationIdcardNo() {
		return corporationIdcardNo;
	}

	public void setCorporationIdcardNo(String corporationIdcardNo) {
		this.corporationIdcardNo = corporationIdcardNo;
	}

	public String getUscc() {
		return uscc;
	}

	public void setUscc(String uscc) {
		this.uscc = uscc;
	}

	public String getArchiveStatusDesc() {
		return archiveStatusDesc;
	}

	public void setArchiveStatusDesc(String archiveStatusDesc) {
		this.archiveStatusDesc = archiveStatusDesc;
	}

	public String getMchtInfoArchiveStatus() {
		return mchtInfoArchiveStatus;
	}

	public void setMchtInfoArchiveStatus(String mchtInfoArchiveStatus) {
		this.mchtInfoArchiveStatus = mchtInfoArchiveStatus;
	}

	public Date getCooperateBeginDate() {
		return cooperateBeginDate;
	}

	public void setCooperateBeginDate(Date cooperateBeginDate) {
		this.cooperateBeginDate = cooperateBeginDate;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getCompanyInfoArchiveStatus() {
		return companyInfoArchiveStatus;
	}

	public void setCompanyInfoArchiveStatus(String companyInfoArchiveStatus) {
		this.companyInfoArchiveStatus = companyInfoArchiveStatus;
	}

	public String getFwContact() {
		return fwContact;
	}

	public void setFwContact(String fwContact) {
		this.fwContact = fwContact;
	}

	public String getMchtBrandArchiveStatusHtml() {
		return mchtBrandArchiveStatusHtml;
	}

	public void setMchtBrandArchiveStatusHtml(String mchtBrandArchiveStatusHtml) {
		this.mchtBrandArchiveStatusHtml = mchtBrandArchiveStatusHtml;
	}

	public String getSettledType() {
		return settledType;
	}

	public void setSettledType(String settledType) {
		this.settledType = settledType;
	}

	public String getBusinessLicensePic() {
		return businessLicensePic;
	}

	public void setBusinessLicensePic(String businessLicensePic) {
		this.businessLicensePic = businessLicensePic;
	}

	public String getLicenseStatus() {
		return licenseStatus;
	}

	public void setLicenseStatus(String licenseStatus) {
		this.licenseStatus = licenseStatus;
	}

	public String getLicenseArchiveStatus() {
		return licenseArchiveStatus;
	}

	public void setLicenseArchiveStatus(String licenseArchiveStatus) {
		this.licenseArchiveStatus = licenseArchiveStatus;
	}
	
}
