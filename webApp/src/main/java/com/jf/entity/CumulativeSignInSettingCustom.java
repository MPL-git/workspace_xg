package com.jf.entity;

public class CumulativeSignInSettingCustom extends CumulativeSignInSetting{
	/**
	 * 会员累签奖励数量
	 */
	private Integer membeCumulativeSignInCount;

	public Integer getMembeCumulativeSignInCount() {
		return membeCumulativeSignInCount;
	}
	

	public void setMembeCumulativeSignInCount(Integer membeCumulativeSignInCount) {
		this.membeCumulativeSignInCount = membeCumulativeSignInCount;
	}
}
