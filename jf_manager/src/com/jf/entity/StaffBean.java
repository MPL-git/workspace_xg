package com.jf.entity;

import java.io.Serializable;

import com.gzs.common.beans.Organization;

public class StaffBean implements Serializable {
	
	private static final long serialVersionUID = -7101545690685822919L;
	
	private String staffID;
	private String staffCode;
	private String staffName;
	private String staffPass;
	private String mobilePhone;
	private String orgId;
	private String isManagement;
	private String roleId;
	private Organization organization;

	public String getStaffID() {
		return staffID;
	}
	public void setStaffID(String staffID) {
		this.staffID = staffID;
	}
	public String getStaffCode() {
		return staffCode;
	}
	public void setStaffCode(String staffCode) {
		this.staffCode = staffCode;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public String getStaffPass() {
		return staffPass;
	}
	public void setStaffPass(String staffPass) {
		this.staffPass = staffPass;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public Organization getOrganization() {
		return organization;
	}
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getIsManagement() {
		return isManagement;
	}
	public void setIsManagement(String isManagement) {
		this.isManagement = isManagement;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
}
