package com.jf.entity;

public class ShopownerOrderCustom extends ShopownerOrder {

	private String nick;//昵称 
	private String paymentName;//付款渠道 

	public String getPaymentName() {
		return paymentName;
	}

	public void setPaymentName(String paymentName) {
		this.paymentName = paymentName;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	
}
