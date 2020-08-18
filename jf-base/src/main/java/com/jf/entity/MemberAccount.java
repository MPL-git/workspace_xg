package com.jf.entity;

import java.math.BigDecimal;
import java.util.Date;

public class MemberAccount {
    private Integer id;

    private Integer memberId;

    private BigDecimal balance;

    private BigDecimal freeze;

    private Integer integral;

    private Integer gValue;

    private String isEffect;

    private Integer supplementaryCard;

    private BigDecimal profitInviteBalance;

    private BigDecimal profitInviteFreeze;

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

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getFreeze() {
        return freeze;
    }

    public void setFreeze(BigDecimal freeze) {
        this.freeze = freeze;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public Integer getgValue() {
        return gValue;
    }

    public void setgValue(Integer gValue) {
        this.gValue = gValue;
    }

    public String getIsEffect() {
        return isEffect;
    }

    public void setIsEffect(String isEffect) {
        this.isEffect = isEffect == null ? null : isEffect.trim();
    }

    public Integer getSupplementaryCard() {
        return supplementaryCard;
    }

    public void setSupplementaryCard(Integer supplementaryCard) {
        this.supplementaryCard = supplementaryCard;
    }

    public BigDecimal getProfitInviteBalance() {
        return profitInviteBalance;
    }

    public void setProfitInviteBalance(BigDecimal profitInviteBalance) {
        this.profitInviteBalance = profitInviteBalance;
    }

    public BigDecimal getProfitInviteFreeze() {
        return profitInviteFreeze;
    }

    public void setProfitInviteFreeze(BigDecimal profitInviteFreeze) {
        this.profitInviteFreeze = profitInviteFreeze;
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