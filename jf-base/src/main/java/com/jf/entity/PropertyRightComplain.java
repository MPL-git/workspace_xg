package com.jf.entity;

import java.util.Date;

public class PropertyRightComplain {
    private Integer id;

    private Integer intellectualPropertyRightAppealId;

    private String status;

    private String complainReason;

    private String remarksToMcht;

    private String remarksToObligee;

    private String innerRemarks;

    private Date complainDate;

    private Date prosecutionEndDate;

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

    public Integer getIntellectualPropertyRightAppealId() {
        return intellectualPropertyRightAppealId;
    }

    public void setIntellectualPropertyRightAppealId(Integer intellectualPropertyRightAppealId) {
        this.intellectualPropertyRightAppealId = intellectualPropertyRightAppealId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getComplainReason() {
        return complainReason;
    }

    public void setComplainReason(String complainReason) {
        this.complainReason = complainReason == null ? null : complainReason.trim();
    }

    public String getRemarksToMcht() {
        return remarksToMcht;
    }

    public void setRemarksToMcht(String remarksToMcht) {
        this.remarksToMcht = remarksToMcht == null ? null : remarksToMcht.trim();
    }

    public String getRemarksToObligee() {
        return remarksToObligee;
    }

    public void setRemarksToObligee(String remarksToObligee) {
        this.remarksToObligee = remarksToObligee == null ? null : remarksToObligee.trim();
    }

    public String getInnerRemarks() {
        return innerRemarks;
    }

    public void setInnerRemarks(String innerRemarks) {
        this.innerRemarks = innerRemarks == null ? null : innerRemarks.trim();
    }

    public Date getComplainDate() {
        return complainDate;
    }

    public void setComplainDate(Date complainDate) {
        this.complainDate = complainDate;
    }

    public Date getProsecutionEndDate() {
        return prosecutionEndDate;
    }

    public void setProsecutionEndDate(Date prosecutionEndDate) {
        this.prosecutionEndDate = prosecutionEndDate;
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