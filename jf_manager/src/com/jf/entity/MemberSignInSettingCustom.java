package com.jf.entity;


public class MemberSignInSettingCustom extends MemberSignInSetting {
	private String rewardTypeDesc;
	
	private String rewardName;
	
	private int signInCount;

	public String getRewardTypeDesc() {
		return rewardTypeDesc;
	}

	public void setRewardTypeDesc(String rewardTypeDesc) {
		this.rewardTypeDesc = rewardTypeDesc;
	}
	
	public String getRewardName() {
		return rewardName;
	}

	public void setRewardName(String rewardName) {
		this.rewardName = rewardName;
	}

	public int getSignInCount() {
		return signInCount;
	}

	public void setSignInCount(int signInCount) {
		this.signInCount = signInCount;
	}
	
}
