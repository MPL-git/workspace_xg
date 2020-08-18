package com.jf.entity;

import java.util.Date;

public class MemberExtendCustom extends MemberExtend
{
  private String memberNick;
  private String memberMobile;
  private Date memberCreateDate;
  private String mchtCompany;
  private String zsName;
  private String mchtCode;

  public String getMemberNick()
  {
    return this.memberNick;
  }
  public void setMemberNick(String memberNick) {
    this.memberNick = memberNick;
  }
  public String getMemberMobile() {
    return this.memberMobile;
  }
  public void setMemberMobile(String memberMobile) {
    this.memberMobile = memberMobile;
  }
  public Date getMemberCreateDate() {
    return this.memberCreateDate;
  }
  public void setMemberCreateDate(Date memberCreateDate) {
    this.memberCreateDate = memberCreateDate;
  }
  public String getMchtCompany() {
    return this.mchtCompany;
  }
  public void setMchtCompany(String mchtCompany) {
    this.mchtCompany = mchtCompany;
  }
  public String getZsName() {
    return this.zsName;
  }
  public void setZsName(String zsName) {
    this.zsName = zsName;
  }
  public String getMchtCode() {
    return this.mchtCode;
  }
  public void setMchtCode(String mchtCode) {
    this.mchtCode = mchtCode;
  }
}