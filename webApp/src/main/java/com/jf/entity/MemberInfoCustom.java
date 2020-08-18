package com.jf.entity;

public class MemberInfoCustom extends MemberInfo{
	/**
	 * 我关注的数量
	 */
	private Integer followNum;
	/**
	 * 关注我的数量
	 */
	private Integer attentionMeNum;
	private Integer payOrderCount;
	private String shieldingDynamics;
	public Integer getFollowNum() {
		return followNum;
	}
	

	public void setFollowNum(Integer followNum) {
		this.followNum = followNum;
	}


	public Integer getPayOrderCount() {
		return payOrderCount;
	}
	


	public void setPayOrderCount(Integer payOrderCount) {
		this.payOrderCount = payOrderCount;
	}


	public Integer getAttentionMeNum() {
		return attentionMeNum;
	}
	


	public void setAttentionMeNum(Integer attentionMeNum) {
		this.attentionMeNum = attentionMeNum;
	}


	public String getShieldingDynamics() {
		return shieldingDynamics;
	}


	public void setShieldingDynamics(String shieldingDynamics) {
		this.shieldingDynamics = shieldingDynamics;
	}
	
	
	
	
}
