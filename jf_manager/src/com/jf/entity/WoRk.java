package com.jf.entity;

import java.util.Date;

public class WoRk {
    private Integer id;

    private Integer orgId;

    private Integer staffId;

    private String workType;

    private String status;

    private String statusBehavior;

    private String urgentDegree;

    private String closeReason;

    private String titleContent;

    private String relevantType;

    private String relevantCode;

    private Integer relevantId;

    private String describeContent;

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

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType == null ? null : workType.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getStatusBehavior() {
        return statusBehavior;
    }

    public void setStatusBehavior(String statusBehavior) {
        this.statusBehavior = statusBehavior == null ? null : statusBehavior.trim();
    }

    public String getUrgentDegree() {
        return urgentDegree;
    }

    public void setUrgentDegree(String urgentDegree) {
        this.urgentDegree = urgentDegree == null ? null : urgentDegree.trim();
    }

    public String getCloseReason() {
        return closeReason;
    }

    public void setCloseReason(String closeReason) {
        this.closeReason = closeReason == null ? null : closeReason.trim();
    }

    public String getTitleContent() {
        return titleContent;
    }

    public void setTitleContent(String titleContent) {
        this.titleContent = titleContent == null ? null : titleContent.trim();
    }

    public String getRelevantType() {
        return relevantType;
    }

    public void setRelevantType(String relevantType) {
        this.relevantType = relevantType == null ? null : relevantType.trim();
    }

    public String getRelevantCode() {
        return relevantCode;
    }

    public void setRelevantCode(String relevantCode) {
        this.relevantCode = relevantCode == null ? null : relevantCode.trim();
    }

    public Integer getRelevantId() {
        return relevantId;
    }

    public void setRelevantId(Integer relevantId) {
        this.relevantId = relevantId;
    }

    public String getDescribeContent() {
        return describeContent;
    }

    public void setDescribeContent(String describeContent) {
        this.describeContent = describeContent == null ? null : describeContent.trim();
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