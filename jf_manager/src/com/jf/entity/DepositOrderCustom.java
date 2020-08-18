package com.jf.entity;

public class DepositOrderCustom extends DepositOrder{
	private String paymentTypeDesc;
	private String statusDesc;
	private PlatformCapitalAccount platformCapitalAccount;
	private String mchtCode;
	private String companyName;
	private String staffName;
	private String platformPaymentName;
	public String getPaymentTypeDesc() {
		return paymentTypeDesc;
	}

	public void setPaymentTypeDesc(String paymentTypeDesc) {
		this.paymentTypeDesc = paymentTypeDesc;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public PlatformCapitalAccount getPlatformCapitalAccount() {
		return platformCapitalAccount;
	}

	public void setPlatformCapitalAccount(
			PlatformCapitalAccount platformCapitalAccount) {
		this.platformCapitalAccount = platformCapitalAccount;
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

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getPlatformPaymentName() {
		return platformPaymentName;
	}

	public void setPlatformPaymentName(String platformPaymentName) {
		this.platformPaymentName = platformPaymentName;
	}
	
}
