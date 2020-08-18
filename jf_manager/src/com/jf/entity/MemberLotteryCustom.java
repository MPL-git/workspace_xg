package com.jf.entity;

import java.util.Date;

public class MemberLotteryCustom extends MemberLottery {

	private String memberNick;// 用户名称

	private String memberMobile;// 用户电话

	private String receiverName;// 收货人姓名

	private String receiverPhone;// 收货人电话

	private String receiverAddress;// 收货人地址

	private String couponProductCode;

	private String productBrandName;

	private String useStatus;

	private Date useDate;

	private String combineOrderCode;

	public String getMemberNick() {
		return memberNick;
	}

	public void setMemberNick(String memberNick) {
		this.memberNick = memberNick;
	}

	public String getMemberMobile() {
		return memberMobile;
	}

	public void setMemberMobile(String memberMobile) {
		this.memberMobile = memberMobile;
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

	public String getCouponProductCode() {
		return couponProductCode;
	}

	public void setCouponProductCode(String couponProductCode) {
		this.couponProductCode = couponProductCode;
	}

	public String getProductBrandName() {
		return productBrandName;
	}

	public void setProductBrandName(String productBrandName) {
		this.productBrandName = productBrandName;
	}

	public String getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(String useStatus) {
		this.useStatus = useStatus;
	}

	public Date getUseDate() {
		return useDate;
	}

	public void setUseDate(Date useDate) {
		this.useDate = useDate;
	}

	public String getCombineOrderCode() {
		return combineOrderCode;
	}

	public void setCombineOrderCode(String combineOrderCode) {
		this.combineOrderCode = combineOrderCode;
	}
}
