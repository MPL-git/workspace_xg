package com.jf.entity;

import com.jf.common.ext.core.Model;

import java.math.BigDecimal;
import java.util.Date;

public class MchtSettleOrder extends Model {
    private Integer id;

    private Date beginDate;

    private Date endDate;

    private Integer mchtId;

    private String mchtType;

    private Integer productNum;

    private BigDecimal settlePriceTotal;

    private BigDecimal mchtPreferentialTotal;

    private BigDecimal orderAmount;

    private BigDecimal refundAmount;

    private BigDecimal commissionAmount;

    private BigDecimal settleAmount;

    private BigDecimal depositAmount;

    private BigDecimal needPayAmount;

    private BigDecimal payAmount;

    private BigDecimal earnestMoney;

    private String confirmStatus;

    private Date mchtConfirmDate;

    private Date platformConfirmDate;

    private String mchtCollectType;

    private String mchtReceiverName;

    private String mchtReceiverPhone;

    private String mchtReceiverAddress;

    private String mchtInvoiceStatus;

    private Date mchtInvoiceDate;

    private Integer mchtInvoiceExpressId;

    private String mchtInvoiceExpressNo;

    private String platformCollectStatus;

    private String platformInvoiceStatus;

    private Date platformInvoiceDate;

    private Integer platformInvoiceExpressId;

    private String platformInvoiceExpressNo;

    private BigDecimal manageSelfFreight;

    private String payStatus;

    private Date payReadyDate;

    private Date payDate;

    private String payCode;

    private Integer payStaffId;

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

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getMchtId() {
        return mchtId;
    }

    public void setMchtId(Integer mchtId) {
        this.mchtId = mchtId;
    }

    public String getMchtType() {
        return mchtType;
    }

    public void setMchtType(String mchtType) {
        this.mchtType = mchtType == null ? null : mchtType.trim();
    }

    public Integer getProductNum() {
        return productNum;
    }

    public void setProductNum(Integer productNum) {
        this.productNum = productNum;
    }

    public BigDecimal getSettlePriceTotal() {
        return settlePriceTotal;
    }

    public void setSettlePriceTotal(BigDecimal settlePriceTotal) {
        this.settlePriceTotal = settlePriceTotal;
    }

    public BigDecimal getMchtPreferentialTotal() {
        return mchtPreferentialTotal;
    }

