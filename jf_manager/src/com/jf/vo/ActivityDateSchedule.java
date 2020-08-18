package com.jf.vo;

import java.util.Date;

public class ActivityDateSchedule {
	
	private Integer weekSeveral;//星期几
	
	private String month;//月份
	
	private String day;//那一天
	
	private Integer activityNum;//活动中
	
	private Integer dayStart;//当天开始
	
	private Integer endDay;//当天结束
	
	private Date time;//时间
	
	private Date dateTime;

	public Integer getWeekSeveral() {
		return weekSeveral;
	}

	public void setWeekSeveral(Integer weekSeveral) {
		this.weekSeveral = weekSeveral;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public Integer getActivityNum() {
		return activityNum;
	}

	public void setActivityNum(Integer activityNum) {
		this.activityNum = activityNum;
	}

	public Integer getDayStart() {
		return dayStart;
	}

	public void setDayStart(Integer dayStart) {
		this.dayStart = dayStart;
	}

	public Integer getEndDay() {
		return endDay;
	}

	public void setEndDay(Integer endDay) {
		this.endDay = endDay;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
	
}
