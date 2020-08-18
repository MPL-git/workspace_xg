package com.jf.entity;

import java.util.Date;

public class MemberLabelRuleCustom extends MemberLabelRule{
	
	private String memberlabelTypeName;
	private String memberlabelName;
	private String memberlabelStatus;
	private String staffName;
	
	private String memberStatusDesc;
	private Date memberCreateDate;
	private String memberNick;
	private Integer memberId;
	private String memberMobile;
	
	public String getMemberlabelTypeName() {
		return memberlabelTypeName;
	}
	public void setMemberlabelTypeName(String memberlabelTypeName) {
		this.memberlabelTypeName = memberlabelTypeName;
	}
	public String getMemberlabelName() {
		return memberlabelName;
	}
	public void setMemberlabelName(String memberlabelName) {
		this.memberlabelName = memberlabelName;
	}
	public String getMemberlabelStatus() {
		return memberlabelStatus;
	}
	public void setMemberlabelStatus(String memberlabelStatus) {
		this.memberlabelStatus = memberlabelStatus;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public String getMemberStatusDesc() {
		return memberStatusDesc;
	}
	public void setMemberStatusDesc(String memberStatusDesc) {
		this.memberStatusDesc = memberStatusDesc;
	}
	public Date getMemberCreateDate() {
		return memberCreateDate;
	}
	public void setMemberCreateDate(Date memberCreateDate) {
		this.memberCreateDate = memberCreateDate;
	}
	public String getMemberNick() {
		return memberNick;
	}
	public void setMemberNick(String memberNick) {
		this.memberNick = memberNick;
	}
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	public String getMemberMobile() {
		return memberMobile;
	}
	public void setMemberMobile(String memberMobile) {
		this.memberMobile = memberMobile;
	}
	

}
