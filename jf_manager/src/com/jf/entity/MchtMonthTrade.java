package com.jf.entity;

import java.math.BigDecimal;
import java.util.Date;

public class MchtMonthTrade {
    private Integer id;

    private String tradeMonth;

    private Integer mchtId;

    private BigDecimal beginUnpay;

    private BigDecimal currentMonthSettleAmount;

    private BigDecimal currentDepositAmount;

    private BigDecimal currentPayAmount;

    private BigDecimal violateNeedDeduct;

    private BigDecimal depositDtl;

    private BigDecimal discount;

    private BigDecimal endUnpay;

    private BigDecimal totalOrderPayAmount;

    private BigDecimal collectDepositAmount;

    private BigDecimal currentChangeAmount;

    private BigDecimal depositBalance;

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

    public String getTradeMonth() {
        return tradeMonth;
    }

    public void setTradeMonth(String tradeMonth) {
        this.tradeMonth = tradeMonth == null ? null : tradeMonth.trim();
    }

    public Integer getMchtId() {
        return mchtId;
    }

    public void setMchtId(Integer mchtId) {
        this.mchtId = mchtId;
    }

    public BigDecimal getBeginUnpay() {
        return beginUnpay;
    }

    public void setBeginUnpay(BigDecimal beginUnpay) {
        this.beginUnpay = beginUnpay;
    }

    public BigDecimal getCurrentMonthSettleAmount() {
        return currentMonthSettleAmount;
    }

    public void setCurrentMonthSettleAmount(BigDecimal currentMonthSettleAmount) {
        this.currentMonthSettleAmount = currentMonthSettleAmount;
    }

    public BigDecimal getCurrentDepositAmount() {
        return currentDepositAmount;
    }

    public void setCurrentDepositAmount(BigDecimal currentDepositAmount) {
        this.currentDepositAmount = currentDepositAmount;
    }

    public BigDecimal getCurrentPayAmount() {
        return currentPayAmount;
    }

    public void setCurrentPayAmount(BigDecimal currentPayAmount) {
        this.currentPayAmount = currentPayAmount;
    }

    public BigDecimal getViolateNeedDeduct() {
        return violateNeedDeduct;
    }

    public void setViolateNeedDeduct(BigDecimal violateNeedDeduct) {
        this.violateNeedDeduct = violateNeedDeduct;
    }

    public BigDecimal getDepositDtl() {
        return depositDtl;
    }

    public void setDepositDtl(BigDecimal depositDtl) {
        this.depositDtl = depositDtl;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getEndUnpay() {
        return endUnpay;
    }

    public void setEndUnpay(BigDecimal endUnpay) {
        this.endUnpay = endUnpay;
    }

    public BigDecimal getTotalOrderPayAmount() {
        return totalOrderPayAmount;
    }

    public void setTotalOrderPayAmount(BigDecimal totalOrderPayAmount) {
        this.totalOrderPayAmount = totalOrderPayAmount;
    }

    public BigDecimal getCollectDepositAmount() {
        return collectDepositAmount;
    }

    public void setCollectDepositAmount(BigDecimal collectDepositAmount) {
        this.collectDepositAmount = collectDepositAmount;
    }

    public BigDecimal getCurrentChangeAmount() {
        return currentChangeAmount;
    }

    public void setCurrentChangeAmount(BigDecimal currentChangeAmount) {
        this.currentChangeAmount = currentChangeAmount;
    }

    public BigDecimal getDepositBalance() {
        return depositBalance;
    }

    public void setDepositBalance(BigDecimal depositBalance) {
        this.depositBalance = depositBalance;
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