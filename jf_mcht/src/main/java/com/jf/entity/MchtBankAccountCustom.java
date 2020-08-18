package com.jf.entity;


public class MchtBankAccountCustom extends MchtBankAccount{
	private String bankName;
	
	private String accTypeDesc;
	
	private String statusDesc;

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
	
	
}