    public void setMchtPreferentialTotal(BigDecimal mchtPreferentialTotal) {
        this.mchtPreferentialTotal = mchtPreferentialTotal;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public BigDecimal getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(BigDecimal refundAmount) {
        this.refundAmount = refundAmount;
    }

    public BigDecimal getCommissionAmount() {
        return commissionAmount;
    }

    public void setCommissionAmount(BigDecimal commissionAmount) {
        this.commissionAmount = commissionAmount;
    }

    public BigDecimal getSettleAmount() {
        return settleAmount;
    }

    public void setSettleAmount(BigDecimal settleAmount) {
        this.settleAmount = settleAmount;
    }

    public BigDecimal getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(BigDecimal depositAmount) {
        this.depositAmount = depositAmount;
    }

    public BigDecimal getNeedPayAmount() {
        return needPayAmount;
    }

    public void setNeedPayAmount(BigDecimal needPayAmount) {
        this.needPayAmount = needPayAmount;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public BigDecimal getEarnestMoney() {
        return earnestMoney;
    }

    public void setEarnestMoney(BigDecimal earnestMoney) {
        this.earnestMoney = earnestMoney;
    }

    public String getConfirmStatus() {
        return confirmStatus;
    }

    public void setConfirmStatus(String confirmStatus) {
        this.confirmStatus = confirmStatus == null ? null : confirmStatus.trim();
    }

    public Date getMchtConfirmDate() {
        return mchtConfirmDate;
    }

    public void setMchtConfirmDate(Date mchtConfirmDate) {
        this.mchtConfirmDate = mchtConfirmDate;
    }

    public Date getPlatformConfirmDate() {
        return platformConfirmDate;
    }

    public void setPlatformConfirmDate(Date platformConfirmDate) {
        this.platformConfirmDate = platformConfirmDate;
    }

    public String getMchtCollectType() {
        return mchtCollectType;
    }

    public void setMchtCollectType(String mchtCollectType) {
        this.mchtCollectType = mchtCollectType == null ? null : mchtCollectType.trim();
    }

    public String getMchtReceiverName() {
        return mchtReceiverName;
    }

    public void setMchtReceiverName(String mchtReceiverName) {
        this.mchtReceiverName = mchtReceiverName == null ? null : mchtReceiverName.trim();
    }

    public String getMchtReceiverPhone() {
        return mchtReceiverPhone;
    }

    public void setMchtReceiverPhone(String mchtReceiverPhone) {
        this.mchtReceiverPhone = mchtReceiverPhone == null ? null : mchtReceiverPhone.trim();
    }

    public String getMchtReceiverAddress() {
        return mchtReceiverAddress;
    }

    public void setMchtReceiverAddress(String mchtReceiverAddress) {
        this.mchtReceiverAddress = mchtReceiverAddress == null ? null : mchtReceiverAddress.trim();
    }

    public String getMchtInvoiceStatus() {
        return mchtInvoiceStatus;
    }

    public void setMchtInvoiceStatus(String mchtInvoiceStatus) {
        this.mchtInvoiceStatus = mchtInvoiceStatus == null ? null : mchtInvoiceStatus.trim();
    }

    public Date getMchtInvoiceDate() {
        return mchtInvoiceDate;
    }

    public void setMchtInvoiceDate(Date mchtInvoiceDate) {
        this.mchtInvoiceDate = mchtInvoiceDate;
    }

    public Integer getMchtInvoiceExpressId() {
        return mchtInvoiceExpressId;
    }

    public void setMchtInvoiceExpressId(Integer mchtInvoiceExpressId) {
        this.mchtInvoiceExpressId = mchtInvoiceExpressId;
    }

    public String getMchtInvoiceExpressNo() {
        return mchtInvoiceExpressNo;
    }

    public void setMchtInvoiceExpressNo(String mchtInvoiceExpressNo) {
        this.mchtInvoiceExpressNo = mchtInvoiceExpressNo == null ? null : mchtInvoiceExpressNo.trim();
    }

    public String getPlatformCollectStatus() {
        return platformCollectStatus;
    }

    public void setPlatformCollectStatus(String platformCollectStatus) {
        this.platformCollectStatus = platformCollectStatus == null ? null : platformCollectStatus.trim();
    }

    public String getPlatformInvoiceStatus() {
        return platformInvoiceStatus;
    }

    public void setPlatformInvoiceStatus(String platformInvoiceStatus) {
        this.platformInvoiceStatus = platformInvoiceStatus == null ? null : platformInvoiceStatus.trim();
    }

    public Date getPlatformInvoiceDate() {
        return platformInvoiceDate;
    }

    public void setPlatformInvoiceDate(Date platformInvoiceDate) {
        this.platformInvoiceDate = platformInvoiceDate;
    }

    public Integer getPlatformInvoiceExpressId() {
        return platformInvoiceExpressId;
    }

    public void setPlatformInvoiceExpressId(Integer platformInvoiceExpressId) {
        this.platformInvoiceExpressId = platformInvoiceExpressId;
    }

    public String getPlatformInvoiceExpressNo() {
        return platformInvoiceExpressNo;
    }

    public void setPlatformInvoiceExpressNo(String platformInvoiceExpressNo) {
        this.platformInvoiceExpressNo = platformInvoiceExpressNo == null ? null : platformInvoiceExpressNo.trim();
    }

    public BigDecimal getManageSelfFreight() {
        return manageSelfFreight;
    }

    public void setManageSelfFreight(BigDecimal manageSelfFreight) {
        this.manageSelfFreight = manageSelfFreight;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus == null ? null : payStatus.trim();
    }

    public Date getPayReadyDate() {
        return payReadyDate;
    }

    public void setPayReadyDate(Date payReadyDate) {
        this.payReadyDate = payReadyDate;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public String getPayCode() {
        return payCode;
    }

    public void setPayCode(String payCode) {
        this.payCode = payCode == null ? null : payCode.trim();
    }

    public Integer getPayStaffId() {
        return payStaffId;
    }

    public void setPayStaffId(Integer payStaffId) {
        this.payStaffId = payStaffId;
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