package com.jf.entity;

import java.util.Date;

public class MemberCoupon {
    private Integer id;

    private Integer memberId;

    private Integer couponId;

    private Date recDate;

    private Date expiryBeginDate;

    private Date expiryEndDate;

    private String status;

    private Date useDate;

    private Integer orderId;

    private String receiveType;

    private String isGive;

    private Integer fromOrderDtlId;

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

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public Date getRecDate() {
        return recDate;
    }

    public void setRecDate(Date recDate) {
        this.recDate = recDate;
    }

    public Date getExpiryBeginDate() {
        return expiryBeginDate;
    }

    public void setExpiryBeginDate(Date expiryBeginDate) {
        this.expiryBeginDate = expiryBeginDate;
    }

    public Date getExpiryEndDate() {
        return expiryEndDate;
    }

    public void setExpiryEndDate(Date expiryEndDate) {
        this.expiryEndDate = expiryEndDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getUseDate() {
        return useDate;
    }

    public void setUseDate(Date useDate) {
        this.useDate = useDate;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getReceiveType() {
        return receiveType;
    }

    public void setReceiveType(String receiveType) {
        this.receiveType = receiveType == null ? null : receiveType.trim();
    }

    public String getIsGive() {
        return isGive;
    }

    public void setIsGive(String isGive) {
        this.isGive = isGive == null ? null : isGive.trim();
    }

    public Integer getFromOrderDtlId() {
        return fromOrderDtlId;
    }

    public void setFromOrderDtlId(Integer fromOrderDtlId) {
        this.fromOrderDtlId = fromOrderDtlId;
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