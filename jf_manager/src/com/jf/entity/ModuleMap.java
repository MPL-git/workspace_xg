package com.jf.entity;

import java.util.Date;

public class ModuleMap {
    private Integer id;

    private Integer decorateModuleId;

    private String coords;

    private String linkType;

    private Integer linkValue;

    private String linkUrl;

    private String fontColor;

    private Integer fontSize;

    private Date countDownBeginDate;

    private Date countDownEndDate;

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

    public Integer getDecorateModuleId() {
        return decorateModuleId;
    }

    public void setDecorateModuleId(Integer decorateModuleId) {
        this.decorateModuleId = decorateModuleId;
    }

    public String getCoords() {
        return coords;
    }

    public void setCoords(String coords) {
        this.coords = coords == null ? null : coords.trim();
    }

    public String getLinkType() {
        return linkType;
    }

    public void setLinkType(String linkType) {
        this.linkType = linkType == null ? null : linkType.trim();
    }

    public Integer getLinkValue() {
        return linkValue;
    }

    public void setLinkValue(Integer linkValue) {
        this.linkValue = linkValue;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl == null ? null : linkUrl.trim();
    }

    public String getFontColor() {
        return fontColor;
    }

    public void setFontColor(String fontColor) {
        this.fontColor = fontColor == null ? null : fontColor.trim();
    }

    public Integer getFontSize() {
        return fontSize;
    }

    public void setFontSize(Integer fontSize) {
        this.fontSize = fontSize;
    }

    public Date getCountDownBeginDate() {
        return countDownBeginDate;
    }

    public void setCountDownBeginDate(Date countDownBeginDate) {
        this.countDownBeginDate = countDownBeginDate;
    }

    public Date getCountDownEndDate() {
        return countDownEndDate;
    }

    public void setCountDownEndDate(Date countDownEndDate) {
        this.countDownEndDate = countDownEndDate;
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