package com.jf.entity;

import java.math.BigDecimal;
import java.util.Date;

public class SingleProductActivity {
    private Integer id;

    private String type;

    private Integer mchtId;

    private Integer productId;

    private Integer couponId;

    private String originalPrice;

    private BigDecimal activityPrice;

    private String seckillType;

    private BigDecimal comparePrice;

    private Date expectedDate;

    private Date beginTime;

    private Date endTime;

    private String auditStatus;

    private String status;

    private String isClose;

    private Integer firstAuditBy;

    private Integer scheduleAuditBy;

    private Integer againAuditBy;

    private Integer seqNo;

    private Integer unrealityNum;

    private Integer tomorrowIncreaseMin;

    private Integer tomorrowIncreaseMax;

    private String isWatermark;

    private BigDecimal platformPreferential;

    private Integer seckillTimeId;

    private String isRecommend;

    private Integer recommendSeqNo;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Integer getMchtId() {
        return mchtId;
    }

    public void setMchtId(Integer mchtId) {
        this.mchtId = mchtId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public String getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(String originalPrice) {
        this.originalPrice = originalPrice == null ? null : originalPrice.trim();
    }

    public BigDecimal getActivityPrice() {
        return activityPrice;
    }

    public void setActivityPrice(BigDecimal activityPrice) {
        this.activityPrice = activityPrice;
    }

    public String getSeckillType() {
        return seckillType;
    }

    public void setSeckillType(String seckillType) {
        this.seckillType = seckillType == null ? null : seckillType.trim();
    }

    public BigDecimal getComparePrice() {
        return comparePrice;
    }

    public void setComparePrice(BigDecimal comparePrice) {
        this.comparePrice = comparePrice;
    }

    public Date getExpectedDate() {
        return expectedDate;
    }

    public void setExpectedDate(Date expectedDate) {
        this.expectedDate = expectedDate;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus == null ? null : auditStatus.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getIsClose() {
        return isClose;
    }

    public void setIsClose(String isClose) {
        this.isClose = isClose == null ? null : isClose.trim();
    }

    public Integer getFirstAuditBy() {
        return firstAuditBy;
    }

    public void setFirstAuditBy(Integer firstAuditBy) {
        this.firstAuditBy = firstAuditBy;
    }

    public Integer getScheduleAuditBy() {
        return scheduleAuditBy;
    }

    public void setScheduleAuditBy(Integer scheduleAuditBy) {
        this.scheduleAuditBy = scheduleAuditBy;
    }

    public Integer getAgainAuditBy() {
        return againAuditBy;
    }

    public void setAgainAuditBy(Integer againAuditBy) {
        this.againAuditBy = againAuditBy;
    }

    public Integer getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(Integer seqNo) {
        this.seqNo = seqNo;
    }

    public Integer getUnrealityNum() {
        return unrealityNum;
    }

    public void setUnrealityNum(Integer unrealityNum) {
        this.unrealityNum = unrealityNum;
    }

    public Integer getTomorrowIncreaseMin() {
        return tomorrowIncreaseMin;
    }

    public void setTomorrowIncreaseMin(Integer tomorrowIncreaseMin) {
        this.tomorrowIncreaseMin = tomorrowIncreaseMin;
    }

    public Integer getTomorrowIncreaseMax() {
        return tomorrowIncreaseMax;
    }

    public void setTomorrowIncreaseMax(Integer tomorrowIncreaseMax) {
        this.tomorrowIncreaseMax = tomorrowIncreaseMax;
    }

    public String getIsWatermark() {
        return isWatermark;
    }

    public void setIsWatermark(String isWatermark) {
        this.isWatermark = isWatermark == null ? null : isWatermark.trim();
    }

    public BigDecimal getPlatformPreferential() {
        return platformPreferential;
    }

    public void setPlatformPreferential(BigDecimal platformPreferential) {
        this.platformPreferential = platformPreferential;
    }

    public Integer getSeckillTimeId() {
        return seckillTimeId;
    }

    public void setSeckillTimeId(Integer seckillTimeId) {
        this.seckillTimeId = seckillTimeId;
    }

    public String getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(String isRecommend) {
        this.isRecommend = isRecommend == null ? null : isRecommend.trim();
    }

    public Integer getRecommendSeqNo() {
        return recommendSeqNo;
    }

    public void setRecommendSeqNo(Integer recommendSeqNo) {
        this.recommendSeqNo = recommendSeqNo;
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