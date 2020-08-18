package com.jf.entity;

import java.math.BigDecimal;


public class RefundOrderCustom extends RefundOrder{
	private String combineOrderCode;
	private String combineDepositOrderCode;
	private BigDecimal totalPayAmount;
	private BigDecimal totalDeposit;
	private String combineOrderStatusDesc;
	private String combineDepositOrderStatusDesc;
	private String paymentName;
	private String depositPaymentName;
	private String paymentNo;
	private String depositPaymentNo;
	private String serviceTypeDesc;
	private String statusDesc;
	private String refundStaffName;
	private String refundMethodDesc;
	private String eachDay;
	private Integer totalCount;
	private BigDecimal refundAmount;
	private BigDecimal wxAmount;
	private BigDecimal zfbAmount;
	private BigDecimal ylAmount;
	private BigDecimal gzhAmount;
	private BigDecimal otherAmount;
	private Integer wxCount;
	private Integer zfbCount;
	private Integer ylCount;
	private Integer gzhCount;
	private Integer otherCount;
	private BigDecimal confirmAmount;
	private String lockDate;
	private BigDecimal registerAmount;
	private BigDecimal noDealAmount;
	private BigDecimal unusualAmount;
	private String customerServiceOrderCode;
	private String subOrderCode;
	private Integer orderDtlId;
	
	private String eachDay1;
	private BigDecimal rowxappAmount;
	private BigDecimal rowxgzhAmount;
	private BigDecimal rocdowxAmount;
	private BigDecimal rocdogzhAmount;
	private BigDecimal rozFbAmount;
	private BigDecimal rocdozfbAmount;
	private BigDecimal rovipwxAmount;
	private BigDecimal rosvipgzhAmount;
	private BigDecimal rosvipzfbAmount;
	private BigDecimal wxrAmount;
	
