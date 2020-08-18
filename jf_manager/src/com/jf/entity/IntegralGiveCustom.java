package com.jf.entity;


public class IntegralGiveCustom extends IntegralGive{
	private String typeDesc;
	private String groupName;
	private String auditStatusDesc;
	private String staffName;
	private String shopName;
	private String costBearDesc;
	private String isDepositDeductDesc;
	private Integer memberCountSum;
	private Integer subOrderId;
	private String templateContent;
	private Integer violateId;
	
	public String getTypeDesc() {
		return typeDesc;
	}
	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getAuditStatusDesc() {
		return auditStatusDesc;
	}
	public void setAuditStatusDesc(String auditStatusDesc) {
		this.auditStatusDesc = auditStatusDesc;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public Integer getMemberCountSum() {
		return memberCountSum;
	}
	public void setMemberCountSum(Integer memberCountSum) {
		this.memberCountSum = memberCountSum;
	}
	public String getCostBearDesc() {
		return costBearDesc;
	}
	public void setCostBearDesc(String costBearDesc) {
		this.costBearDesc = costBearDesc;
	}
	public String getIsDepositDeductDesc() {
		return isDepositDeductDesc;
	}
	public void setIsDepositDeductDesc(String isDepositDeductDesc) {
		this.isDepositDeductDesc = isDepositDeductDesc;
	}
	public Integer getSubOrderId() {
		return subOrderId;
	}
	public void setSubOrderId(Integer subOrderId) {
		this.subOrderId = subOrderId;
	}
	public String getTemplateContent() {
		return templateContent;
	}
	public void setTemplateContent(String templateContent) {
		this.templateContent = templateContent;
	}
	public Integer getViolateId() {
		return violateId;
	}
	public void setViolateId(Integer violateId) {
		this.violateId = violateId;
	}
}
