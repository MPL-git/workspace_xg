package com.jf.entity;

import java.util.Date;

public class AndroidChannelGroupSetDtl {
    private Integer id;

    private Integer androidChannelGroupSetId;

    private Integer androidChannelGroupId;

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

    public Integer getAndroidChannelGroupSetId() {
        return androidChannelGroupSetId;
    }

    public void setAndroidChannelGroupSetId(Integer androidChannelGroupSetId) {
        this.androidChannelGroupSetId = androidChannelGroupSetId;
    }

    public Integer getAndroidChannelGroupId() {
        return androidChannelGroupId;
    }

    public void setAndroidChannelGroupId(Integer androidChannelGroupId) {
        this.androidChannelGroupId = androidChannelGroupId;
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