	public String getCombineOrderCode() {
		return combineOrderCode;
	}
	public void setCombineOrderCode(String combineOrderCode) {
		this.combineOrderCode = combineOrderCode;
	}
	public BigDecimal getTotalPayAmount() {
		return totalPayAmount;
	}
	public void setTotalPayAmount(BigDecimal totalPayAmount) {
		this.totalPayAmount = totalPayAmount;
	}
	public String getCombineOrderStatusDesc() {
		return combineOrderStatusDesc;
	}
	public void setCombineOrderStatusDesc(String combineOrderStatusDesc) {
		this.combineOrderStatusDesc = combineOrderStatusDesc;
	}
	public String getPaymentName() {
		return paymentName;
	}
	public void setPaymentName(String paymentName) {
		this.paymentName = paymentName;
	}
	public String getPaymentNo() {
		return paymentNo;
	}
	public void setPaymentNo(String paymentNo) {
		this.paymentNo = paymentNo;
	}
	public String getServiceTypeDesc() {
		return serviceTypeDesc;
	}
	public void setServiceTypeDesc(String serviceTypeDesc) {
		this.serviceTypeDesc = serviceTypeDesc;
	}
	public String getStatusDesc() {
		return statusDesc;
	}
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	public String getRefundStaffName() {
		return refundStaffName;
	}
	public void setRefundStaffName(String refundStaffName) {
		this.refundStaffName = refundStaffName;
	}
	public String getRefundMethodDesc() {
		return refundMethodDesc;
	}
	public void setRefundMethodDesc(String refundMethodDesc) {
		this.refundMethodDesc = refundMethodDesc;
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
	public BigDecimal getRefundAmount() {
		return refundAmount;
	}
	public void setRefundAmount(BigDecimal refundAmount) {
		this.refundAmount = refundAmount;
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
	public BigDecimal getOtherAmount() {
		return otherAmount;
	}
	public void setOtherAmount(BigDecimal otherAmount) {
		this.otherAmount = otherAmount;
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
	public Integer getOtherCount() {
		return otherCount;
	}
	public void setOtherCount(Integer otherCount) {
		this.otherCount = otherCount;
	}
	public BigDecimal getConfirmAmount() {
		return confirmAmount;
	}
	public void setConfirmAmount(BigDecimal confirmAmount) {
		this.confirmAmount = confirmAmount;
	}
	public String getLockDate() {
		return lockDate;
	}
	public void setLockDate(String lockDate) {
		this.lockDate = lockDate;
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
	public String getCustomerServiceOrderCode() {
		return customerServiceOrderCode;
	}
	public void setCustomerServiceOrderCode(String customerServiceOrderCode) {
		this.customerServiceOrderCode = customerServiceOrderCode;
	}
	public String getSubOrderCode() {
		return subOrderCode;
	}
	public void setSubOrderCode(String subOrderCode) {
		this.subOrderCode = subOrderCode;
	}
	public Integer getOrderDtlId() {
		return orderDtlId;
	}
	public void setOrderDtlId(Integer orderDtlId) {
		this.orderDtlId = orderDtlId;
	}
	public String getCombineDepositOrderCode() {
		return combineDepositOrderCode;
	}
	public void setCombineDepositOrderCode(String combineDepositOrderCode) {
		this.combineDepositOrderCode = combineDepositOrderCode;
	}
	public BigDecimal getTotalDeposit() {
		return totalDeposit;
	}
	public void setTotalDeposit(BigDecimal totalDeposit) {
		this.totalDeposit = totalDeposit;
	}
	public String getCombineDepositOrderStatusDesc() {
		return combineDepositOrderStatusDesc;
	}
	public void setCombineDepositOrderStatusDesc(
			String combineDepositOrderStatusDesc) {
		this.combineDepositOrderStatusDesc = combineDepositOrderStatusDesc;
	}
	public String getDepositPaymentName() {
		return depositPaymentName;
	}
	public void setDepositPaymentName(String depositPaymentName) {
		this.depositPaymentName = depositPaymentName;
	}
	public String getDepositPaymentNo() {
		return depositPaymentNo;
	}
	public void setDepositPaymentNo(String depositPaymentNo) {
		this.depositPaymentNo = depositPaymentNo;
	}
	public String getEachDay1() {
		return eachDay1;
	}
	public void setEachDay1(String eachDay1) {
		this.eachDay1 = eachDay1;
	}
	public BigDecimal getRowxappAmount() {
		return rowxappAmount;
	}
	public void setRowxappAmount(BigDecimal rowxappAmount) {
		this.rowxappAmount = rowxappAmount;
	}
	public BigDecimal getRowxgzhAmount() {
		return rowxgzhAmount;
	}
	public void setRowxgzhAmount(BigDecimal rowxgzhAmount) {
		this.rowxgzhAmount = rowxgzhAmount;
	}
	public BigDecimal getRocdowxAmount() {
		return rocdowxAmount;
	}
	public void setRocdowxAmount(BigDecimal rocdowxAmount) {
		this.rocdowxAmount = rocdowxAmount;
	}
	public BigDecimal getRocdogzhAmount() {
		return rocdogzhAmount;
	}
	public void setRocdogzhAmount(BigDecimal rocdogzhAmount) {
		this.rocdogzhAmount = rocdogzhAmount;
	}
	public BigDecimal getRozFbAmount() {
		return rozFbAmount;
	}
	public void setRozFbAmount(BigDecimal rozFbAmount) {
		this.rozFbAmount = rozFbAmount;
	}
	public BigDecimal getRocdozfbAmount() {
		return rocdozfbAmount;
	}
	public void setRocdozfbAmount(BigDecimal rocdozfbAmount) {
		this.rocdozfbAmount = rocdozfbAmount;
	}
	public BigDecimal getRovipwxAmount() {
		return rovipwxAmount;
	}
	public void setRovipwxAmount(BigDecimal rovipwxAmount) {
		this.rovipwxAmount = rovipwxAmount;
	}
	public BigDecimal getRosvipgzhAmount() {
		return rosvipgzhAmount;
	}
	public void setRosvipgzhAmount(BigDecimal rosvipgzhAmount) {
		this.rosvipgzhAmount = rosvipgzhAmount;
	}
	public BigDecimal getRosvipzfbAmount() {
		return rosvipzfbAmount;
	}
	public void setRosvipzfbAmount(BigDecimal rosvipzfbAmount) {
		this.rosvipzfbAmount = rosvipzfbAmount;
	}
	public BigDecimal getWxrAmount() {
		return wxrAmount;
	}
	public void setWxrAmount(BigDecimal wxrAmount) {
		this.wxrAmount = wxrAmount;
	}
	
}
