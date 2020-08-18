package com.jf.entity;

import java.util.Date;

public class OrderAbnormalLogCustom extends OrderAbnormalLog {

	private String followStatusDesc;
	private String followByName;
	private Date payDate;
	private Date deliveryDate;
	private Date deliveryLastDate;
	private String subOrderCode;
	private String statusDesc;
	private String brandCode;
	private String mchtCode;
	private String shopName;
	private String serviceOrderCode;
	private String receiverName;
	private String ordercode;
	private String logRemarks;
	private String companyName;
	
	public String getFollowStatusDesc() {
		return followStatusDesc;
	}
	public void setFollowStatusDesc(String followStatusDesc) {
		this.followStatusDesc = followStatusDesc;
	}
	public String getFollowByName() {
		return followByName;
	}
	public void setFollowByName(String followByName) {
		this.followByName = followByName;
	}
	public Date getPayDate() {
		return payDate;
	}
	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}
	public Date getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public Date getDeliveryLastDate() {
		return deliveryLastDate;
	}
	public void setDeliveryLastDate(Date deliveryLastDate) {
		this.deliveryLastDate = deliveryLastDate;
	}
	public String getSubOrderCode() {
		return subOrderCode;
	}
	public void setSubOrderCode(String subOrderCode) {
		this.subOrderCode = subOrderCode;
	}
	public String getStatusDesc() {
		return statusDesc;
	}
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	public String getBrandCode() {
		return brandCode;
	}
	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
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
	public String getServiceOrderCode() {
		return serviceOrderCode;
	}
	public void setServiceOrderCode(String serviceOrderCode) {
		this.serviceOrderCode = serviceOrderCode;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public String getOrdercode() {
		return ordercode;
	}
	public void setOrdercode(String ordercode) {
		this.ordercode = ordercode;
	}
	public String getLogRemarks() {
		return logRemarks;
	}
	public void setLogRemarks(String logRemarks) {
		this.logRemarks = logRemarks;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
}
