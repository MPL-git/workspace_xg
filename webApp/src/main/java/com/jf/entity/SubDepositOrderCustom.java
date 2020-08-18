package com.jf.entity;

import java.math.BigDecimal;
import java.util.Date;

public class SubDepositOrderCustom extends SubDepositOrder {

	private Integer activityId;
	private Date activityBeginTime;
	private Date activityEndTime;
	private Integer quantitySum;
	private BigDecimal depositSum;
	private BigDecimal salePriceSum;
	private BigDecimal deductAmountSum;

	public Integer getActivityId() {
		return activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	public Date getActivityBeginTime() {
		return activityBeginTime;
	}

	public void setActivityBeginTime(Date activityBeginTime) {
		this.activityBeginTime = activityBeginTime;
	}

	public Date getActivityEndTime() {
		return activityEndTime;
	}

	public void setActivityEndTime(Date activityEndTime) {
		this.activityEndTime = activityEndTime;
	}

	public Integer getQuantitySum() {
		return quantitySum;
	}

	public void setQuantitySum(Integer quantitySum) {
		this.quantitySum = quantitySum;
	}

	public BigDecimal getDepositSum() {
		return depositSum;
	}

	public void setDepositSum(BigDecimal depositSum) {
		this.depositSum = depositSum;
	}

	public BigDecimal getSalePriceSum() {
		return salePriceSum;
	}

	public void setSalePriceSum(BigDecimal salePriceSum) {
		this.salePriceSum = salePriceSum;
	}

	public BigDecimal getDeductAmountSum() {
		return deductAmountSum;
	}

	public void setDeductAmountSum(BigDecimal deductAmountSum) {
		this.deductAmountSum = deductAmountSum;
	}

}
