package com.jf.entity;

public class SalesmanCustom extends Salesman{
	private String nick;
	private Integer shopownerCount;
	private String createName;
	
	public String getCreateName() {
		return createName;
	}
	public void setCreateName(String createName) {
		this.createName = createName;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public Integer getShopownerCount() {
		return shopownerCount;
	}
	public void setShopownerCount(Integer shopownerCount) {
		this.shopownerCount = shopownerCount;
	}
	
}