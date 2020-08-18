package com.jf.entity;

public class SingleProductActivityAuditLogCustom extends SingleProductActivityAuditLog {

    private String createName;

    private String updateName;

	public SingleProductActivityAuditLogCustom() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SingleProductActivityAuditLogCustom(String createName,
			String updateName) {
		super();
		this.createName = createName;
		this.updateName = updateName;
	}

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	public String getUpdateName() {
		return updateName;
	}

	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}

}