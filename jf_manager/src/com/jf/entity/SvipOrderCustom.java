package com.jf.entity;

public class SvipOrderCustom extends SvipOrder {

	private String memberNick; // 会员昵称
	private String memberStatus; // 会员状态
	private String memberStatusDesc; // 会员状态
	private String buyTypeDesc; // 购买类型
	private String paymentName; // 付款渠道
	private String svipOrderDesc; // 订单状态
	private String combineOrderCode; // 母订单编号
	private Integer combineOrderId; // 母订单ID

	public String getMemberNick() {
		return memberNick;
	}

	public void setMemberNick(String memberNick) {
		this.memberNick = memberNick;
	}

	public String getMemberStatus() {
		return memberStatus;
	}

	public void setMemberStatus(String memberStatus) {
		this.memberStatus = memberStatus;
	}

	public String getMemberStatusDesc() {
		return memberStatusDesc;
	}

	public void setMemberStatusDesc(String memberStatusDesc) {
		this.memberStatusDesc = memberStatusDesc;
	}

	public String getBuyTypeDesc() {
		return buyTypeDesc;
	}

	public void setBuyTypeDesc(String buyTypeDesc) {
		this.buyTypeDesc = buyTypeDesc;
	}

	public String getPaymentName() {
		return paymentName;
	}

	public void setPaymentName(String paymentName) {
		this.paymentName = paymentName;
	}

	public String getSvipOrderDesc() {
		return svipOrderDesc;
	}

	public void setSvipOrderDesc(String svipOrderDesc) {
		this.svipOrderDesc = svipOrderDesc;
	}

	public String getCombineOrderCode() {
		return combineOrderCode;
	}

	public void setCombineOrderCode(String combineOrderCode) {
		this.combineOrderCode = combineOrderCode;
	}

	public Integer getCombineOrderId() {
		return combineOrderId;
	}

	public void setCombineOrderId(Integer combineOrderId) {
		this.combineOrderId = combineOrderId;
	}
}
