package com.jf.entity;

import java.util.Date;

public class MemberActivityFootprintCustom extends MemberActivityFootprint{
	private String entryPic;
	private String activityName;
	private String areaEntryPic;
	private String activityAreaName;
	private Date activityBeginTime;
	private Date activityEndTime;
	private String source;
	private Integer activityAreaId;
	private String benefitPoint;
	
	public String getEntryPic() {
		return entryPic;
	}
	
	public void setEntryPic(String entryPic) {
		this.entryPic = entryPic;
	}
	
	public String getActivityName() {
		return activityName;
	}
	
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	
	public String getAreaEntryPic() {
		return areaEntryPic;
	}
	
	public void setAreaEntryPic(String areaEntryPic) {
		this.areaEntryPic = areaEntryPic;
	}
	
	public String getActivityAreaName() {
		return activityAreaName;
	}
	
	public void setActivityAreaName(String activityAreaName) {
		this.activityAreaName = activityAreaName;
	}
	
	public Date getActivityEndTime() {
		return activityEndTime;
	}
	
	public void setActivityEndTime(Date activityEndTime) {
		this.activityEndTime = activityEndTime;
	}
	
	public String getSource() {
		return source;
	}
	
	public void setSource(String source) {
		this.source = source;
	}

	public Integer getActivityAreaId() {
		return activityAreaId;
	}
	

	public void setActivityAreaId(Integer activityAreaId) {
		this.activityAreaId = activityAreaId;
	}
	

	public String getBenefitPoint() {
		return benefitPoint;
	}
	

	public void setBenefitPoint(String benefitPoint) {
		this.benefitPoint = benefitPoint;
	}

	public Date getActivityBeginTime() {
		return activityBeginTime;
	}
	

	public void setActivityBeginTime(Date activityBeginTime) {
		this.activityBeginTime = activityBeginTime;
	}
	
	
	
	
}
