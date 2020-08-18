package com.jf.entity;

import java.math.BigDecimal;
import java.util.Date;

public class ActivityAreaPvStatisticalCustom extends ActivityAreaPvStatistical{
	private String name;
	private String type;
	private Integer totalExposure;
	private BigDecimal inversionRate;

	private Integer paySubOrderCount;

	
	public Integer getPaySubOrderCount() {
		return paySubOrderCount;
	}
	public void setPaySubOrderCount(Integer paySubOrderCount) {
		this.paySubOrderCount = paySubOrderCount;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getTotalExposure() {
		return totalExposure;
	}
	public void setTotalExposure(Integer totalExposure) {
		this.totalExposure = totalExposure;
	}
	public BigDecimal getInversionRate() {
		return inversionRate;
	}
	public void setInversionRate(BigDecimal inversionRate) {
		this.inversionRate = inversionRate;
	}
}