package com.jf.entity;

import java.util.Date;

public class NodeApproverCustom extends NodeApprover{
	
	 private Date applicationCommitDate;//提审时间
	 private String procedureName;//流程名称
	 private String applicationName;//标题名称
	 private String createName;//创建人
	 private String department;//所属部门
	 
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
	 
	 
	 
	 

}
