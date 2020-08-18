package com.jf.entity;

import java.util.Date;

public class TaskMarketing {
    private Integer id;

    private Integer taskId;

    private String iosContext;

    private String iosTitle;

    private String androidContext;

    private String androidTitle;

    private Integer bigPushDuration;

    private String linkType;

    private String linkUrl;

    private Integer sendMemberCount;

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

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getIosContext() {
        return iosContext;
    }

    public void setIosContext(String iosContext) {
        this.iosContext = iosContext == null ? null : iosContext.trim();
    }

    public String getIosTitle() {
        return iosTitle;
    }

    public void setIosTitle(String iosTitle) {
        this.iosTitle = iosTitle == null ? null : iosTitle.trim();
    }

    public String getAndroidContext() {
        return androidContext;
    }

    public void setAndroidContext(String androidContext) {
        this.androidContext = androidContext == null ? null : androidContext.trim();
    }

    public String getAndroidTitle() {
        return androidTitle;
    }

    public void setAndroidTitle(String androidTitle) {
        this.androidTitle = androidTitle == null ? null : androidTitle.trim();
    }

    public Integer getBigPushDuration() {
        return bigPushDuration;
    }

    public void setBigPushDuration(Integer bigPushDuration) {
        this.bigPushDuration = bigPushDuration;
    }

    public String getLinkType() {
        return linkType;
    }

    public void setLinkType(String linkType) {
        this.linkType = linkType == null ? null : linkType.trim();
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl == null ? null : linkUrl.trim();
    }

    public Integer getSendMemberCount() {
        return sendMemberCount;
    }

    public void setSendMemberCount(Integer sendMemberCount) {
        this.sendMemberCount = sendMemberCount;
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