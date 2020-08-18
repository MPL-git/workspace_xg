package com.jf.entity;

import java.util.Date;

public class SeckillModuleColorSetting {
    private Integer id;

    private Integer decorateModuleId;

    private String dataSource;

    private String totalBgColor;

    private String timeColBgColor;

    private String selectedBgColor;

    private String btnDefaultBgColor;

    private String btnSelectedBgColor;

    private String couponBgColor;

    private String defaultFontColor;

    private String selectedFontColor;

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

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource == null ? null : dataSource.trim();
    }

    public String getTotalBgColor() {
        return totalBgColor;
    }

    public void setTotalBgColor(String totalBgColor) {
        this.totalBgColor = totalBgColor == null ? null : totalBgColor.trim();
    }

    public String getTimeColBgColor() {
        return timeColBgColor;
    }

    public void setTimeColBgColor(String timeColBgColor) {
        this.timeColBgColor = timeColBgColor == null ? null : timeColBgColor.trim();
    }

    public String getSelectedBgColor() {
        return selectedBgColor;
    }

    public void setSelectedBgColor(String selectedBgColor) {
        this.selectedBgColor = selectedBgColor == null ? null : selectedBgColor.trim();
    }

    public String getBtnDefaultBgColor() {
        return btnDefaultBgColor;
    }

    public void setBtnDefaultBgColor(String btnDefaultBgColor) {
        this.btnDefaultBgColor = btnDefaultBgColor == null ? null : btnDefaultBgColor.trim();
    }

    public String getBtnSelectedBgColor() {
        return btnSelectedBgColor;
    }

    public void setBtnSelectedBgColor(String btnSelectedBgColor) {
        this.btnSelectedBgColor = btnSelectedBgColor == null ? null : btnSelectedBgColor.trim();
    }

    public String getCouponBgColor() {
        return couponBgColor;
    }

    public void setCouponBgColor(String couponBgColor) {
        this.couponBgColor = couponBgColor == null ? null : couponBgColor.trim();
    }

    public String getDefaultFontColor() {
        return defaultFontColor;
    }

    public void setDefaultFontColor(String defaultFontColor) {
        this.defaultFontColor = defaultFontColor == null ? null : defaultFontColor.trim();
    }

    public String getSelectedFontColor() {
        return selectedFontColor;
    }

    public void setSelectedFontColor(String selectedFontColor) {
        this.selectedFontColor = selectedFontColor == null ? null : selectedFontColor.trim();
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