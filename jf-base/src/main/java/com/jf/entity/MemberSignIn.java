package com.jf.entity;

import java.util.Date;

public class MemberSignIn {
    private Integer id;

    private Integer memberId;

    private Date lastSignInDate;

    private Integer mostContinuity;

    private Integer currentContinuity;

    private Integer sendCount;

    private String isSendWarn;

    private Integer popupCount;

    private Integer sourceMemberId;

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

    public Date getLastSignInDate() {
        return lastSignInDate;
    }

    public void setLastSignInDate(Date lastSignInDate) {
        this.lastSignInDate = lastSignInDate;
    }

    public Integer getMostContinuity() {
        return mostContinuity;
    }

    public void setMostContinuity(Integer mostContinuity) {
        this.mostContinuity = mostContinuity;
    }

    public Integer getCurrentContinuity() {
        return currentContinuity;
    }

    public void setCurrentContinuity(Integer currentContinuity) {
        this.currentContinuity = currentContinuity;
    }

    public Integer getSendCount() {
        return sendCount;
    }

    public void setSendCount(Integer sendCount) {
        this.sendCount = sendCount;
    }

    public String getIsSendWarn() {
        return isSendWarn;
    }

    public void setIsSendWarn(String isSendWarn) {
        this.isSendWarn = isSendWarn == null ? null : isSendWarn.trim();
    }

    public Integer getPopupCount() {
        return popupCount;
    }

    public void setPopupCount(Integer popupCount) {
        this.popupCount = popupCount;
    }

    public Integer getSourceMemberId() {
        return sourceMemberId;
    }

    public void setSourceMemberId(Integer sourceMemberId) {
        this.sourceMemberId = sourceMemberId;
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