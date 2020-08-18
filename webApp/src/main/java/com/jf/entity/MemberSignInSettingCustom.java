package com.jf.entity;

import java.util.Date;

public class MemberSignInSettingCustom extends MemberSignInSetting{
	/**
	 * 奖励名称
	 */
	private String rewardName;
	/**
	 * 奖励类型
	 */
	private String rewardType;
	/**
	 * 奖励礼物
	 */
	private String rewardGift;
	/**
	 * 会员签到数量
	 */
	private Integer memberSignInCount;
	/**
	 * 会员签到数量
	 */
	private Date signInDate;
	/**
	 * 签到管理id
	 */
	private Integer signInManageId;
	/**
	 * 会员昵称
	 */
	private String nick;
	/**
	 * 会员手机号码
	 */
	private String mobile;
	
	public String getRewardName() {
		return rewardName;
	}
	
	public void setRewardName(String rewardName) {
		this.rewardName = rewardName;
	}
	
	public String getRewardType() {
		return rewardType;
	}
	
	public void setRewardType(String rewardType) {
		this.rewardType = rewardType;
	}
	
	public String getRewardGift() {
		return rewardGift;
	}
	
	public void setRewardGift(String rewardGift) {
		this.rewardGift = rewardGift;
	}
	
	public Integer getMemberSignInCount() {
		return memberSignInCount;
	}
	
	public void setMemberSignInCount(Integer memberSignInCount) {
		this.memberSignInCount = memberSignInCount;
	}

	public Date getSignInDate() {
		return signInDate;
	}
	

	public void setSignInDate(Date signInDate) {
		this.signInDate = signInDate;
	}

	public Integer getSignInManageId() {
		return signInManageId;
	}
	

	public void setSignInManageId(Integer signInManageId) {
		this.signInManageId = signInManageId;
	}

	public String getNick() {
		return nick;
	}
	

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getMobile() {
		return mobile;
	}
	

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
}
