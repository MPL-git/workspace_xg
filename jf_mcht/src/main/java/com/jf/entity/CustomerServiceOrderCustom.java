package com.jf.entity;

import java.util.Date;


public class CustomerServiceOrderCustom extends CustomerServiceOrder{
	
	/**
	 * 售后类型：A.退款单
	 */
	public static String ORDER_REFUND = "A";
	/**
	 * 售后类型：B.退货单
	 */
	public static String ORDER_RETURN_GOODS = "B";
	/**
	 * 售后类型：C.换货单
	 */
	public static String ORDER_EXCHANGE_GOODS = "C";
	/**
	 * 售后类型：D.直赔单
	 */
	public static String ORDER_DIRECT_COMPENSATION = "D";
	/**
	 * 售后状态：0.进行中(默认)
	 */
	public static String ORDER_STATUS_ING = "0";
	
	/**
	 * 售后状态：1.已完成
	 */
	public static String ORDER_STATUS_COMPLETE = "1";
	
	/**
	 * 售后状态：2.已拒绝
	 */
	public static String ORDER_STATUS_REFUSE = "2";
	
	/**
	 * 售后状态：3.已撤销
	 */
	public static String ORDER_STATUS_CANCLE = "3";
	
	private String subOrderCode;
	
	private String serviceTypeDesc;
	
	private String proStatusDesc;
	
	private String statusDesc;
	
	private OrderDtl orderDtl;
	
	private Date refundDate;
	
	private String logContent;
	
	private String receiverName;
	
	private String receiverPhone;
	
	private String memberStatus;

	public String getSubOrderCode() {
		return subOrderCode;
	}

	public void setSubOrderCode(String subOrderCode) {
		this.subOrderCode = subOrderCode;
	}
	
	public String getServiceTypeDesc() {
		return serviceTypeDesc;
	}

	public void setServiceTypeDesc(String serviceTypeDesc) {
		this.serviceTypeDesc = serviceTypeDesc;
	}

	public String getProStatusDesc() {
		return proStatusDesc;
	}

	public void setProStatusDesc(String proStatusDesc) {
		this.proStatusDesc = proStatusDesc;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public OrderDtl getOrderDtl() {
		return orderDtl;
	}

	public void setOrderDtl(OrderDtl orderDtl) {
		this.orderDtl = orderDtl;
	}
	
	public Date getRefundDate() {
		return refundDate;
	}

	public void setRefundDate(Date refundDate) {
		this.refundDate = refundDate;
	}

	public String getLogContent() {
		return logContent;
	}

	public void setLogContent(String logContent) {
		this.logContent = logContent;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getReceiverPhone() {
		return receiverPhone;
	}

	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}

	public String getMemberStatus() {
		return memberStatus;
	}

	public void setMemberStatus(String memberStatus) {
		this.memberStatus = memberStatus;
	}
	
}
