package com.jf.entity;

import java.math.BigDecimal;
import java.util.Date;

public class ToutiaoAdInfo {
    private Integer id;

    private String advertiserId;

    private String campaignId;

    private String adId;

    private String name;

    private String modifyTime;

    private String adModifyTime;

    private String adCreateTime;

    private String budgetMode;

    private BigDecimal budget;

    private Integer hideIfExists;

    private String status;

    private String optStatus;

    private String startTime;

    private String endTime;

    private BigDecimal bid;

    private String pricing;

    private String scheduleType;

    private String flowControlMode;

    private String openUrl;

    private String downloadType;

    private String externalUrl;

    private String downloadUrl;

    private String appType;

    private String adPackage;

    private String auditRejectReason;

    private BigDecimal cpaBid;

    private Integer cpaSkipFirstPhrase;

    private String convertId;

    private String hideIfConverted;

    private String pageInfo;

    private Integer batchCode;

    private Integer createBy;

    private Date createDate;

    private Integer updateBy;

    private Date updateDate;

    private String remarks;

    private String delFlag;

    private String deliveryRange;

    private String unionVideoType;

    private String deepBidType;

    private BigDecimal deepCpabid;

    private String uniqueFk;

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

    public String getAdId() {
        return adId;
    }

    public void setAdId(String adId) {
        this.adId = adId == null ? null : adId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime == null ? null : modifyTime.trim();
    }

    public String getAdModifyTime() {
        return adModifyTime;
    }

    public void setAdModifyTime(String adModifyTime) {
        this.adModifyTime = adModifyTime == null ? null : adModifyTime.trim();
    }

    public String getAdCreateTime() {
        return adCreateTime;
    }

    public void setAdCreateTime(String adCreateTime) {
        this.adCreateTime = adCreateTime == null ? null : adCreateTime.trim();
    }

    public String getBudgetMode() {
        return budgetMode;
    }

    public void setBudgetMode(String budgetMode) {
        this.budgetMode = budgetMode == null ? null : budgetMode.trim();
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }

    public Integer getHideIfExists() {
        return hideIfExists;
    }

    public void setHideIfExists(Integer hideIfExists) {
        this.hideIfExists = hideIfExists;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getOptStatus() {
        return optStatus;
    }

    public void setOptStatus(String optStatus) {
        this.optStatus = optStatus == null ? null : optStatus.trim();
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime == null ? null : startTime.trim();
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime == null ? null : endTime.trim();
    }

    public BigDecimal getBid() {
        return bid;
    }

    public void setBid(BigDecimal bid) {
        this.bid = bid;
    }

    public String getPricing() {
        return pricing;
    }

    public void setPricing(String pricing) {
        this.pricing = pricing == null ? null : pricing.trim();
    }

    public String getScheduleType() {
        return scheduleType;
    }

    public void setScheduleType(String scheduleType) {
        this.scheduleType = scheduleType == null ? null : scheduleType.trim();
    }

    public String getFlowControlMode() {
        return flowControlMode;
    }

    public void setFlowControlMode(String flowControlMode) {
        this.flowControlMode = flowControlMode == null ? null : flowControlMode.trim();
    }

    public String getOpenUrl() {
        return openUrl;
    }

    public void setOpenUrl(String openUrl) {
        this.openUrl = openUrl == null ? null : openUrl.trim();
    }

    public String getDownloadType() {
        return downloadType;
    }

    public void setDownloadType(String downloadType) {
        this.downloadType = downloadType == null ? null : downloadType.trim();
    }

    public String getExternalUrl() {
        return externalUrl;
    }

    public void setExternalUrl(String externalUrl) {
        this.externalUrl = externalUrl == null ? null : externalUrl.trim();
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl == null ? null : downloadUrl.trim();
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType == null ? null : appType.trim();
    }

    public String getAdPackage() {
        return adPackage;
    }

    public void setAdPackage(String adPackage) {
        this.adPackage = adPackage == null ? null : adPackage.trim();
    }

    public String getAuditRejectReason() {
        return auditRejectReason;
    }

    public void setAuditRejectReason(String auditRejectReason) {
        this.auditRejectReason = auditRejectReason == null ? null : auditRejectReason.trim();
    }

    public BigDecimal getCpaBid() {
        return cpaBid;
    }

    public void setCpaBid(BigDecimal cpaBid) {
        this.cpaBid = cpaBid;
    }

    public Integer getCpaSkipFirstPhrase() {
        return cpaSkipFirstPhrase;
    }

    public void setCpaSkipFirstPhrase(Integer cpaSkipFirstPhrase) {
        this.cpaSkipFirstPhrase = cpaSkipFirstPhrase;
    }

    public String getConvertId() {
        return convertId;
    }

    public void setConvertId(String convertId) {
        this.convertId = convertId == null ? null : convertId.trim();
    }

    public String getHideIfConverted() {
        return hideIfConverted;
    }

    public void setHideIfConverted(String hideIfConverted) {
        this.hideIfConverted = hideIfConverted == null ? null : hideIfConverted.trim();
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

    public String getDeliveryRange() {
        return deliveryRange;
    }

    public void setDeliveryRange(String deliveryRange) {
        this.deliveryRange = deliveryRange == null ? null : deliveryRange.trim();
    }

    public String getUnionVideoType() {
        return unionVideoType;
    }

    public void setUnionVideoType(String unionVideoType) {
        this.unionVideoType = unionVideoType == null ? null : unionVideoType.trim();
    }

    public String getDeepBidType() {
        return deepBidType;
    }

    public void setDeepBidType(String deepBidType) {
        this.deepBidType = deepBidType == null ? null : deepBidType.trim();
    }

    public BigDecimal getDeepCpabid() {
        return deepCpabid;
    }

    public void setDeepCpabid(BigDecimal deepCpabid) {
        this.deepCpabid = deepCpabid;
    }

    public String getUniqueFk() {
        return uniqueFk;
    }

    public void setUniqueFk(String uniqueFk) {
        this.uniqueFk = uniqueFk == null ? null : uniqueFk.trim();
    }
}