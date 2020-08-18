package com.jf.entity;

import java.util.Date;

public class DouyinSprPlan {
    private Integer id;

    private Integer sprChnlId;

    private String sprPlanSite;

    private String sprPlanType;

    private String linkValue;

    private String sprUrl;

    private String convertId;

    private String convertId2;

    private String trackingId;

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

    public Integer getSprChnlId() {
        return sprChnlId;
    }

    public void setSprChnlId(Integer sprChnlId) {
        this.sprChnlId = sprChnlId;
    }

    public String getSprPlanSite() {
        return sprPlanSite;
    }

    public void setSprPlanSite(String sprPlanSite) {
        this.sprPlanSite = sprPlanSite == null ? null : sprPlanSite.trim();
    }

    public String getSprPlanType() {
        return sprPlanType;
    }

    public void setSprPlanType(String sprPlanType) {
        this.sprPlanType = sprPlanType == null ? null : sprPlanType.trim();
    }

    public String getLinkValue() {
        return linkValue;
    }

    public void setLinkValue(String linkValue) {
        this.linkValue = linkValue == null ? null : linkValue.trim();
    }

    public String getSprUrl() {
        return sprUrl;
    }

    public void setSprUrl(String sprUrl) {
        this.sprUrl = sprUrl == null ? null : sprUrl.trim();
    }

    public String getConvertId() {
        return convertId;
    }

    public void setConvertId(String convertId) {
        this.convertId = convertId == null ? null : convertId.trim();
    }

    public String getConvertId2() {
        return convertId2;
    }

    public void setConvertId2(String convertId2) {
        this.convertId2 = convertId2 == null ? null : convertId2.trim();
    }

    public String getTrackingId() {
        return trackingId;
    }

    public void setTrackingId(String trackingId) {
        this.trackingId = trackingId == null ? null : trackingId.trim();
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