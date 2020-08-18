package com.jf.entity;

import java.util.Date;

public class MchtOptimize {
    private Integer id;

    private Integer mchtId;

    private String auditRisk;

    private String degreeAdaptability;

    private String resourceGrade;

    private String operateRemarks;

    private String depositRemarks;

    private String grossProfitRateRemarks;

    private String productRemarks;

    private String serviceRemarks;

    private String wuliuRemarks;

    private String spreadRemarks;

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

    public String getAuditRisk() {
        return auditRisk;
    }

    public void setAuditRisk(String auditRisk) {
        this.auditRisk = auditRisk == null ? null : auditRisk.trim();
    }

    public String getDegreeAdaptability() {
        return degreeAdaptability;
    }

    public void setDegreeAdaptability(String degreeAdaptability) {
        this.degreeAdaptability = degreeAdaptability == null ? null : degreeAdaptability.trim();
    }

    public String getResourceGrade() {
        return resourceGrade;
    }

    public void setResourceGrade(String resourceGrade) {
        this.resourceGrade = resourceGrade == null ? null : resourceGrade.trim();
    }

    public String getOperateRemarks() {
        return operateRemarks;
    }

    public void setOperateRemarks(String operateRemarks) {
        this.operateRemarks = operateRemarks == null ? null : operateRemarks.trim();
    }

    public String getDepositRemarks() {
        return depositRemarks;
    }

    public void setDepositRemarks(String depositRemarks) {
        this.depositRemarks = depositRemarks == null ? null : depositRemarks.trim();
    }

    public String getGrossProfitRateRemarks() {
        return grossProfitRateRemarks;
    }

    public void setGrossProfitRateRemarks(String grossProfitRateRemarks) {
        this.grossProfitRateRemarks = grossProfitRateRemarks == null ? null : grossProfitRateRemarks.trim();
    }

    public String getProductRemarks() {
        return productRemarks;
    }

    public void setProductRemarks(String productRemarks) {
        this.productRemarks = productRemarks == null ? null : productRemarks.trim();
    }

    public String getServiceRemarks() {
        return serviceRemarks;
    }

    public void setServiceRemarks(String serviceRemarks) {
        this.serviceRemarks = serviceRemarks == null ? null : serviceRemarks.trim();
    }

    public String getWuliuRemarks() {
        return wuliuRemarks;
    }

    public void setWuliuRemarks(String wuliuRemarks) {
        this.wuliuRemarks = wuliuRemarks == null ? null : wuliuRemarks.trim();
    }

    public String getSpreadRemarks() {
        return spreadRemarks;
    }

    public void setSpreadRemarks(String spreadRemarks) {
        this.spreadRemarks = spreadRemarks == null ? null : spreadRemarks.trim();
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