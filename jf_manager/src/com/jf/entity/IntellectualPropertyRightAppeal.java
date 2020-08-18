package com.jf.entity;

import java.util.Date;

public class IntellectualPropertyRightAppeal {
    private Integer id;

    private Integer obligeeId;

    private Integer intellectualPropertyRightId;

    private Integer mchtId;

    private String appealReason;

    private String appealType;

    private String relevantValue;

    private String reasonDesc;

    private String acceptStatus;

    private String remarksToObligee;

    private String remarksToMcht;

    private String innerRemarks;

    private Date commitDate;

    private String status;

    private Date statusDate;

    private Date complainEndDate;

    private Integer staffId;

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

    public Integer getObligeeId() {
        return obligeeId;
    }

    public void setObligeeId(Integer obligeeId) {
        this.obligeeId = obligeeId;
    }

    public Integer getIntellectualPropertyRightId() {
        return intellectualPropertyRightId;
    }

    public void setIntellectualPropertyRightId(Integer intellectualPropertyRightId) {
        this.intellectualPropertyRightId = intellectualPropertyRightId;
    }

    public Integer getMchtId() {
        return mchtId;
    }

    public void setMchtId(Integer mchtId) {
        this.mchtId = mchtId;
    }

    public String getAppealReason() {
        return appealReason;
    }

    public void setAppealReason(String appealReason) {
        this.appealReason = appealReason == null ? null : appealReason.trim();
    }

    public String getAppealType() {
        return appealType;
    }

    public void setAppealType(String appealType) {
        this.appealType = appealType == null ? null : appealType.trim();
    }

    public String getRelevantValue() {
        return relevantValue;
    }

    public void setRelevantValue(String relevantValue) {
        this.relevantValue = relevantValue == null ? null : relevantValue.trim();
    }

    public String getReasonDesc() {
        return reasonDesc;
    }

    public void setReasonDesc(String reasonDesc) {
        this.reasonDesc = reasonDesc == null ? null : reasonDesc.trim();
    }

    public String getAcceptStatus() {
        return acceptStatus;
    }

    public void setAcceptStatus(String acceptStatus) {
        this.acceptStatus = acceptStatus == null ? null : acceptStatus.trim();
    }

    public String getRemarksToObligee() {
        return remarksToObligee;
    }

    public void setRemarksToObligee(String remarksToObligee) {
        this.remarksToObligee = remarksToObligee == null ? null : remarksToObligee.trim();
    }

    public String getRemarksToMcht() {
        return remarksToMcht;
    }

    public void setRemarksToMcht(String remarksToMcht) {
        this.remarksToMcht = remarksToMcht == null ? null : remarksToMcht.trim();
    }

    public String getInnerRemarks() {
        return innerRemarks;
    }

    public void setInnerRemarks(String innerRemarks) {
        this.innerRemarks = innerRemarks == null ? null : innerRemarks.trim();
    }

    public Date getCommitDate() {
        return commitDate;
    }

    public void setCommitDate(Date commitDate) {
        this.commitDate = commitDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getStatusDate() {
        return statusDate;
    }

    public void setStatusDate(Date statusDate) {
        this.statusDate = statusDate;
    }

    public Date getComplainEndDate() {
        return complainEndDate;
    }

    public void setComplainEndDate(Date complainEndDate) {
        this.complainEndDate = complainEndDate;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
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