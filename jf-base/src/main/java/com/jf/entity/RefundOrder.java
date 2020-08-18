package com.jf.entity;

import java.math.BigDecimal;
import java.util.Date;

public class RefundOrder {
    private Integer id;

    private Integer combineOrderId;

    private String serviceType;

    private String orderType;

    private Integer serviceOrderId;

    private Integer combineDepositOrderId;

    private BigDecimal refundAmount;

    private Date refundAgreeDate;

    private String refundMethod;

    private String refundReqNo;

    private String failedReason;

    private Integer tryTimes;

    private String status;

    private Date successDate;

    private String refundCode;

    private Date refundRegisterDate;

    private Integer cashierStaffId;

    private String wxRefundId;

    private String zfbRefundId;

    private String ylRefundId;

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

    public Integer getCombineOrderId() {
        return combineOrderId;
    }

    public void setCombineOrderId(Integer combineOrderId) {
        this.combineOrderId = combineOrderId;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType == null ? null : serviceType.trim();
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType == null ? null : orderType.trim();
    }

    public Integer getServiceOrderId() {
        return serviceOrderId;
    }

    public void setServiceOrderId(Integer serviceOrderId) {
        this.serviceOrderId = serviceOrderId;
    }

    public Integer getCombineDepositOrderId() {
        return combineDepositOrderId;
    }

    public void setCombineDepositOrderId(Integer combineDepositOrderId) {
        this.combineDepositOrderId = combineDepositOrderId;
    }

    public BigDecimal getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(BigDecimal refundAmount) {
        this.refundAmount = refundAmount;
    }

    public Date getRefundAgreeDate() {
        return refundAgreeDate;
    }

    public void setRefundAgreeDate(Date refundAgreeDate) {
        this.refundAgreeDate = refundAgreeDate;
    }

    public String getRefundMethod() {
        return refundMethod;
    }

    public void setRefundMethod(String refundMethod) {
        this.refundMethod = refundMethod == null ? null : refundMethod.trim();
    }

    public String getRefundReqNo() {
        return refundReqNo;
    }

    public void setRefundReqNo(String refundReqNo) {
        this.refundReqNo = refundReqNo == null ? null : refundReqNo.trim();
    }

    public String getFailedReason() {
        return failedReason;
    }

    public void setFailedReason(String failedReason) {
        this.failedReason = failedReason == null ? null : failedReason.trim();
    }

    public Integer getTryTimes() {
        return tryTimes;
    }

    public void setTryTimes(Integer tryTimes) {
        this.tryTimes = tryTimes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getSuccessDate() {
        return successDate;
    }

    public void setSuccessDate(Date successDate) {
        this.successDate = successDate;
    }

    public String getRefundCode() {
        return refundCode;
    }

    public void setRefundCode(String refundCode) {
        this.refundCode = refundCode == null ? null : refundCode.trim();
    }

    public Date getRefundRegisterDate() {
        return refundRegisterDate;
    }

    public void setRefundRegisterDate(Date refundRegisterDate) {
        this.refundRegisterDate = refundRegisterDate;
    }

    public Integer getCashierStaffId() {
        return cashierStaffId;
    }

    public void setCashierStaffId(Integer cashierStaffId) {
        this.cashierStaffId = cashierStaffId;
    }

    public String getWxRefundId() {
        return wxRefundId;
    }

    public void setWxRefundId(String wxRefundId) {
        this.wxRefundId = wxRefundId == null ? null : wxRefundId.trim();
    }

    public String getZfbRefundId() {
        return zfbRefundId;
    }

    public void setZfbRefundId(String zfbRefundId) {
        this.zfbRefundId = zfbRefundId == null ? null : zfbRefundId.trim();
    }

    public String getYlRefundId() {
        return ylRefundId;
    }

    public void setYlRefundId(String ylRefundId) {
        this.ylRefundId = ylRefundId == null ? null : ylRefundId.trim();
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