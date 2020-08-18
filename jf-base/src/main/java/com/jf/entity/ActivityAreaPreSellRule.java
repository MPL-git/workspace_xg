package com.jf.entity;

import java.math.BigDecimal;
import java.util.Date;

public class ActivityAreaPreSellRule {
    private Integer id;

    private Integer activityAreaId;

    private BigDecimal activityPriceLimit;

    private BigDecimal depositLimit;

    private Integer minRate;

    private Integer maxRate;

    private BigDecimal offsetMultiple;

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

    public Integer getActivityAreaId() {
        return activityAreaId;
    }

    public void setActivityAreaId(Integer activityAreaId) {
        this.activityAreaId = activityAreaId;
    }

    public BigDecimal getActivityPriceLimit() {
        return activityPriceLimit;
    }

    public void setActivityPriceLimit(BigDecimal activityPriceLimit) {
        this.activityPriceLimit = activityPriceLimit;
    }

    public BigDecimal getDepositLimit() {
        return depositLimit;
    }

    public void setDepositLimit(BigDecimal depositLimit) {
        this.depositLimit = depositLimit;
    }

    public Integer getMinRate() {
        return minRate;
    }

    public void setMinRate(Integer minRate) {
        this.minRate = minRate;
    }

    public Integer getMaxRate() {
        return maxRate;
    }

    public void setMaxRate(Integer maxRate) {
        this.maxRate = maxRate;
    }

    public BigDecimal getOffsetMultiple() {
        return offsetMultiple;
    }

    public void setOffsetMultiple(BigDecimal offsetMultiple) {
        this.offsetMultiple = offsetMultiple;
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