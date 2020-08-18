package com.jf.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class SubOrderCustom extends SubOrder{
    private String activityIds;
	
    private String receiverName;
    
    private String receiverPhone;
    
    private String receiverAddress;
    
    private Date orderPayDate;
    
    private String expressName;
    
    private String memberStatus;
    
    private List<OrderDtlCustom> orderDtlCustoms=new ArrayList<OrderDtlCustom>();

    private Integer paymentId;
    
    private Date yesterday;

    private Date deliveryDateAfter;

    private Date deliveryLastDateAfter;

	public Date getDeliveryDateAfter() {
		return deliveryDateAfter;
	}

	public void setDeliveryDateAfter(Date deliveryDateAfter) {
		this.deliveryDateAfter = deliveryDateAfter;
	}

	public Date getDeliveryLastDateAfter() {
		return deliveryLastDateAfter;
	}

	public void setDeliveryLastDateAfter(Date deliveryLastDateAfter) {
		this.deliveryLastDateAfter = deliveryLastDateAfter;
	}

	public String getActivityIds() {
		return activityIds;
	}

	public void setActivityIds(String activityIds) {
		this.activityIds = activityIds;
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

	public String getReceiverAddress() {
		return receiverAddress;
	}

	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}
	
	public Date getOrderPayDate() {
		return orderPayDate;
	}

	public void setOrderPayDate(Date orderPayDate) {
		this.orderPayDate = orderPayDate;
	}

	public String getExpressName() {
		return expressName;
	}

	public void setExpressName(String expressName) {
		this.expressName = expressName;
	}
	
	public List<OrderDtlCustom> getOrderDtlCustoms() {
		return orderDtlCustoms;
	}

	public void setOrderDtlCustoms(List<OrderDtlCustom> orderDtlCustoms) {
		this.orderDtlCustoms = orderDtlCustoms;
	}

	public Integer getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Integer paymentId) {
		this.paymentId = paymentId;
	}

	public String getMemberStatus() {
		return memberStatus;
	}

	public void setMemberStatus(String memberStatus) {
		this.memberStatus = memberStatus;
	}

	public Date getYesterday() {
		return yesterday;
	}

	public void setYesterday(Date yesterday) {
		this.yesterday = yesterday;
	}

}