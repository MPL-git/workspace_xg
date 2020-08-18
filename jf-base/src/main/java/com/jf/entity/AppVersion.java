package com.jf.entity;

import java.util.Date;

public class AppVersion {
    private Integer id;

    private Integer appVersion;

    private String appVersionNo;

    private Integer targetVersion;

    private String appType;

    private String sprChnl;

    private String packageUrl;

    private Integer packageSize;

    private String isEffect;

    private String launchContent;

    private String isMust;

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

    public Integer getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(Integer appVersion) {
        this.appVersion = appVersion;
    }

    public String getAppVersionNo() {
        return appVersionNo;
    }

    public void setAppVersionNo(String appVersionNo) {
        this.appVersionNo = appVersionNo == null ? null : appVersionNo.trim();
    }

    public Integer getTargetVersion() {
        return targetVersion;
    }

    public void setTargetVersion(Integer targetVersion) {
        this.targetVersion = targetVersion;
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType == null ? null : appType.trim();
    }

    public String getSprChnl() {
        return sprChnl;
    }

    public void setSprChnl(String sprChnl) {
        this.sprChnl = sprChnl == null ? null : sprChnl.trim();
    }

    public String getPackageUrl() {
        return packageUrl;
    }

    public void setPackageUrl(String packageUrl) {
        this.packageUrl = packageUrl == null ? null : packageUrl.trim();
    }

    public Integer getPackageSize() {
        return packageSize;
    }

    public void setPackageSize(Integer packageSize) {
        this.packageSize = packageSize;
    }

    public String getIsEffect() {
        return isEffect;
    }

    public void setIsEffect(String isEffect) {
        this.isEffect = isEffect == null ? null : isEffect.trim();
    }

    public String getLaunchContent() {
        return launchContent;
    }

    public void setLaunchContent(String launchContent) {
        this.launchContent = launchContent == null ? null : launchContent.trim();
    }

    public String getIsMust() {
        return isMust;
    }

    public void setIsMust(String isMust) {
        this.isMust = isMust == null ? null : isMust.trim();
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