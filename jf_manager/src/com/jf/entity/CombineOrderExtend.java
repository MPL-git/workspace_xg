package com.jf.entity;

import java.util.Date;

public class CombineOrderExtend {
    private Integer id;

    private Integer combineOrderId;

    private String spreadname;

    private String activityname;

    private String channel;

    private String trackingioCommitStatus;

    private Date trackingioCommitTime;

    private String isSvipMember;

    private String sprChnl;

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

    public Integer getCombineOrderId() {
        return combineOrderId;
    }

    public void setCombineOrderId(Integer combineOrderId) {
        this.combineOrderId = combineOrderId;
    }

    public String getSpreadname() {
        return spreadname;
    }

    public void setSpreadname(String spreadname) {
        this.spreadname = spreadname == null ? null : spreadname.trim();
    }

    public String getActivityname() {
        return activityname;
    }

    public void setActivityname(String activityname) {
        this.activityname = activityname == null ? null : activityname.trim();
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel == null ? null : channel.trim();
    }

    public String getTrackingioCommitStatus() {
        return trackingioCommitStatus;
    }

    public void setTrackingioCommitStatus(String trackingioCommitStatus) {
        this.trackingioCommitStatus = trackingioCommitStatus == null ? null : trackingioCommitStatus.trim();
    }

    public Date getTrackingioCommitTime() {
        return trackingioCommitTime;
    }

    public void setTrackingioCommitTime(Date trackingioCommitTime) {
        this.trackingioCommitTime = trackingioCommitTime;
    }

    public String getIsSvipMember() {
        return isSvipMember;
    }

    public void setIsSvipMember(String isSvipMember) {
        this.isSvipMember = isSvipMember == null ? null : isSvipMember.trim();
    }

    public String getSprChnl() {
        return sprChnl;
    }

    public void setSprChnl(String sprChnl) {
        this.sprChnl = sprChnl == null ? null : sprChnl.trim();
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