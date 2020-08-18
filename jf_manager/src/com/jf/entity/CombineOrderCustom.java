package com.jf.entity;

import java.math.BigDecimal;
import java.util.Date;

public class CombineOrderCustom extends CombineOrder{
	private String statusDesc;
	private String paymentName;
	private String financialStatusDesc;
	private String financialStaffName;
	private String artBrandGroup;
	private String sourceClientDesc;
	private String orderTypeDesc;
	private String eachDay;
	private Integer totalCount;
	private BigDecimal combineAmount;
	private BigDecimal subOrderAmount;
	private BigDecimal wxAmount;
	private BigDecimal zfbAmount;
	private BigDecimal ylAmount;
	private BigDecimal gzhAmount;
	private Integer wxCount;
	private Integer zfbCount;
	private Integer ylCount;
	private Integer gzhCount;
	private BigDecimal confirmAmount;
	private BigDecimal registerAmount;
	private BigDecimal noDealAmount;
	private BigDecimal unusualAmount;
	private String lockDate;
	private String sprChnlDesc;
	private BigDecimal registerDateAmount;
	private String spreadname;
	private String channel;
	private String activityname;
	private String firstSpreadname;
	private Date receivedDate;
	private BigDecimal receivedAmount;
    
	private String eachDay1;
	private BigDecimal wxappAmount;
	private BigDecimal wxgzhAmount;
	private BigDecimal cdowxAmount;
	private BigDecimal cdogzhAmount;
	private BigDecimal zFbamount;
	private BigDecimal cdozfbAmount;
	private BigDecimal svipwxAmount;
	private BigDecimal svipgzhAmount;
	private BigDecimal svipzfbAmount;
	private BigDecimal zfbDesignAmount;
	private Integer combineOrderInvoiceId;
	private String combineOrderInvoiceStatus;
	private String combineOrderInvoiceRejectReason;

