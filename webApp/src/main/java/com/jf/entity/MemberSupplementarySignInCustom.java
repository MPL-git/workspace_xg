package com.jf.entity;

public class MemberSupplementarySignInCustom extends MemberSupplementarySignIn{
	/**
	 * 设置的邀请人数
	 */
	 private Integer settingInvitationCount;
	 /**
	  * 设置的补签卡赠送数量
	  */
	 private Integer supplementaryCardCount;

	public Integer getSettingInvitationCount() {
		return settingInvitationCount;
	}
	

	public void setSettingInvitationCount(Integer settingInvitationCount) {
		this.settingInvitationCount = settingInvitationCount;
	}


	public Integer getSupplementaryCardCount() {
		return supplementaryCardCount;
	}
	


	public void setSupplementaryCardCount(Integer supplementaryCardCount) {
		this.supplementaryCardCount = supplementaryCardCount;
	}
	
	
	 
}	
