package com.jf.entity;

public class ActivityRuleConfigurationCustom extends ActivityRuleConfiguration{
	
	private String priceRulesDesc;
	
	private String salesRulesDesc;
	
	private String stockRulesDesc;
	
	private String salesCycleRulesDesc;

	public String getPriceRulesDesc() {
		return priceRulesDesc;
	}

	public void setPriceRulesDesc(String priceRulesDesc) {
		this.priceRulesDesc = priceRulesDesc;
	}

	public String getSalesRulesDesc() {
		return salesRulesDesc;
	}

	public void setSalesRulesDesc(String salesRulesDesc) {
		this.salesRulesDesc = salesRulesDesc;
	}

	public String getStockRulesDesc() {
		return stockRulesDesc;
	}

	public void setStockRulesDesc(String stockRulesDesc) {
		this.stockRulesDesc = stockRulesDesc;
	}

	public String getSalesCycleRulesDesc() {
		return salesCycleRulesDesc;
	}

	public void setSalesCycleRulesDesc(String salesCycleRulesDesc) {
		this.salesCycleRulesDesc = salesCycleRulesDesc;
	}
	
}