	public String getStatusDesc() {
		return statusDesc;
	}
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	public String getPaymentName() {
		return paymentName;
	}
	public void setPaymentName(String paymentName) {
		this.paymentName = paymentName;
	}
	public String getFinancialStatusDesc() {
		return financialStatusDesc;
	}
	public void setFinancialStatusDesc(String financialStatusDesc) {
		this.financialStatusDesc = financialStatusDesc;
	}
	public String getFinancialStaffName() {
		return financialStaffName;
	}
	public void setFinancialStaffName(String financialStaffName) {
		this.financialStaffName = financialStaffName;
	}
	public String getArtBrandGroup() {
		return artBrandGroup;
	}
	public void setArtBrandGroup(String artBrandGroup) {
		this.artBrandGroup = artBrandGroup;
	}
	public String getSourceClientDesc() {
		return sourceClientDesc;
	}
	public void setSourceClientDesc(String sourceClientDesc) {
		this.sourceClientDesc = sourceClientDesc;
	}
	public String getOrderTypeDesc() {
		return orderTypeDesc;
	}
	public void setOrderTypeDesc(String orderTypeDesc) {
		this.orderTypeDesc = orderTypeDesc;
	}
	public String getEachDay() {
		return eachDay;
	}
	public void setEachDay(String eachDay) {
		this.eachDay = eachDay;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public BigDecimal getCombineAmount() {
		return combineAmount;
	}
	public void setCombineAmount(BigDecimal combineAmount) {
		this.combineAmount = combineAmount;
	}
	public BigDecimal getSubOrderAmount() {
		return subOrderAmount;
	}
	public void setSubOrderAmount(BigDecimal subOrderAmount) {
		this.subOrderAmount = subOrderAmount;
	}
	public BigDecimal getWxAmount() {
		return wxAmount;
	}
	public void setWxAmount(BigDecimal wxAmount) {
		this.wxAmount = wxAmount;
	}
	public BigDecimal getZfbAmount() {
		return zfbAmount;
	}
	public void setZfbAmount(BigDecimal zfbAmount) {
		this.zfbAmount = zfbAmount;
	}
	public BigDecimal getYlAmount() {
		return ylAmount;
	}
	public void setYlAmount(BigDecimal ylAmount) {
		this.ylAmount = ylAmount;
	}
	public BigDecimal getGzhAmount() {
		return gzhAmount;
	}
	public void setGzhAmount(BigDecimal gzhAmount) {
		this.gzhAmount = gzhAmount;
	}
	public Integer getWxCount() {
		return wxCount;
	}
	public void setWxCount(Integer wxCount) {
		this.wxCount = wxCount;
	}
	public Integer getZfbCount() {
		return zfbCount;
	}
	public void setZfbCount(Integer zfbCount) {
		this.zfbCount = zfbCount;
	}
	public Integer getYlCount() {
		return ylCount;
	}
	public void setYlCount(Integer ylCount) {
		this.ylCount = ylCount;
	}
	public Integer getGzhCount() {
		return gzhCount;
	}
	public void setGzhCount(Integer gzhCount) {
		this.gzhCount = gzhCount;
	}
	public BigDecimal getConfirmAmount() {
		return confirmAmount;
	}
	public void setConfirmAmount(BigDecimal confirmAmount) {
		this.confirmAmount = confirmAmount;
	}
	public BigDecimal getRegisterAmount() {
		return registerAmount;
	}
	public void setRegisterAmount(BigDecimal registerAmount) {
		this.registerAmount = registerAmount;
	}
	public BigDecimal getNoDealAmount() {
		return noDealAmount;
	}
	public void setNoDealAmount(BigDecimal noDealAmount) {
		this.noDealAmount = noDealAmount;
	}
	public BigDecimal getUnusualAmount() {
		return unusualAmount;
	}
	public void setUnusualAmount(BigDecimal unusualAmount) {
		this.unusualAmount = unusualAmount;
	}
	public String getLockDate() {
		return lockDate;
	}
	public void setLockDate(String lockDate) {
		this.lockDate = lockDate;
	}
	public String getSprChnlDesc() {
		return sprChnlDesc;
	}
	public void setSprChnlDesc(String sprChnlDesc) {
		this.sprChnlDesc = sprChnlDesc;
	}
	public BigDecimal getRegisterDateAmount() {
		return registerDateAmount;
	}
	public void setRegisterDateAmount(BigDecimal registerDateAmount) {
		this.registerDateAmount = registerDateAmount;
	}
	public String getSpreadname() {
		return spreadname;
	}
	public void setSpreadname(String spreadname) {
		this.spreadname = spreadname;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getActivityname() {
		return activityname;
	}
	public void setActivityname(String activityname) {
		this.activityname = activityname;
	}
	public String getFirstSpreadname() {
		return firstSpreadname;
	}
	public void setFirstSpreadname(String firstSpreadname) {
		this.firstSpreadname = firstSpreadname;
	}
	public Date getReceivedDate() {
		return receivedDate;
	}
	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}
	public BigDecimal getReceivedAmount() {
		return receivedAmount;
	}
	public void setReceivedAmount(BigDecimal receivedAmount) {
		this.receivedAmount = receivedAmount;
	}
	public String getEachDay1() {
		return eachDay1;
	}
	public void setEachDay1(String eachDay1) {
		this.eachDay1 = eachDay1;
	}
	public BigDecimal getWxappAmount() {
		return wxappAmount;
	}
	public void setWxappAmount(BigDecimal wxappAmount) {
		this.wxappAmount = wxappAmount;
	}
	public BigDecimal getWxgzhAmount() {
		return wxgzhAmount;
	}
	public void setWxgzhAmount(BigDecimal wxgzhAmount) {
		this.wxgzhAmount = wxgzhAmount;
	}
	public BigDecimal getCdowxAmount() {
		return cdowxAmount;
	}
	public void setCdowxAmount(BigDecimal cdowxAmount) {
		this.cdowxAmount = cdowxAmount;
	}
	public BigDecimal getCdogzhAmount() {
		return cdogzhAmount;
	}
	public void setCdogzhAmount(BigDecimal cdogzhAmount) {
		this.cdogzhAmount = cdogzhAmount;
	}
	public BigDecimal getzFbamount() {
		return zFbamount;
	}
	public void setzFbamount(BigDecimal zFbamount) {
		this.zFbamount = zFbamount;
	}
	public BigDecimal getCdozfbAmount() {
		return cdozfbAmount;
	}
	public void setCdozfbAmount(BigDecimal cdozfbAmount) {
		this.cdozfbAmount = cdozfbAmount;
	}
	public BigDecimal getSvipwxAmount() {
		return svipwxAmount;
	}
	public void setSvipwxAmount(BigDecimal svipwxAmount) {
		this.svipwxAmount = svipwxAmount;
	}
	public BigDecimal getSvipgzhAmount() {
		return svipgzhAmount;
	}
	public void setSvipgzhAmount(BigDecimal svipgzhAmount) {
		this.svipgzhAmount = svipgzhAmount;
	}
	public BigDecimal getSvipzfbAmount() {
		return svipzfbAmount;
	}
	public void setSvipzfbAmount(BigDecimal svipzfbAmount) {
		this.svipzfbAmount = svipzfbAmount;
	}
	public BigDecimal getZfbDesignAmount() { return zfbDesignAmount; }
	public void setZfbDesignAmount(BigDecimal zfbDesignAmount) { this.zfbDesignAmount = zfbDesignAmount; }

	public Integer getCombineOrderInvoiceId() {
		return combineOrderInvoiceId;
	}

	public void setCombineOrderInvoiceId(Integer combineOrderInvoiceId) {
		this.combineOrderInvoiceId = combineOrderInvoiceId;
	}

	public String getCombineOrderInvoiceStatus() {
		return combineOrderInvoiceStatus;
	}

	public void setCombineOrderInvoiceStatus(String combineOrderInvoiceStatus) {
		this.combineOrderInvoiceStatus = combineOrderInvoiceStatus;
	}

	public String getCombineOrderInvoiceRejectReason() {
		return combineOrderInvoiceRejectReason;
	}

	public void setCombineOrderInvoiceRejectReason(String combineOrderInvoiceRejectReason) {
		this.combineOrderInvoiceRejectReason = combineOrderInvoiceRejectReason;
	}
}
