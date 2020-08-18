package com.jf.entity;

import java.util.Date;

public class IntellectualPropertyRight {
    private Integer id;

    private Integer obligeeId;

    private String propertyRightType;

    private String propertyRightBelong;

    private String propertyRightName;

    private String obligeeName;

    private String status;

    private String auditRemarks;

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

    public String getPropertyRightType() {
        return propertyRightType;
    }

    public void setPropertyRightType(String propertyRightType) {
        this.propertyRightType = propertyRightType == null ? null : propertyRightType.trim();
    }

    public String getPropertyRightBelong() {
        return propertyRightBelong;
    }

    public void setPropertyRightBelong(String propertyRightBelong) {
        this.propertyRightBelong = propertyRightBelong == null ? null : propertyRightBelong.trim();
    }

    public String getPropertyRightName() {
        return propertyRightName;
    }

    public void setPropertyRightName(String propertyRightName) {
        this.propertyRightName = propertyRightName == null ? null : propertyRightName.trim();
    }

    public String getObligeeName() {
        return obligeeName;
    }

    public void setObligeeName(String obligeeName) {
        this.obligeeName = obligeeName == null ? null : obligeeName.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getAuditRemarks() {
        return auditRemarks;
    }

    public void setAuditRemarks(String auditRemarks) {
        this.auditRemarks = auditRemarks == null ? null : auditRemarks.trim();
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