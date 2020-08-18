package com.jf.entity;

import java.math.BigDecimal;

public class OperateData {
	
	private Integer productType1Id;
	
	private String payDate;
	
	private BigDecimal payAmount;
	
	private BigDecimal settleAmount;
	
	private BigDecimal allPayAmount;

	public Integer getProductType1Id() {
		return productType1Id;
	}

	public void setProductType1Id(Integer productType1Id) {
		this.productType1Id = productType1Id;
	}

	public String getPayDate() {
		return payDate;
	}

	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}

	public BigDecimal getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}

	public BigDecimal getSettleAmount() {
		return settleAmount;
	}

	public void setSettleAmount(BigDecimal settleAmount) {
		this.settleAmount = settleAmount;
	}

	public BigDecimal getAllPayAmount() {
		return allPayAmount;
	}

	public void setAllPayAmount(BigDecimal allPayAmount) {
		this.allPayAmount = allPayAmount;
	}
	
}
