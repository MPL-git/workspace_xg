package com.jf.entity;


public class MchtBankAccountCustom extends MchtBankAccount{
	private String bankName;
	
	private String accTypeDesc;
	
	private String statusDesc;
	
	private String mchtCode;
	
	private String companyName;
	
	private String contractCode;
	
	private String cwContactName;
	
	private Integer cwStaffId;
	
	private Integer hisCount;
	
	private String settledType;

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

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getAccTypeDesc() {
		return accTypeDesc;
	}

	public void setAccTypeDesc(String accTypeDesc) {
		this.accTypeDesc = accTypeDesc;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getContractCode() {
		return contractCode;
	}

	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}

	public String getCwContactName() {
		return cwContactName;
	}

	public void setCwContactName(String cwContactName) {
		this.cwContactName = cwContactName;
	}

	public Integer getCwStaffId() {
		return cwStaffId;
	}

	public void setCwStaffId(Integer cwStaffId) {
		this.cwStaffId = cwStaffId;
	}

	public Integer getHisCount() {
		return hisCount;
	}

	public void setHisCount(Integer hisCount) {
		this.hisCount = hisCount;
	}

	public String getMchtCode() {
		return mchtCode;
	}

	public void setMchtCode(String mchtCode) {
		this.mchtCode = mchtCode;
	}

	public String getSettledType() {
		return settledType;
	}

	public void setSettledType(String settledType) {
		this.settledType = settledType;
	}
	
}

