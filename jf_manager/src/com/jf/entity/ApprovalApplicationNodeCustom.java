package com.jf.entity;

import java.util.Date;

public class ApprovalApplicationNodeCustom extends ApprovalApplicationNode{
	
	 private Date applicationCommitDate;//提审时间
	 private String procedureName;//流程名称
	 private String applicationName;//标题名称
	 private String createName;//创建人
	 private String department;//所属部门
	 private String nodeApproverStatus;//审核人审核状态
	 private Integer maxSeqNo;//每个流程的当前需要审核的最大顺序
	 private Integer aai;//流程Id
	 private Integer maxSeq;//最大seqNo
	 
	 
	public Date getApplicationCommitDate() {
		return applicationCommitDate;
	}
	public void setApplicationCommitDate(Date applicationCommitDate) {
		this.applicationCommitDate = applicationCommitDate;
	}
	public String getProcedureName() {
		return procedureName;
	}
	public void setProcedureName(String procedureName) {
		this.procedureName = procedureName;
	}
	public String getApplicationName() {
		return applicationName;
	}
	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}
	public String getCreateName() {
		return createName;
	}
	public void setCreateName(String createName) {
		this.createName = createName;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getNodeApproverStatus() {
		return nodeApproverStatus;
	}
	public void setNodeApproverStatus(String nodeApproverStatus) {
		this.nodeApproverStatus = nodeApproverStatus;
	}
	public Integer getMaxSeqNo() {
		return maxSeqNo;
	}
	public void setMaxSeqNo(Integer maxSeqNo) {
		this.maxSeqNo = maxSeqNo;
	}
	public Integer getAai() {
		return aai;
	}
	public void setAai(Integer aai) {
		this.aai = aai;
	}
	public Integer getMaxSeq() {
		return maxSeq;
	}
	public void setMaxSeq(Integer maxSeq) {
		this.maxSeq = maxSeq;
	}
	 
	 
	
}
