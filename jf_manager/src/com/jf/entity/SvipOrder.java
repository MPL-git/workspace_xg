package com.jf.entity;

import java.math.BigDecimal;
import java.util.Date;

public class SvipOrder {
    private Integer id;

    private String orderCode;

    private String buyType;

    private Integer memberId;

    private Integer svipMemberSettingId;

    private Integer yearsOfPurchase;

    private String sourceClient;

    private String ip;

    private Integer paymentId;

    private String paymentNo;

    private BigDecimal payAmount;

    private String status;

    private Date payDate;

    private Date dealCompleteDate;

    private String payStatus;

    private Date refundDate;

    private Integer refundBy;

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

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode == null ? null : orderCode.trim();
    }

    public String getBuyType() {
        return buyType;
    }

    public void setBuyType(String buyType) {
        this.buyType = buyType == null ? null : buyType.trim();
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getSvipMemberSettingId() {
        return svipMemberSettingId;
    }

    public void setSvipMemberSettingId(Integer svipMemberSettingId) {
        this.svipMemberSettingId = svipMemberSettingId;
    }

    public Integer getYearsOfPurchase() {
        return yearsOfPurchase;
    }

    public void setYearsOfPurchase(Integer yearsOfPurchase) {
        this.yearsOfPurchase = yearsOfPurchase;
    }

    public String getSourceClient() {
        return sourceClient;
    }

    public void setSourceClient(String sourceClient) {
        this.sourceClient = sourceClient == null ? null : sourceClient.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public Integer getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }

    public String getPaymentNo() {
        return paymentNo;
    }

    public void setPaymentNo(String paymentNo) {
        this.paymentNo = paymentNo == null ? null : paymentNo.trim();
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public Date getDealCompleteDate() {
        return dealCompleteDate;
    }

    public void setDealCompleteDate(Date dealCompleteDate) {
        this.dealCompleteDate = dealCompleteDate;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus == null ? null : payStatus.trim();
    }

    public Date getRefundDate() {
        return refundDate;
    }

    public void setRefundDate(Date refundDate) {
        this.refundDate = refundDate;
    }

    public Integer getRefundBy() {
        return refundBy;
    }

    public void setRefundBy(Integer refundBy) {
        this.refundBy = refundBy;
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