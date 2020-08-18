package com.jf.entity;


public class CustomerServiceOrderCustom extends CustomerServiceOrder{
	private Integer mchtId;
	
	private String mchtType;

	private Integer memberId;

	private String receiverPhone;

	private Integer supplierUserId;

	private String content;

	private String customOrderPics;

	public Integer getMchtId() {
		return mchtId;
	}

	public void setMchtId(Integer mchtId) {
		this.mchtId = mchtId;
	}

	public String getMchtType() {
		return mchtType;
	}

	public void setMchtType(String mchtType) {
		this.mchtType = mchtType;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public String getReceiverPhone() {
		return receiverPhone;
	}

	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}

	public Integer getSupplierUserId() {
		return supplierUserId;
	}

	public void setSupplierUserId(Integer supplierUserId) {
		this.supplierUserId = supplierUserId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCustomOrderPics() {
		return customOrderPics;
	}

	public void setCustomOrderPics(String customOrderPics) {
		this.customOrderPics = customOrderPics;
	}
}