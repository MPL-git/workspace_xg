package com.jf.entity;

/**
 * @author Pengl
 * @create 2019-12-04 下午 5:05
 */
public class SpreadActivityGroupDiscountRateCustom extends SpreadActivityGroupDiscountRate {

	private String activityGroupName;
	private String activityGroupChannelName;

	public String getActivityGroupName() {
		return activityGroupName;
	}

	public void setActivityGroupName(String activityGroupName) {
		this.activityGroupName = activityGroupName;
	}

	public String getActivityGroupChannelName() {
		return activityGroupChannelName;
	}

	public void setActivityGroupChannelName(String activityGroupChannelName) {
		this.activityGroupChannelName = activityGroupChannelName;
	}
}
