package com.jf.entity;

import java.math.BigDecimal;
import java.util.Date;

public class MchtStatisticalInfo {
    private Integer id;

    private Integer mchtId;

    private Integer activityCount7Days;

    private BigDecimal payAmountSum7Days;

    private Integer subOrderCount7Days;

    private BigDecimal returnRate7Days;

    private BigDecimal appealRate7Days;

    private BigDecimal interventionRate7Days;

    private Integer activityCount30Days;

    private BigDecimal payAmountSum30Days;

    private Integer subOrderCount30Days;

    private BigDecimal returnRate30Days;

    private BigDecimal appealRate30Days;

    private BigDecimal interventionRate30Days;

    private Integer activityCount90Days;

    private BigDecimal payAmountSum90Days;

    private Integer subOrderCount90Days;

    private BigDecimal returnRate90Days;

    private BigDecimal appealRate90Days;

    private BigDecimal interventionRate90Days;

    private BigDecimal productApplauseRate;

    private BigDecimal productScoreAvg;

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

    public Integer getMchtId() {
        return mchtId;
    }

    public void setMchtId(Integer mchtId) {
        this.mchtId = mchtId;
    }

    public Integer getActivityCount7Days() {
        return activityCount7Days;
    }

    public void setActivityCount7Days(Integer activityCount7Days) {
        this.activityCount7Days = activityCount7Days;
    }

    public BigDecimal getPayAmountSum7Days() {
        return payAmountSum7Days;
    }

    public void setPayAmountSum7Days(BigDecimal payAmountSum7Days) {
        this.payAmountSum7Days = payAmountSum7Days;
    }

    public Integer getSubOrderCount7Days() {
        return subOrderCount7Days;
    }

    public void setSubOrderCount7Days(Integer subOrderCount7Days) {
        this.subOrderCount7Days = subOrderCount7Days;
    }

    public BigDecimal getReturnRate7Days() {
        return returnRate7Days;
    }

    public void setReturnRate7Days(BigDecimal returnRate7Days) {
        this.returnRate7Days = returnRate7Days;
    }

    public BigDecimal getAppealRate7Days() {
        return appealRate7Days;
    }

    public void setAppealRate7Days(BigDecimal appealRate7Days) {
        this.appealRate7Days = appealRate7Days;
    }

    public BigDecimal getInterventionRate7Days() {
        return interventionRate7Days;
    }

    public void setInterventionRate7Days(BigDecimal interventionRate7Days) {
        this.interventionRate7Days = interventionRate7Days;
    }

    public Integer getActivityCount30Days() {
        return activityCount30Days;
    }

    public void setActivityCount30Days(Integer activityCount30Days) {
        this.activityCount30Days = activityCount30Days;
    }

    public BigDecimal getPayAmountSum30Days() {
        return payAmountSum30Days;
    }

    public void setPayAmountSum30Days(BigDecimal payAmountSum30Days) {
        this.payAmountSum30Days = payAmountSum30Days;
    }

    public Integer getSubOrderCount30Days() {
        return subOrderCount30Days;
    }

    public void setSubOrderCount30Days(Integer subOrderCount30Days) {
        this.subOrderCount30Days = subOrderCount30Days;
    }

    public BigDecimal getReturnRate30Days() {
        return returnRate30Days;
    }

    public void setReturnRate30Days(BigDecimal returnRate30Days) {
        this.returnRate30Days = returnRate30Days;
    }

    public BigDecimal getAppealRate30Days() {
        return appealRate30Days;
    }

    public void setAppealRate30Days(BigDecimal appealRate30Days) {
        this.appealRate30Days = appealRate30Days;
    }

    public BigDecimal getInterventionRate30Days() {
        return interventionRate30Days;
    }

    public void setInterventionRate30Days(BigDecimal interventionRate30Days) {
        this.interventionRate30Days = interventionRate30Days;
    }

    public Integer getActivityCount90Days() {
        return activityCount90Days;
    }

    public void setActivityCount90Days(Integer activityCount90Days) {
        this.activityCount90Days = activityCount90Days;
    }

    public BigDecimal getPayAmountSum90Days() {
        return payAmountSum90Days;
    }

    public void setPayAmountSum90Days(BigDecimal payAmountSum90Days) {
        this.payAmountSum90Days = payAmountSum90Days;
    }

    public Integer getSubOrderCount90Days() {
        return subOrderCount90Days;
    }

    public void setSubOrderCount90Days(Integer subOrderCount90Days) {
        this.subOrderCount90Days = subOrderCount90Days;
    }

    public BigDecimal getReturnRate90Days() {
        return returnRate90Days;
    }

    public void setReturnRate90Days(BigDecimal returnRate90Days) {
        this.returnRate90Days = returnRate90Days;
    }

    public BigDecimal getAppealRate90Days() {
        return appealRate90Days;
    }

    public void setAppealRate90Days(BigDecimal appealRate90Days) {
        this.appealRate90Days = appealRate90Days;
    }

    public BigDecimal getInterventionRate90Days() {
        return interventionRate90Days;
    }

    public void setInterventionRate90Days(BigDecimal interventionRate90Days) {
        this.interventionRate90Days = interventionRate90Days;
    }

    public BigDecimal getProductApplauseRate() {
        return productApplauseRate;
    }

    public void setProductApplauseRate(BigDecimal productApplauseRate) {
        this.productApplauseRate = productApplauseRate;
    }

    public BigDecimal getProductScoreAvg() {
        return productScoreAvg;
    }

    public void setProductScoreAvg(BigDecimal productScoreAvg) {
        this.productScoreAvg = productScoreAvg;
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