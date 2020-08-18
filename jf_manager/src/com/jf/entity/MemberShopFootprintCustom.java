package com.jf.entity;

import java.util.Date;


public class MemberShopFootprintCustom extends MemberShopFootprint{
	private String mchtCode;
	private String shopName;
	private Date cooperateBeginDate;
	private String mchtproductTypeName;
	private String mchtproductBandName; 
	private String nick;
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
	public Date getCooperateBeginDate() {
		return cooperateBeginDate;
	}
	public void setCooperateBeginDate(Date cooperateBeginDate) {
		this.cooperateBeginDate = cooperateBeginDate;
	}
	public String getMchtproductTypeName() {
		return mchtproductTypeName;
	}
	public void setMchtproductTypeName(String mchtproductTypeName) {
		this.mchtproductTypeName = mchtproductTypeName;
	}
	public String getMchtproductBandName() {
		return mchtproductBandName;
	}
	public void setMchtproductBandName(String mchtproductBandName) {
		this.mchtproductBandName = mchtproductBandName;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}

}
