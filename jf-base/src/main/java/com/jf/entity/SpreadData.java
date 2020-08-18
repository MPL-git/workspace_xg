package com.jf.entity;

import java.util.Date;

public class SpreadData {
    private Integer id;

    private Date date;

    private String accountId;

    private String accountName;

    private String displayQuantity;

    private String clickQuantity;

    private String clickRate;

    private String conversionQuantity;

    private String conversionCost;

    private String conversionRate;

    private String totalConsumption;

    private String consumptionRingRatio;

    private String balance;

    private String availableBalance;

    private String email;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId == null ? null : accountId.trim();
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName == null ? null : accountName.trim();
    }

    public String getDisplayQuantity() {
        return displayQuantity;
    }

    public void setDisplayQuantity(String displayQuantity) {
        this.displayQuantity = displayQuantity == null ? null : displayQuantity.trim();
    }

    public String getClickQuantity() {
        return clickQuantity;
    }

    public void setClickQuantity(String clickQuantity) {
        this.clickQuantity = clickQuantity == null ? null : clickQuantity.trim();
    }

    public String getClickRate() {
        return clickRate;
    }

    public void setClickRate(String clickRate) {
        this.clickRate = clickRate == null ? null : clickRate.trim();
    }

    public String getConversionQuantity() {
        return conversionQuantity;
    }

    public void setConversionQuantity(String conversionQuantity) {
        this.conversionQuantity = conversionQuantity == null ? null : conversionQuantity.trim();
    }

    public String getConversionCost() {
        return conversionCost;
    }

    public void setConversionCost(String conversionCost) {
        this.conversionCost = conversionCost == null ? null : conversionCost.trim();
    }

    public String getConversionRate() {
        return conversionRate;
    }

    public void setConversionRate(String conversionRate) {
        this.conversionRate = conversionRate == null ? null : conversionRate.trim();
    }

    public String getTotalConsumption() {
        return totalConsumption;
    }

    public void setTotalConsumption(String totalConsumption) {
        this.totalConsumption = totalConsumption == null ? null : totalConsumption.trim();
    }

    public String getConsumptionRingRatio() {
        return consumptionRingRatio;
    }

    public void setConsumptionRingRatio(String consumptionRingRatio) {
        this.consumptionRingRatio = consumptionRingRatio == null ? null : consumptionRingRatio.trim();
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance == null ? null : balance.trim();
    }

    public String getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(String availableBalance) {
        this.availableBalance = availableBalance == null ? null : availableBalance.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
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