package com.jf.entity;

/**
 * @author Pengl
 * @create 2019-12-04 下午 4:58
 */
public class AndroidChannelGroupDiscountRateCustom extends AndroidChannelGroupDiscountRate {

	private String groupName;
	private String groupPrefix;

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupPrefix() {
		return groupPrefix;
	}

	public void setGroupPrefix(String groupPrefix) {
		this.groupPrefix = groupPrefix;
	}
}
