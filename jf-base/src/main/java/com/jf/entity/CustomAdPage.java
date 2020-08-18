package com.jf.entity;

import java.util.Date;

public class CustomAdPage {
    private Integer id;

    private Integer sourceProductTypeId;

    private String pageType;

    private String pageName;

    private Integer decorateInfoId;

    private String isEffect;

    private Date autoUpDate;

    private Date autoDownDate;

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

    public Integer getSourceProductTypeId() {
        return sourceProductTypeId;
    }

    public void setSourceProductTypeId(Integer sourceProductTypeId) {
        this.sourceProductTypeId = sourceProductTypeId;
    }

    public String getPageType() {
        return pageType;
    }

    public void setPageType(String pageType) {
        this.pageType = pageType == null ? null : pageType.trim();
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName == null ? null : pageName.trim();
    }

    public Integer getDecorateInfoId() {
        return decorateInfoId;
    }

    public void setDecorateInfoId(Integer decorateInfoId) {
        this.decorateInfoId = decorateInfoId;
    }

    public String getIsEffect() {
        return isEffect;
    }

    public void setIsEffect(String isEffect) {
        this.isEffect = isEffect == null ? null : isEffect.trim();
    }

    public Date getAutoUpDate() {
        return autoUpDate;
    }

    public void setAutoUpDate(Date autoUpDate) {
        this.autoUpDate = autoUpDate;
    }

    public Date getAutoDownDate() {
        return autoDownDate;
    }

    public void setAutoDownDate(Date autoDownDate) {
        this.autoDownDate = autoDownDate;
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