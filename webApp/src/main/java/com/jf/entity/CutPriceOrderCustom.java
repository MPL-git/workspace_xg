package com.jf.entity;

import java.math.BigDecimal;

/**
  * @author  chenwf: 
  * @date 创建时间：2018年8月2日 下午2:46:33 
  * @version 1.0 
  * @parameter  
  * @return  
*/
public class CutPriceOrderCustom extends CutPriceOrder{
	/**
	 * 未完全助力成功人数
	 */
	private Integer unAssistanceNum;
	/**
	 * 成功助力人数
	 */
	private Integer assistanceNum;
	/**
	 * 每个人助力的固定金额
	 */
	private BigDecimal fixedAmount;
	/**
	 * 最多邀请人数（用于助力减价）
	 */
	private Integer maxInviteTimes;
	/**
	 * 活动时长
	 */
	private Integer activeTime;
	
	public Integer getAssistanceNum() {
		return assistanceNum;
	}
	
	public void setAssistanceNum(Integer assistanceNum) {
		this.assistanceNum = assistanceNum;
	}
	
	public BigDecimal getFixedAmount() {
		return fixedAmount;
	}
	
	public void setFixedAmount(BigDecimal fixedAmount) {
		this.fixedAmount = fixedAmount;
	}
	
	public Integer getMaxInviteTimes() {
		return maxInviteTimes;
	}
	
	public void setMaxInviteTimes(Integer maxInviteTimes) {
		this.maxInviteTimes = maxInviteTimes;
	}

	public Integer getActiveTime() {
		return activeTime;
	}
	

	public void setActiveTime(Integer activeTime) {
		this.activeTime = activeTime;
	}

	public Integer getUnAssistanceNum() {
		return unAssistanceNum;
	}
	

	public void setUnAssistanceNum(Integer unAssistanceNum) {
		this.unAssistanceNum = unAssistanceNum;
	}
	
	
	
	
}
