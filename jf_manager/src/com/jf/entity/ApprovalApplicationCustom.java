package com.jf.entity;

public class ApprovalApplicationCustom extends ApprovalApplication {
	
	private String procedureName;//流程名称
	 private String createName;//创建人
	 private String department;//所属部门
	

	public String getProcedureName() {
		return procedureName;
	}

	public void setProcedureName(String procedureName) {
		this.procedureName = procedureName;
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
