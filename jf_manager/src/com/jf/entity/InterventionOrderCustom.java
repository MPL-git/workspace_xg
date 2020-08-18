package com.jf.entity;

public class InterventionOrderCustom extends InterventionOrder {
	private String customerServiceOrderCode; // 售后编号
	private String customerServiceTypeDesc; // 售后类型
	private String mchtCode; // 商家编码
	private String shopName; // 店铺名称
	private String companyName; // 公司名称
	private String reasonDesc; // 介入原因
	private String statusDesc; // 介入单状态
	private String rejectReasonDesc; // 客服拒绝介入理由
	private String winTypeDesc; // 胜诉方
	private String clientResultReasonDesc; // 买家胜诉/败诉理由
	private String mchtResultReasonDesc; // 商家败诉/胜诉理由
	private String platformProcessorName; // 平台处理人
	private String mchtTypeDesc; // 商家类型
	private String contactName; // 运营对接人
	private String memberInfoStatus; // 会员状态

	public String getCustomerServiceOrderCode() {
		return customerServiceOrderCode;
	}

	public void setCustomerServiceOrderCode(String customerServiceOrderCode) {
		this.customerServiceOrderCode = customerServiceOrderCode;
	}

	public String getMchtCode() {
		return mchtCode;
	}

	public void setMchtCode(String mchtCode) {
		this.mchtCode = mchtCode;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
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

	public String getRejectReasonDesc() {
		return rejectReasonDesc;
	}

	public void setRejectReasonDesc(String rejectReasonDesc) {
		this.rejectReasonDesc = rejectReasonDesc;
	}

	public String getWinTypeDesc() {
		return winTypeDesc;
	}

	public void setWinTypeDesc(String winTypeDesc) {
		this.winTypeDesc = winTypeDesc;
	}

	public String getClientResultReasonDesc() {
		return clientResultReasonDesc;
	}

	public void setClientResultReasonDesc(String clientResultReasonDesc) {
		this.clientResultReasonDesc = clientResultReasonDesc;
	}

	public String getMchtResultReasonDesc() {
		return mchtResultReasonDesc;
	}

	public void setMchtResultReasonDesc(String mchtResultReasonDesc) {
		this.mchtResultReasonDesc = mchtResultReasonDesc;
	}

	public String getPlatformProcessorName() {
		return platformProcessorName;
	}

	public void setPlatformProcessorName(String platformProcessorName) {
		this.platformProcessorName = platformProcessorName;
	}

	public String getMchtTypeDesc() {
		return mchtTypeDesc;
	}

	public void setMchtTypeDesc(String mchtTypeDesc) {
		this.mchtTypeDesc = mchtTypeDesc;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getCustomerServiceTypeDesc() {
		return customerServiceTypeDesc;
	}

	public void setCustomerServiceTypeDesc(String customerServiceTypeDesc) {
		this.customerServiceTypeDesc = customerServiceTypeDesc;
	}

	public String getMemberInfoStatus() {
		return memberInfoStatus;
	}

	public void setMemberInfoStatus(String memberInfoStatus) {
		this.memberInfoStatus = memberInfoStatus;
	}

}