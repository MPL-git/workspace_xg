package com.jf.entity;

/**
 * @author Pengl
 * @create 2020-03-16 下午 2:50
 */
public class DesignTaskOrderCustom extends DesignTaskOrder {

	private String taskTypeDesc;
	private String statusDesc;
	private String payStatusDesc;
	private String designStatusDesc;
	private String commentTypeDesc;
	private String designTaskRefundOrderStatus;

	public String getTaskTypeDesc() {
		return taskTypeDesc;
	}

	public void setTaskTypeDesc(String taskTypeDesc) {
		this.taskTypeDesc = taskTypeDesc;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public String getPayStatusDesc() {
		return payStatusDesc;
	}

	public void setPayStatusDesc(String payStatusDesc) {
		this.payStatusDesc = payStatusDesc;
	}

	public String getDesignStatusDesc() {
		return designStatusDesc;
	}

	public void setDesignStatusDesc(String designStatusDesc) {
		this.designStatusDesc = designStatusDesc;
	}

	public String getCommentTypeDesc() {
		return commentTypeDesc;
	}

	public void setCommentTypeDesc(String commentTypeDesc) {
		this.commentTypeDesc = commentTypeDesc;
	}

	public String getDesignTaskRefundOrderStatus() {
		return designTaskRefundOrderStatus;
	}

	public void setDesignTaskRefundOrderStatus(String designTaskRefundOrderStatus) {
		this.designTaskRefundOrderStatus = designTaskRefundOrderStatus;
	}
}
