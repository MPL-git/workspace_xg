package com.jf.entity;

import java.math.BigDecimal;

public class CombineDepositOrderCustom extends CombineDepositOrder{
	/**
	 * 预售子订单定金
	 */
	private BigDecimal deposit;
	/**
	 * 售后状态
	 */
	private String customerStatus;
	/**
	 * 申请售后理由
	 */
	private String reason;
	/**
	 * 申请数量
	 */
	private Integer customerQuantity;
	/**
	 * 订单明细id
	 */
	private Integer orderDtlId;
	
	public BigDecimal getDeposit() {
		return deposit;
	}
	
	public void setDeposit(BigDecimal deposit) {
		this.deposit = deposit;
	}
	
	public String getCustomerStatus() {
		return customerStatus;
	}
	
	public void setCustomerStatus(String customerStatus) {
		this.customerStatus = customerStatus;
	}
	
	public String getReason() {
		return reason;
	}
	
	public void setReason(String reason) {
		this.reason = reason;
	}

	public Integer getCustomerQuantity() {
		return customerQuantity;
	}
	

	public void setCustomerQuantity(Integer customerQuantity) {
		this.customerQuantity = customerQuantity;
	}

	public Integer getOrderDtlId() {
		return orderDtlId;
	}
	

	public void setOrderDtlId(Integer orderDtlId) {
		this.orderDtlId = orderDtlId;
	}
	
	
	
	
}
