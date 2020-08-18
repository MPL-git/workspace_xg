package com.jf.entity;

import java.math.BigDecimal;
import java.util.Date;

public class ActivityProduct {
    private Integer id;

    private Integer activityId;

    private Integer productId;

    private BigDecimal activityPrice;

    private String refuseFlag;

    private Integer operateAuditBy;

    private String operateAuditStatus;

    private Integer designAuditBy;

    private String designAuditStatus;

    private Integer lawAuditBy;

    private String lawAuditStatus;

    private Integer cooAuditBy;

    private String cooAuditStatus;

    private Integer seqNo;

    private String isWatermark;

    private String isGift;

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

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public BigDecimal getActivityPrice() {
        return activityPrice;
    }

    public void setActivityPrice(BigDecimal activityPrice) {
        this.activityPrice = activityPrice;
    }

    public String getRefuseFlag() {
        return refuseFlag;
    }

    public void setRefuseFlag(String refuseFlag) {
        this.refuseFlag = refuseFlag == null ? null : refuseFlag.trim();
    }

    public Integer getOperateAuditBy() {
        return operateAuditBy;
    }

    public void setOperateAuditBy(Integer operateAuditBy) {
        this.operateAuditBy = operateAuditBy;
    }

    public String getOperateAuditStatus() {
        return operateAuditStatus;
    }

    public void setOperateAuditStatus(String operateAuditStatus) {
        this.operateAuditStatus = operateAuditStatus == null ? null : operateAuditStatus.trim();
    }

    public Integer getDesignAuditBy() {
        return designAuditBy;
    }

    public void setDesignAuditBy(Integer designAuditBy) {
        this.designAuditBy = designAuditBy;
    }

    public String getDesignAuditStatus() {
        return designAuditStatus;
    }

    public void setDesignAuditStatus(String designAuditStatus) {
        this.designAuditStatus = designAuditStatus == null ? null : designAuditStatus.trim();
    }

    public Integer getLawAuditBy() {
        return lawAuditBy;
    }

    public void setLawAuditBy(Integer lawAuditBy) {
        this.lawAuditBy = lawAuditBy;
    }

    public String getLawAuditStatus() {
        return lawAuditStatus;
    }

    public void setLawAuditStatus(String lawAuditStatus) {
        this.lawAuditStatus = lawAuditStatus == null ? null : lawAuditStatus.trim();
    }

    public Integer getCooAuditBy() {
        return cooAuditBy;
    }

    public void setCooAuditBy(Integer cooAuditBy) {
        this.cooAuditBy = cooAuditBy;
    }

    public String getCooAuditStatus() {
        return cooAuditStatus;
    }

    public void setCooAuditStatus(String cooAuditStatus) {
        this.cooAuditStatus = cooAuditStatus == null ? null : cooAuditStatus.trim();
    }

    public Integer getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(Integer seqNo) {
        this.seqNo = seqNo;
    }

    public String getIsWatermark() {
        return isWatermark;
    }

    public void setIsWatermark(String isWatermark) {
        this.isWatermark = isWatermark == null ? null : isWatermark.trim();
    }

    public String getIsGift() {
        return isGift;
    }

    public void setIsGift(String isGift) {
        this.isGift = isGift == null ? null : isGift.trim();
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