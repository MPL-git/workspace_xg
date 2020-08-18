package com.jf.entity;


public class MemberAddressCustom extends MemberAddress{
	private String nick;
	private String fullAddress;

	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getFullAddress() {
		return fullAddress;
	}
	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}
}
