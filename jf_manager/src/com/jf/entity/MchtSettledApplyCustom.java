package com.jf.entity;

import java.math.BigDecimal;
import java.util.Date;

public class MchtSettledApplyCustom extends MchtSettledApply {
	private String sourceTypeDesc;
	private String regFeeTypeDesc;
	private String areaName;
	private String platformContactName;
	private String statusDesc;
	private String mchtCode;
	private String mchtStatusDesc;
	private String mchtinforemarks;
	private String depositTypeDesc;
	private String depositConfirmStatusDesc;
	private String infoConfirmByName;
	private String depositConfirmByName;
	private Date createDateMax;
	private String recoRd;
	private Integer staffId;
	private Integer roleId;
	private BigDecimal productTypeDeposit;
	private BigDecimal productTypeFeeRate;
	private BigDecimal productTypeIndividualDeposit;
	private BigDecimal productTypeIndividualFeeRate;

	public String getSourceTypeDesc() {
		return sourceTypeDesc;
	}

	public void setSourceTypeDesc(String sourceTypeDesc) {
		this.sourceTypeDesc = sourceTypeDesc;
	}

	public String getRegFeeTypeDesc() {
		return regFeeTypeDesc;
	}

	public void setRegFeeTypeDesc(String regFeeTypeDesc) {
		this.regFeeTypeDesc = regFeeTypeDesc;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getPlatformContactName() {
		return platformContactName;
	}

	public void setPlatformContactName(String platformContactName) {
		this.platformContactName = platformContactName;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public String getMchtCode() {
		return mchtCode;
	}

	public void setMchtCode(String mchtCode) {
		this.mchtCode = mchtCode;
	}

	public String getMchtStatusDesc() {
		return mchtStatusDesc;
	}

	public void setMchtStatusDesc(String mchtStatusDesc) {
		this.mchtStatusDesc = mchtStatusDesc;
	}

	public String getMchtinforemarks() {
		return mchtinforemarks;
	}

	public void setMchtinforemarks(String mchtinforemarks) {
		this.mchtinforemarks = mchtinforemarks;
	}

	public String getDepositTypeDesc() {
		return depositTypeDesc;
	}

	public void setDepositTypeDesc(String depositTypeDesc) {
		this.depositTypeDesc = depositTypeDesc;
	}

	public String getDepositConfirmStatusDesc() {
		return depositConfirmStatusDesc;
	}

	public void setDepositConfirmStatusDesc(String depositConfirmStatusDesc) {
		this.depositConfirmStatusDesc = depositConfirmStatusDesc;
	}

	public String getInfoConfirmByName() {
		return infoConfirmByName;
	}

	public void setInfoConfirmByName(String infoConfirmByName) {
		this.infoConfirmByName = infoConfirmByName;
	}

	public String getDepositConfirmByName() {
		return depositConfirmByName;
	}

	public void setDepositConfirmByName(String depositConfirmByName) {
		this.depositConfirmByName = depositConfirmByName;
	}

	public Date getCreateDateMax() {
		return createDateMax;
	}

	public void setCreateDateMax(Date createDateMax) {
		this.createDateMax = createDateMax;
	}

	public String getRecoRd() {
		return recoRd;
	}

	public void setRecoRd(String recoRd) {
		this.recoRd = recoRd;
	}

	public Integer getStaffId() {
		return staffId;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public BigDecimal getProductTypeDeposit() {
		return productTypeDeposit;
	}

	public void setProductTypeDeposit(BigDecimal productTypeDeposit) {
		this.productTypeDeposit = productTypeDeposit;
	}

	public BigDecimal getProductTypeFeeRate() {
		return productTypeFeeRate;
	}

	public void setProductTypeFeeRate(BigDecimal productTypeFeeRate) {
		this.productTypeFeeRate = productTypeFeeRate;
	}

	public BigDecimal getProductTypeIndividualDeposit() {
		return productTypeIndividualDeposit;
	}

	public void setProductTypeIndividualDeposit(
			BigDecimal productTypeIndividualDeposit) {
		this.productTypeIndividualDeposit = productTypeIndividualDeposit;
	}

	public BigDecimal getProductTypeIndividualFeeRate() {
		return productTypeIndividualFeeRate;
	}

	public void setProductTypeIndividualFeeRate(
			BigDecimal productTypeIndividualFeeRate) {
		this.productTypeIndividualFeeRate = productTypeIndividualFeeRate;
	}

}
