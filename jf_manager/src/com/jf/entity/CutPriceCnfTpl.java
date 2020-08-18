package com.jf.entity;

import java.math.BigDecimal;
import java.util.Date;

public class CutPriceCnfTpl {
    private Integer id;

    private String name;

    private BigDecimal beginPrice;

    private BigDecimal endPrice;

    private BigDecimal needCutToPrice;

    private BigDecimal minCutToPrice;

    private Integer maxInviteTimes;

    private Integer predictMinTime;

    private Integer predictMaxTime;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public BigDecimal getBeginPrice() {
        return beginPrice;
    }

    public void setBeginPrice(BigDecimal beginPrice) {
        this.beginPrice = beginPrice;
    }

    public BigDecimal getEndPrice() {
        return endPrice;
    }

    public void setEndPrice(BigDecimal endPrice) {
        this.endPrice = endPrice;
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

    public Integer getMaxInviteTimes() {
        return maxInviteTimes;
    }

    public void setMaxInviteTimes(Integer maxInviteTimes) {
        this.maxInviteTimes = maxInviteTimes;
    }

    public Integer getPredictMinTime() {
        return predictMinTime;
    }

    public void setPredictMinTime(Integer predictMinTime) {
        this.predictMinTime = predictMinTime;
    }

    public Integer getPredictMaxTime() {
        return predictMaxTime;
    }

    public void setPredictMaxTime(Integer predictMaxTime) {
        this.predictMaxTime = predictMaxTime;
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