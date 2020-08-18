package com.jf.entity;

import java.util.Date;

public class BlackList {
    private Integer id;

    private Integer memberId;

    private String blackType;

    private String blackReason;

    private Date blackDate;

    private Date blackToDate;

    private Date unblackDate;

    private Integer unblackBy;

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

    public String getBlackType() {
        return blackType;
    }

    public void setBlackType(String blackType) {
        this.blackType = blackType == null ? null : blackType.trim();
    }

    public String getBlackReason() {
        return blackReason;
    }

    public void setBlackReason(String blackReason) {
        this.blackReason = blackReason == null ? null : blackReason.trim();
    }

    public Date getBlackDate() {
        return blackDate;
    }

    public void setBlackDate(Date blackDate) {
        this.blackDate = blackDate;
    }

    public Date getBlackToDate() {
        return blackToDate;
    }

    public void setBlackToDate(Date blackToDate) {
        this.blackToDate = blackToDate;
    }

    public Date getUnblackDate() {
        return unblackDate;
    }

    public void setUnblackDate(Date unblackDate) {
        this.unblackDate = unblackDate;
    }

    public Integer getUnblackBy() {
        return unblackBy;
    }

    public void setUnblackBy(Integer unblackBy) {
        this.unblackBy = unblackBy;
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