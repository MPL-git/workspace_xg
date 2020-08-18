package com.jf.entity;

/**
 * @author Pengl
 * @create 2020-04-09 下午 2:15
 */
public class SubOrderCustom extends SubOrder {

	private String receiverPhone;
	private Integer paymentId;

	public String getReceiverPhone() {
		return receiverPhone;
	}

	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}

	public Integer getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Integer paymentId) {
		this.paymentId = paymentId;
	}
}
