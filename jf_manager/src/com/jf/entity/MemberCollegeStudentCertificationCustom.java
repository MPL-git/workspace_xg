package com.jf.entity;

import java.util.Date;

public class MemberCollegeStudentCertificationCustom extends MemberCollegeStudentCertification{
	private String memberNick;
	   
    private String memberMobile;
	     
	private String memberName;
	   
	private Date memberCreateDate;
	
	private String memberStatus;

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

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public Date getMemberCreateDate() {
		return memberCreateDate;
	}

	public void setMemberCreateDate(Date memberCreateDate) {
		this.memberCreateDate = memberCreateDate;
	}

	public String getMemberStatus() {
		return memberStatus;
	}

	public void setMemberStatus(String memberStatus) {
		this.memberStatus = memberStatus;
	}
	   	   	   
}