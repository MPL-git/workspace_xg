package com.jf.entity;

import java.math.BigDecimal;
import java.util.Date;

public class TaskSendMemberCustom extends TaskSendMember {

	private Date memberLoginDate;
	private Integer combineOrderCount;
	private String taskSendMemeberStatusDesc;
	private String memberNick;
	private String taskContent;
	private String taskSendCount;
	private BigDecimal taskSendAmount;
	private String taskSendType;
	private String taskSendChannel;

	public Date getMemberLoginDate() {
		return memberLoginDate;
	}

	public void setMemberLoginDate(Date memberLoginDate) {
		this.memberLoginDate = memberLoginDate;
	}

	public Integer getCombineOrderCount() {
		return combineOrderCount;
	}

	public void setCombineOrderCount(Integer combineOrderCount) {
		this.combineOrderCount = combineOrderCount;
	}

	public String getTaskSendMemeberStatusDesc() {
		return taskSendMemeberStatusDesc;
	}

	public void setTaskSendMemeberStatusDesc(String taskSendMemeberStatusDesc) {
		this.taskSendMemeberStatusDesc = taskSendMemeberStatusDesc;
	}

	public String getMemberNick() {
		return memberNick;
	}

	public void setMemberNick(String memberNick) {
		this.memberNick = memberNick;
	}

	public String getTaskContent() {
		return taskContent;
	}

	public void setTaskContent(String taskContent) {
		this.taskContent = taskContent;
	}

	public String getTaskSendCount() {
		return taskSendCount;
	}

	public void setTaskSendCount(String taskSendCount) {
		this.taskSendCount = taskSendCount;
	}

	public BigDecimal getTaskSendAmount() {
		return taskSendAmount;
	}

	public void setTaskSendAmount(BigDecimal taskSendAmount) {
		this.taskSendAmount = taskSendAmount;
	}

	public String getTaskSendType() {
		return taskSendType;
	}

	public void setTaskSendType(String taskSendType) {
		this.taskSendType = taskSendType;
	}

	public String getTaskSendChannel() {
		return taskSendChannel;
	}

	public void setTaskSendChannel(String taskSendChannel) {
		this.taskSendChannel = taskSendChannel;
	}
	

	
	
}
