package com.jf.entity;

import java.util.Date;


public class MemberActivityFootprintCustom extends MemberActivityFootprint{
	private String activityMchtCode;
	private String activityMchtShopName;
	private Date preheatTime;
	private String activityProductTypename;
	private String activityProductBrandname; 
	private String activityName;
	private String benefitPoint; 
	private String preferentialTypeDesc;
	private Integer sumCooPass; 
	private String activityStatus;
	private Date activityBeginTime;
	private Date activityEndTime;
	private String nick;
	public String getActivityMchtCode() {
		return activityMchtCode;
	}
	public void setActivityMchtCode(String activityMchtCode) {
		this.activityMchtCode = activityMchtCode;
	}
	public String getActivityMchtShopName() {
		return activityMchtShopName;
	}
	public void setActivityMchtShopName(String activityMchtShopName) {
		this.activityMchtShopName = activityMchtShopName;
	}
	public Date getPreheatTime() {
		return preheatTime;
	}
	public void setPreheatTime(Date preheatTime) {
		this.preheatTime = preheatTime;
	}
	public String getActivityProductTypename() {
		return activityProductTypename;
	}
	public void setActivityProductTypename(String activityProductTypename) {
		this.activityProductTypename = activityProductTypename;
	}
	public String getActivityProductBrandname() {
		return activityProductBrandname;
	}
	public void setActivityProductBrandname(String activityProductBrandname) {
		this.activityProductBrandname = activityProductBrandname;
	}
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	public String getBenefitPoint() {
		return benefitPoint;
	}
	public void setBenefitPoint(String benefitPoint) {
		this.benefitPoint = benefitPoint;
	}
	public String getPreferentialTypeDesc() {
		return preferentialTypeDesc;
	}
	public void setPreferentialTypeDesc(String preferentialTypeDesc) {
		this.preferentialTypeDesc = preferentialTypeDesc;
	}
	public Integer getSumCooPass() {
		return sumCooPass;
	}
	public void setSumCooPass(Integer sumCooPass) {
		this.sumCooPass = sumCooPass;
	}
	public String getActivityStatus() {
		return activityStatus;
	}
	public void setActivityStatus(String activityStatus) {
		this.activityStatus = activityStatus;
	}
	public Date getActivityBeginTime() {
		return activityBeginTime;
	}
	public void setActivityBeginTime(Date activityBeginTime) {
		this.activityBeginTime = activityBeginTime;
	}
	public Date getActivityEndTime() {
		return activityEndTime;
	}
	public void setActivityEndTime(Date activityEndTime) {
		this.activityEndTime = activityEndTime;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}	

}
