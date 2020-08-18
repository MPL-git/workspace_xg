package com.jf.entity;

public class AssistanceDtlCustom extends AssistanceDtl{
	/**
	 * 会员微信头像
	 */
	private String weixinHead;
	/**
	 * 会员昵称
	 */
	private String nick;
	/**
	 * 已邀请人数
	 */
	private Integer invitationCount;
	/**
	 * 设置需邀请的人数
	 */
	private Integer invitationCountSetting;
	/**
	 * 会员补签任务id
	 */
	private Integer memberSupplementarySignInId;
	/**
	 * 获得的补签卡数量
	 */
	private Integer supplementaryCardCount;
	
	public String getWeixinHead() {
		return weixinHead;
	}
	
	public void setWeixinHead(String weixinHead) {
		this.weixinHead = weixinHead;
	}
	
	public String getNick() {
		return nick;
	}
	
	public void setNick(String nick) {
		this.nick = nick;
	}

	public Integer getInvitationCount() {
		return invitationCount;
	}
	

	public void setInvitationCount(Integer invitationCount) {
		this.invitationCount = invitationCount;
	}
	

	public Integer getInvitationCountSetting() {
		return invitationCountSetting;
	}
	

	public void setInvitationCountSetting(Integer invitationCountSetting) {
		this.invitationCountSetting = invitationCountSetting;
	}

	public Integer getMemberSupplementarySignInId() {
		return memberSupplementarySignInId;
	}
	

	public void setMemberSupplementarySignInId(Integer memberSupplementarySignInId) {
		this.memberSupplementarySignInId = memberSupplementarySignInId;
	}

	public Integer getSupplementaryCardCount() {
		return supplementaryCardCount;
	}
	

	public void setSupplementaryCardCount(Integer supplementaryCardCount) {
		this.supplementaryCardCount = supplementaryCardCount;
	}
	
	
}
