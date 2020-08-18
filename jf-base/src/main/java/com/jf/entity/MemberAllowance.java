package com.jf.entity;

import java.math.BigDecimal;
import java.util.Date;

public class MemberAllowance {
    private Integer id;

    private Integer memberId;

    private Integer allowanceSettingId;

    private Integer allowanceInfoId;

    private String source;

    private Integer spendIntegral;

    private BigDecimal allowanceAmount;

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

    public Integer getAllowanceSettingId() {
        return allowanceSettingId;
    }

    public void setAllowanceSettingId(Integer allowanceSettingId) {
        this.allowanceSettingId = allowanceSettingId;
    }

    public Integer getAllowanceInfoId() {
        return allowanceInfoId;
    }

    public void setAllowanceInfoId(Integer allowanceInfoId) {
        this.allowanceInfoId = allowanceInfoId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    public Integer getSpendIntegral() {
        return spendIntegral;
    }

    public void setSpendIntegral(Integer spendIntegral) {
        this.spendIntegral = spendIntegral;
    }

    public BigDecimal getAllowanceAmount() {
        return allowanceAmount;
    }

    public void setAllowanceAmount(BigDecimal allowanceAmount) {
        this.allowanceAmount = allowanceAmount;
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