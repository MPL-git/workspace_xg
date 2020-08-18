package com.jf.entity;

public class ApprovalApplicationLogCustom  extends ApprovalApplicationLog{
	
	 private String createName;//创建人
	 private String department;//所属部门
	 
	 
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
