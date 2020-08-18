package com.jf.entity;

public class BlackListCustom extends BlackList {

	private String blackTypeDesc;
	private String memberNick;
	private String memberMobile;
	private String createStaffName;

	public String getBlackTypeDesc() {
		return blackTypeDesc;
	}

	public void setBlackTypeDesc(String blackTypeDesc) {
		this.blackTypeDesc = blackTypeDesc;
	}

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

	public String getCreateStaffName() {
		return createStaffName;
	}

	public void setCreateStaffName(String createStaffName) {
		this.createStaffName = createStaffName;
	}

}
