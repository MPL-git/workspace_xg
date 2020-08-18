package com.jf.entity;

import java.math.BigDecimal;
import java.util.Date;

public class CooperationChangeApplyCustom extends CooperationChangeApply{
	private String changeApplyType;
	
	private String sendStatusDesc;
	
	private String archiveStatusDesc;

	private String mchtCode;
	
	private String companyName;
	
	private String productTypeName;
	
	private String newProductTypeName;
	
	private BigDecimal totalAmt;
	
	private String archiveStatus;
	
	private Date archiveDate;
	
	private String fwName;
	
	private String zsName;
	
	private String contractCode;
	
	private String oldShopName;

	public String getChangeApplyType() {
		return changeApplyType;
	}

	public void setChangeApplyType(String changeApplyType) {
		this.changeApplyType = changeApplyType;
	}

	public String getSendStatusDesc() {
		return sendStatusDesc;
	}

	public void setSendStatusDesc(String sendStatusDesc) {
		this.sendStatusDesc = sendStatusDesc;
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

	public String getNewProductTypeName() {
		return newProductTypeName;
	}

	public void setNewProductTypeName(String newProductTypeName) {
		this.newProductTypeName = newProductTypeName;
	}

	public String getArchiveStatus() {
		return archiveStatus;
	}

	public void setArchiveStatus(String archiveStatus) {
		this.archiveStatus = archiveStatus;
	}

	public Date getArchiveDate() {
		return archiveDate;
	}

	public void setArchiveDate(Date archiveDate) {
		this.archiveDate = archiveDate;
	}

	public BigDecimal getTotalAmt() {
		return totalAmt;
	}

	public void setTotalAmt(BigDecimal totalAmt) {
		this.totalAmt = totalAmt;
	}

	public String getFwName() {
		return fwName;
	}

	public void setFwName(String fwName) {
		this.fwName = fwName;
	}

	public String getZsName() {
		return zsName;
	}

	public void setZsName(String zsName) {
		this.zsName = zsName;
	}

	public String getContractCode() {
		return contractCode;
	}

	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}

	public String getOldShopName() {
		return oldShopName;
	}

	public void setOldShopName(String oldShopName) {
		this.oldShopName = oldShopName;
	}
	
}