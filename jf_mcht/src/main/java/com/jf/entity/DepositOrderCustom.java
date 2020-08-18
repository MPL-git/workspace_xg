package com.jf.entity;

public class DepositOrderCustom extends DepositOrder{
	private String paymentTypeDesc;
	private String statusDesc;
	private PlatformCapitalAccount platformCapitalAccount;
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
	
	
}
