package com.jf.entity;


public class InterventionOrderCustom extends InterventionOrder{
	private String customerServiceOrderCode;
	
	private String reasonDesc;
	
	private String statusDesc;
	
	private String memberStatus;

	public String getCustomerServiceOrderCode() {
		return customerServiceOrderCode;
	}

	public void setCustomerServiceOrderCode(String customerServiceOrderCode) {
		this.customerServiceOrderCode = customerServiceOrderCode;
	}

	public String getReasonDesc() {
		return reasonDesc;
	}

	public void setReasonDesc(String reasonDesc) {
		this.reasonDesc = reasonDesc;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public String getMemberStatus() {
		return memberStatus;
	}

	public void setMemberStatus(String memberStatus) {
		this.memberStatus = memberStatus;
	}
	
	
}

