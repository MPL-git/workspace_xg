package com.jf.entity;

public class MemberSupplementarySignInCustom extends MemberSupplementarySignIn{
	
	private int supplementaryInvitationCount;
	
	private int supplementaryCardCount;
	
	private int supplementaryCard;
	
	private Integer blackListId;

	public int getSupplementaryInvitationCount() {
		return supplementaryInvitationCount;
	}

	public void setSupplementaryInvitationCount(int supplementaryInvitationCount) {
		this.supplementaryInvitationCount = supplementaryInvitationCount;
	}

	public int getSupplementaryCardCount() {
		return supplementaryCardCount;
	}

	public void setSupplementaryCardCount(int supplementaryCardCount) {
		this.supplementaryCardCount = supplementaryCardCount;
	}

	public int getSupplementaryCard() {
		return supplementaryCard;
	}

	public void setSupplementaryCard(int supplementaryCard) {
		this.supplementaryCard = supplementaryCard;
	}

	public Integer getBlackListId() {
		return blackListId;
	}

	public void setBlackListId(Integer blackListId) {
		this.blackListId = blackListId;
	}
	
}