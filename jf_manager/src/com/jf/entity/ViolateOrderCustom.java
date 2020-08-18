package com.jf.entity;

import java.math.BigDecimal;
import java.util.Date;

public class ViolateOrderCustom extends ViolateOrder {
	private String violateTypeDesc;
	private String violateActionDesc;
	private String belongActivity;
	private String statusDesc;
	private String auditStatusDesc;
	private String orderSourceDesc;
	private String subOrderCode;
	private String mchtCode;
	private String companyName;
	private String shopName;
	private String staffName;
	private String createName;
	private String violateComplainOrderStatus;
	private Date complainTime;
	private String complainProcesBy;
	private Date payDate;
	private String expressName;
	private String expressNo;
	private Date deliveryDate;
	private BigDecimal payAmt; // 商家保证金余额
	private String againAuditStatusDesc;

	public String getViolateTypeDesc() {
		return violateTypeDesc;
	}

	public void setViolateTypeDesc(String violateTypeDesc) {
		this.violateTypeDesc = violateTypeDesc;
	}

	public String getBelongActivity() {
		return belongActivity;
	}

	public void setBelongActivity(String belongActivity) {
		this.belongActivity = belongActivity;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public String getAuditStatusDesc() {
		return auditStatusDesc;
	}

	public void setAuditStatusDesc(String auditStatusDesc) {
		this.auditStatusDesc = auditStatusDesc;
	}

	public String getOrderSourceDesc() {
		return orderSourceDesc;
	}

	public void setOrderSourceDesc(String orderSourceDesc) {
		this.orderSourceDesc = orderSourceDesc;
	}

	public String getSubOrderCode() {
		return subOrderCode;
	}

	public void setSubOrderCode(String subOrderCode) {
		this.subOrderCode = subOrderCode;
	}

	public String getMchtCode() {
		return mchtCode;
	}

	public void setMchtCode(String mchtCode) {
		this.mchtCode = mchtCode;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	public String getViolateComplainOrderStatus() {
		return violateComplainOrderStatus;
	}

	public void setViolateComplainOrderStatus(String violateComplainOrderStatus) {
		this.violateComplainOrderStatus = violateComplainOrderStatus;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public Date getComplainTime() {
		return complainTime;
	}

	public void setComplainTime(Date complainTime) {
		this.complainTime = complainTime;
	}

	public String getViolateActionDesc() {
		return violateActionDesc;
	}

	public void setViolateActionDesc(String violateActionDesc) {
		this.violateActionDesc = violateActionDesc;
	}

	public String getComplainProcesBy() {
		return complainProcesBy;
	}

	public void setComplainProcesBy(String complainProcesBy) {
		this.complainProcesBy = complainProcesBy;
	}

	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	public String getExpressName() {
		return expressName;
	}

	public void setExpressName(String expressName) {
		this.expressName = expressName;
	}

	public String getExpressNo() {
		return expressNo;
	}

	public void setExpressNo(String expressNo) {
		this.expressNo = expressNo;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public BigDecimal getPayAmt() {
		return payAmt;
	}

	public void setPayAmt(BigDecimal payAmt) {
		this.payAmt = payAmt;
	}

	public String getAgainAuditStatusDesc() {
		return againAuditStatusDesc;
	}

	public void setAgainAuditStatusDesc(String againAuditStatusDesc) {
		this.againAuditStatusDesc = againAuditStatusDesc;
	}

}
