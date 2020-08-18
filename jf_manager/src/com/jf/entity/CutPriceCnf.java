package com.jf.entity;

import java.math.BigDecimal;
import java.util.Date;

public class CutPriceCnf {
    private Integer id;

    private Integer singleProductActivityId;

    private BigDecimal needCutToPrice;

    private BigDecimal minCutToPrice;

    private Integer inviteTimes;

    private Integer maxInviteTimes;

    private Integer predictMinTimes;

    private Integer predictMaxTimes;

    private Integer activeTime;

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

    public Integer getSingleProductActivityId() {
        return singleProductActivityId;
    }

    public void setSingleProductActivityId(Integer singleProductActivityId) {
        this.singleProductActivityId = singleProductActivityId;
    }

    public BigDecimal getNeedCutToPrice() {
        return needCutToPrice;
    }

    public void setNeedCutToPrice(BigDecimal needCutToPrice) {
        this.needCutToPrice = needCutToPrice;
    }

    public BigDecimal getMinCutToPrice() {
        return minCutToPrice;
    }

    public void setMinCutToPrice(BigDecimal minCutToPrice) {
        this.minCutToPrice = minCutToPrice;
    }

    public Integer getInviteTimes() {
        return inviteTimes;
    }

    public void setInviteTimes(Integer inviteTimes) {
        this.inviteTimes = inviteTimes;
    }

    public Integer getMaxInviteTimes() {
        return maxInviteTimes;
    }

    public void setMaxInviteTimes(Integer maxInviteTimes) {
        this.maxInviteTimes = maxInviteTimes;
    }

    public Integer getPredictMinTimes() {
        return predictMinTimes;
    }

    public void setPredictMinTimes(Integer predictMinTimes) {
        this.predictMinTimes = predictMinTimes;
    }

    public Integer getPredictMaxTimes() {
        return predictMaxTimes;
    }

    public void setPredictMaxTimes(Integer predictMaxTimes) {
        this.predictMaxTimes = predictMaxTimes;
    }

    public Integer getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(Integer activeTime) {
        this.activeTime = activeTime;
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