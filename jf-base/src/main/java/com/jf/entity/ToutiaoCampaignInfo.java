package com.jf.entity;

import java.math.BigDecimal;
import java.util.Date;

public class ToutiaoCampaignInfo {
    private Integer id;

    private String advertiserId;

    private String campaignId;

    private String name;

    private BigDecimal budget;

    private String budgetMode;

    private String landingType;

    private String modifyTime;

    private String status;

    private String campaignCreateTime;

    private String campaignModifyTime;

    private String pageInfo;

    private Integer batchCode;

    private Integer createBy;

    private Date createDate;

    private Integer updateBy;

    private Date updateDate;

    private String remarks;

    private String delFlag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAdvertiserId() {
        return advertiserId;
    }

    public void setAdvertiserId(String advertiserId) {
        this.advertiserId = advertiserId == null ? null : advertiserId.trim();
    }

    public String getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(String campaignId) {
        this.campaignId = campaignId == null ? null : campaignId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }

    public String getBudgetMode() {
        return budgetMode;
    }

    public void setBudgetMode(String budgetMode) {
        this.budgetMode = budgetMode == null ? null : budgetMode.trim();
    }

    public String getLandingType() {
        return landingType;
    }

    public void setLandingType(String landingType) {
        this.landingType = landingType == null ? null : landingType.trim();
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime == null ? null : modifyTime.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getCampaignCreateTime() {
        return campaignCreateTime;
    }

    public void setCampaignCreateTime(String campaignCreateTime) {
        this.campaignCreateTime = campaignCreateTime == null ? null : campaignCreateTime.trim();
    }

    public String getCampaignModifyTime() {
        return campaignModifyTime;
    }

    public void setCampaignModifyTime(String campaignModifyTime) {
        this.campaignModifyTime = campaignModifyTime == null ? null : campaignModifyTime.trim();
    }

    public String getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(String pageInfo) {
        this.pageInfo = pageInfo == null ? null : pageInfo.trim();
    }

    public Integer getBatchCode() {
        return batchCode;
    }

    public void setBatchCode(Integer batchCode) {
        this.batchCode = batchCode;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : delFlag.trim();
    }
}