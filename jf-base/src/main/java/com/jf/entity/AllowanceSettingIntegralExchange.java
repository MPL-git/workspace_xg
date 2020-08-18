package com.jf.entity;

import java.math.BigDecimal;
import java.util.Date;

public class AllowanceSettingIntegralExchange {
    private Integer id;

    private Integer allowanceId;

    private Integer integral;

    private BigDecimal exchangeAmountMin;

    private BigDecimal exchangeAmountMax;

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

    public Integer getAllowanceId() {
        return allowanceId;
    }

    public void setAllowanceId(Integer allowanceId) {
        this.allowanceId = allowanceId;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public BigDecimal getExchangeAmountMin() {
        return exchangeAmountMin;
    }

    public void setExchangeAmountMin(BigDecimal exchangeAmountMin) {
        this.exchangeAmountMin = exchangeAmountMin;
    }

    public BigDecimal getExchangeAmountMax() {
        return exchangeAmountMax;
    }

    public void setExchangeAmountMax(BigDecimal exchangeAmountMax) {
        this.exchangeAmountMax = exchangeAmountMax;
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