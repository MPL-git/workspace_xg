package com.jf.entity;

import java.math.BigDecimal;
import java.util.Date;

public class MchtBrandRateChange {
    private Integer id;

    private Integer mchtId;

    private Integer cooperationChangeApplyId;

    private Integer mchtProductBrandId;

    private BigDecimal popCommissionRate;

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

    public Integer getCooperationChangeApplyId() {
        return cooperationChangeApplyId;
    }

    public void setCooperationChangeApplyId(Integer cooperationChangeApplyId) {
        this.cooperationChangeApplyId = cooperationChangeApplyId;
    }

    public Integer getMchtProductBrandId() {
        return mchtProductBrandId;
    }

    public void setMchtProductBrandId(Integer mchtProductBrandId) {
        this.mchtProductBrandId = mchtProductBrandId;
    }

    public BigDecimal getPopCommissionRate() {
        return popCommissionRate;
    }

    public void setPopCommissionRate(BigDecimal popCommissionRate) {
        this.popCommissionRate = popCommissionRate;
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