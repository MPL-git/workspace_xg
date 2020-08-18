package com.jf.entity;

import java.util.Date;

public class AdPvStatistical {
    private Integer id;

    private String statisticalDate;

    private String adType;

    private String adSourceType;

    private Integer adInfoId;

    private String adPic;

    private Integer exposureCount;

    private Integer clickMemberCount;

    private Integer clickMemberCountTourist;

    private Integer clickCount;

    private Integer clickCountTourist;

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

    public String getStatisticalDate() {
        return statisticalDate;
    }

    public void setStatisticalDate(String statisticalDate) {
        this.statisticalDate = statisticalDate == null ? null : statisticalDate.trim();
    }

    public String getAdType() {
        return adType;
    }

    public void setAdType(String adType) {
        this.adType = adType == null ? null : adType.trim();
    }

    public String getAdSourceType() {
        return adSourceType;
    }

    public void setAdSourceType(String adSourceType) {
        this.adSourceType = adSourceType == null ? null : adSourceType.trim();
    }

    public Integer getAdInfoId() {
        return adInfoId;
    }

    public void setAdInfoId(Integer adInfoId) {
        this.adInfoId = adInfoId;
    }

    public String getAdPic() {
        return adPic;
    }

    public void setAdPic(String adPic) {
        this.adPic = adPic == null ? null : adPic.trim();
    }

    public Integer getExposureCount() {
        return exposureCount;
    }

    public void setExposureCount(Integer exposureCount) {
        this.exposureCount = exposureCount;
    }

    public Integer getClickMemberCount() {
        return clickMemberCount;
    }

    public void setClickMemberCount(Integer clickMemberCount) {
        this.clickMemberCount = clickMemberCount;
    }

    public Integer getClickMemberCountTourist() {
        return clickMemberCountTourist;
    }

    public void setClickMemberCountTourist(Integer clickMemberCountTourist) {
        this.clickMemberCountTourist = clickMemberCountTourist;
    }

    public Integer getClickCount() {
        return clickCount;
    }

    public void setClickCount(Integer clickCount) {
        this.clickCount = clickCount;
    }

    public Integer getClickCountTourist() {
        return clickCountTourist;
    }

    public void setClickCountTourist(Integer clickCountTourist) {
        this.clickCountTourist = clickCountTourist;
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