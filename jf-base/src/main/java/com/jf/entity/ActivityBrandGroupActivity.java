package com.jf.entity;

import java.util.Date;

public class ActivityBrandGroupActivity {
    private Integer id;

    private Integer activityBrandGroupId;

    private Integer activityAreaId;

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

    public Integer getActivityBrandGroupId() {
        return activityBrandGroupId;
    }

    public void setActivityBrandGroupId(Integer activityBrandGroupId) {
        this.activityBrandGroupId = activityBrandGroupId;
    }

    public Integer getActivityAreaId() {
        return activityAreaId;
    }

    public void setActivityAreaId(Integer activityAreaId) {
        this.activityAreaId = activityAreaId;
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