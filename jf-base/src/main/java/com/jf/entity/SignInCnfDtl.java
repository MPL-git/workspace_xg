package com.jf.entity;

import java.math.BigDecimal;
import java.util.Date;

public class SignInCnfDtl {
    private Integer id;

    private Integer signInCnfId;

    private String rateType;

    private Integer accumulationDay;

    private BigDecimal beginAmount;

    private BigDecimal endAmount;

    private BigDecimal beginRate;

    private BigDecimal endRate;

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

    public Integer getSignInCnfId() {
        return signInCnfId;
    }

    public void setSignInCnfId(Integer signInCnfId) {
        this.signInCnfId = signInCnfId;
    }

    public String getRateType() {
        return rateType;
    }

    public void setRateType(String rateType) {
        this.rateType = rateType == null ? null : rateType.trim();
    }

    public Integer getAccumulationDay() {
        return accumulationDay;
    }

    public void setAccumulationDay(Integer accumulationDay) {
        this.accumulationDay = accumulationDay;
    }

    public BigDecimal getBeginAmount() {
        return beginAmount;
    }

    public void setBeginAmount(BigDecimal beginAmount) {
        this.beginAmount = beginAmount;
    }

    public BigDecimal getEndAmount() {
        return endAmount;
    }

    public void setEndAmount(BigDecimal endAmount) {
        this.endAmount = endAmount;
    }

    public BigDecimal getBeginRate() {
        return beginRate;
    }

    public void setBeginRate(BigDecimal beginRate) {
        this.beginRate = beginRate;
    }

    public BigDecimal getEndRate() {
        return endRate;
    }

    public void setEndRate(BigDecimal endRate) {
        this.endRate = endRate;
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