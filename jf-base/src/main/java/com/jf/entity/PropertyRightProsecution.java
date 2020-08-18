package com.jf.entity;

import java.util.Date;

public class PropertyRightProsecution {
    private Integer id;

    private Integer intellectualPropertyRightAppealId;

    private Integer propertyRightComplainId;

    private String status;

    private String remarksToObligee;

    private String remarksToMcht;

    private String remarkToInner;

    private String winType;

    private String resultToObligee;

    private String resultToMcht;

    private String innerRemarks;

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

    public Integer getPropertyRightComplainId() {
        return propertyRightComplainId;
    }

    public void setPropertyRightComplainId(Integer propertyRightComplainId) {
        this.propertyRightComplainId = propertyRightComplainId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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

    public String getRemarkToInner() {
        return remarkToInner;
    }

    public void setRemarkToInner(String remarkToInner) {
        this.remarkToInner = remarkToInner == null ? null : remarkToInner.trim();
    }

    public String getWinType() {
        return winType;
    }

    public void setWinType(String winType) {
        this.winType = winType == null ? null : winType.trim();
    }

    public String getResultToObligee() {
        return resultToObligee;
    }

    public void setResultToObligee(String resultToObligee) {
        this.resultToObligee = resultToObligee == null ? null : resultToObligee.trim();
    }

    public String getResultToMcht() {
        return resultToMcht;
    }

    public void setResultToMcht(String resultToMcht) {
        this.resultToMcht = resultToMcht == null ? null : resultToMcht.trim();
    }

    public String getInnerRemarks() {
        return innerRemarks;
    }

    public void setInnerRemarks(String innerRemarks) {
        this.innerRemarks = innerRemarks == null ? null : innerRemarks.trim();
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