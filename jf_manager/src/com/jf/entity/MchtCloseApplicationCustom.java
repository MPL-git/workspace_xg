package com.jf.entity;

import java.math.BigDecimal;
import java.util.Date;



public class MchtCloseApplicationCustom extends MchtCloseApplication{
	private String mchtCode;
	private String companyName;
	private String shopName;
	private String mchtProductBrands;
	private Date agreementBeginDate;
	private Date agreementEndDate;
	private String archiveStatus;
	private Date archiveDate;
	private String subOrderCode;
	private Date deliveryDate;
	private Date completeDate;
	private BigDecimal totalAmt;
	private BigDecimal payAmt;
	private BigDecimal unpayAmt;
	private String productTypeName;
	private String zsStaffName;
	private String commodityStaffName;
	private String mchtArchiveStaffName;
	private String kfStaffName;
	private String cwStaffName;
	private String fwStaffName;
	private String directorStaffName;
	private String productStaffName;
	private String settlementStaffName;
	private String opStaffName;
	private String statusDesc;
	private String platformMerchantsContact;//招商对接人
	private String platformFawuContact;//法务对接人
	private Date openingDate;//开通日期
	private String isManageSelf;
	private String mchtType;

	public String getIsManageSelf() {
		return isManageSelf;
	}

	public void setIsManageSelf(String isManageSelf) {
		this.isManageSelf = isManageSelf;
	}

	public String getMchtType() {
		return mchtType;
	}

	public void setMchtType(String mchtType) {
		this.mchtType = mchtType;
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
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public Date getAgreementEndDate() {
		return agreementEndDate;
	}
	public void setAgreementEndDate(Date agreementEndDate) {
		this.agreementEndDate = agreementEndDate;
	}
	public String getArchiveStatus() {
		return archiveStatus;
	}
	public void setArchiveStatus(String archiveStatus) {
		this.archiveStatus = archiveStatus;
	}
	public String getMchtProductBrands() {
		return mchtProductBrands;
	}
	public void setMchtProductBrands(String mchtProductBrands) {
		this.mchtProductBrands = mchtProductBrands;
	}
	public String getSubOrderCode() {
		return subOrderCode;
	}
	public void setSubOrderCode(String subOrderCode) {
		this.subOrderCode = subOrderCode;
	}
	public Date getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public BigDecimal getPayAmt() {
		return payAmt;
	}
	public void setPayAmt(BigDecimal payAmt) {
		this.payAmt = payAmt;
	}
	public String getProductTypeName() {
		return productTypeName;
	}
	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}
	public BigDecimal getTotalAmt() {
		return totalAmt;
	}
	public void setTotalAmt(BigDecimal totalAmt) {
		this.totalAmt = totalAmt;
	}
	public BigDecimal getUnpayAmt() {
		return unpayAmt;
	}
	public void setUnpayAmt(BigDecimal unpayAmt) {
		this.unpayAmt = unpayAmt;
	}
	public String getZsStaffName() {
		return zsStaffName;
	}
	public void setZsStaffName(String zsStaffName) {
		this.zsStaffName = zsStaffName;
	}
	public String getCommodityStaffName() {
		return commodityStaffName;
	}
	public void setCommodityStaffName(String commodityStaffName) {
		this.commodityStaffName = commodityStaffName;
	}
	public String getMchtArchiveStaffName() {
		return mchtArchiveStaffName;
	}
	public void setMchtArchiveStaffName(String mchtArchiveStaffName) {
		this.mchtArchiveStaffName = mchtArchiveStaffName;
	}
	public String getKfStaffName() {
		return kfStaffName;
	}
	public void setKfStaffName(String kfStaffName) {
		this.kfStaffName = kfStaffName;
	}
	public String getCwStaffName() {
		return cwStaffName;
	}
	public void setCwStaffName(String cwStaffName) {
		this.cwStaffName = cwStaffName;
	}
	public String getFwStaffName() {
		return fwStaffName;
	}
	public void setFwStaffName(String fwStaffName) {
		this.fwStaffName = fwStaffName;
	}
	public String getDirectorStaffName() {
		return directorStaffName;
	}
	public void setDirectorStaffName(String directorStaffName) {
		this.directorStaffName = directorStaffName;
	}
	public String getProductStaffName() {
		return productStaffName;
	}
	public void setProductStaffName(String productStaffName) {
		this.productStaffName = productStaffName;
	}
	public String getSettlementStaffName() {
		return settlementStaffName;
	}
	public void setSettlementStaffName(String settlementStaffName) {
		this.settlementStaffName = settlementStaffName;
	}
	public String getOpStaffName() {
		return opStaffName;
	}
	public void setOpStaffName(String opStaffName) {
		this.opStaffName = opStaffName;
	}
	public Date getAgreementBeginDate() {
		return agreementBeginDate;
	}
	public void setAgreementBeginDate(Date agreementBeginDate) {
		this.agreementBeginDate = agreementBeginDate;
	}
	public Date getArchiveDate() {
		return archiveDate;
	}
	public void setArchiveDate(Date archiveDate) {
		this.archiveDate = archiveDate;
	}
	public Date getCompleteDate() {
		return completeDate;
	}
	public void setCompleteDate(Date completeDate) {
		this.completeDate = completeDate;
	}
	public String getStatusDesc() {
		return statusDesc;
	}
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	public String getPlatformMerchantsContact() {
		return platformMerchantsContact;
	}
	public void setPlatformMerchantsContact(String platformMerchantsContact) {
		this.platformMerchantsContact = platformMerchantsContact;
	}
	public String getPlatformFawuContact() {
		return platformFawuContact;
	}
	public void setPlatformFawuContact(String platformFawuContact) {
		this.platformFawuContact = platformFawuContact;
	}
	public Date getOpeningDate() {
		return openingDate;
	}
	public void setOpeningDate(Date openingDate) {
		this.openingDate = openingDate;
	}
	
	
}
