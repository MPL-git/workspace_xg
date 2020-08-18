package com.jf.entity;

import java.util.Date;

public class InterventionOrder {
    private Integer id;

    private Integer serviceOrderId;

    private Integer mchtId;

    private String interventionCode;

    private String proStatus;

    private String reason;

    private String contacts;

    private String tel;

    private String message;

    private String status;

    private Integer platformProcessor;

    private String rejectReason;

    private String platformRemarks;

    private String directorReason;

    private String directorRemarks;

    private String winType;

    private String clientResultReason;

    private String mchtResultReason;

    private String recordOfCases;

    private String isInitiateViolate;

    private String isComment;

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

    public Integer getServiceOrderId() {
        return serviceOrderId;
    }

    public void setServiceOrderId(Integer serviceOrderId) {
        this.serviceOrderId = serviceOrderId;
    }

    public Integer getMchtId() {
        return mchtId;
    }

    public void setMchtId(Integer mchtId) {
        this.mchtId = mchtId;
    }

    public String getInterventionCode() {
        return interventionCode;
    }

    public void setInterventionCode(String interventionCode) {
        this.interventionCode = interventionCode == null ? null : interventionCode.trim();
    }

    public String getProStatus() {
        return proStatus;
    }

    public void setProStatus(String proStatus) {
        this.proStatus = proStatus == null ? null : proStatus.trim();
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts == null ? null : contacts.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Integer getPlatformProcessor() {
        return platformProcessor;
    }

    public void setPlatformProcessor(Integer platformProcessor) {
        this.platformProcessor = platformProcessor;
    }

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason == null ? null : rejectReason.trim();
    }

    public String getPlatformRemarks() {
        return platformRemarks;
    }

    public void setPlatformRemarks(String platformRemarks) {
        this.platformRemarks = platformRemarks == null ? null : platformRemarks.trim();
    }

    public String getDirectorReason() {
        return directorReason;
    }

    public void setDirectorReason(String directorReason) {
        this.directorReason = directorReason == null ? null : directorReason.trim();
    }

    public String getDirectorRemarks() {
        return directorRemarks;
    }

    public void setDirectorRemarks(String directorRemarks) {
        this.directorRemarks = directorRemarks == null ? null : directorRemarks.trim();
    }

    public String getWinType() {
        return winType;
    }

    public void setWinType(String winType) {
        this.winType = winType == null ? null : winType.trim();
    }

    public String getClientResultReason() {
        return clientResultReason;
    }

    public void setClientResultReason(String clientResultReason) {
        this.clientResultReason = clientResultReason == null ? null : clientResultReason.trim();
    }

    public String getMchtResultReason() {
        return mchtResultReason;
    }

    public void setMchtResultReason(String mchtResultReason) {
        this.mchtResultReason = mchtResultReason == null ? null : mchtResultReason.trim();
    }

    public String getRecordOfCases() {
        return recordOfCases;
    }

    public void setRecordOfCases(String recordOfCases) {
        this.recordOfCases = recordOfCases == null ? null : recordOfCases.trim();
    }

    public String getIsInitiateViolate() {
        return isInitiateViolate;
    }

    public void setIsInitiateViolate(String isInitiateViolate) {
        this.isInitiateViolate = isInitiateViolate == null ? null : isInitiateViolate.trim();
    }

    public String getIsComment() {
        return isComment;
    }

    public void setIsComment(String isComment) {
        this.isComment = isComment == null ? null : isComment.trim();
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