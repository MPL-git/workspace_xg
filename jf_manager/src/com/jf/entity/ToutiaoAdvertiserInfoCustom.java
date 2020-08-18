package com.jf.entity;

import java.util.Date;

public class ToutiaoAdvertiserInfoCustom extends ToutiaoAdvertiserInfo {

	private String roleDesc;
	private String statusDesc;
	private Integer campaignBatchCode;
	private Date campaignUpdateDate;
	private Integer adBatchCode;
	private Date adUpdateDate;

	public Integer getCampaignBatchCode() {
		return campaignBatchCode;
	}

	public void setCampaignBatchCode(Integer campaignBatchCode) {
		this.campaignBatchCode = campaignBatchCode;
	}

	public Date getCampaignUpdateDate() {
		return campaignUpdateDate;
	}

	public void setCampaignUpdateDate(Date campaignUpdateDate) {
		this.campaignUpdateDate = campaignUpdateDate;
	}

	public Integer getAdBatchCode() {
		return adBatchCode;
	}

	public void setAdBatchCode(Integer adBatchCode) {
		this.adBatchCode = adBatchCode;
	}

	public Date getAdUpdateDate() {
		return adUpdateDate;
	}

	public void setAdUpdateDate(Date adUpdateDate) {
		this.adUpdateDate = adUpdateDate;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

}
