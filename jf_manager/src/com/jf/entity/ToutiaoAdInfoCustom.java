package com.jf.entity;

public class ToutiaoAdInfoCustom extends ToutiaoAdInfoWithBLOBs {

	private String advertiserName;
	private String campaignName;
	private String budgetModeDesc;
	private String statusDesc;
	private String pricingDesc;
	private String inventoryType;

	public String getAdvertiserName() {
		return advertiserName;
	}

	public void setAdvertiserName(String advertiserName) {
		this.advertiserName = advertiserName;
	}

	public String getCampaignName() {
		return campaignName;
	}

	public void setCampaignName(String campaignName) {
		this.campaignName = campaignName;
	}

	public String getBudgetModeDesc() {
		return budgetModeDesc;
	}

	public void setBudgetModeDesc(String budgetModeDesc) {
		this.budgetModeDesc = budgetModeDesc;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public String getInventoryType() {
		return inventoryType;
	}

	public void setInventoryType(String inventoryType) {
		this.inventoryType = inventoryType;
	}

	public String getPricingDesc() {
		return pricingDesc;
	}

	public void setPricingDesc(String pricingDesc) {
		this.pricingDesc = pricingDesc;
	}

}
