package com.jf.entity;

import java.math.BigDecimal;
import java.util.Date;

public class MchtSettleSituation {
    private Integer id;

    private String settleDate;

    private Integer mchtId;

    private BigDecimal beginUnpay;

    private Integer productNum;

    private BigDecimal orderAmount;

    private BigDecimal commissionAmount;

    private BigDecimal settlePriceTotal;

    private BigDecimal mchtPreferentialTotal;

    private BigDecimal settleAmount;

    private BigDecimal refundAmount;

    private BigDecimal needPayAmount;

    private BigDecimal payAmount;

    private BigDecimal unpayAmount;

    private BigDecimal endUnpay;

    private BigDecimal depositTotal;

    private BigDecimal deductionDepositTotal;

    private BigDecimal productAmount;

    private BigDecimal platformPreferential;

    private BigDecimal integralPreferential;

    private Integer returnProductNum;

    private BigDecimal returnProductAmount;

    private BigDecimal returnMchtPreferential;

    private BigDecimal returnPlatformPreferential;

    private BigDecimal returnIntegralPreferential;

    private BigDecimal returnCommissionAmount;

    private BigDecimal discount;

    private String discountRemarks;

    private BigDecimal discountEndUnpay;

    private BigDecimal returnAmount;

    private BigDecimal returnOrderAmount;

    private BigDecimal currentMonthSettleAmount;

    private BigDecimal acrossMonthSettleAmount;

    private BigDecimal acrossMonthReturnAmount;

    private BigDecimal beginSettleAmout;

    private BigDecimal endSettleAmount;

    private BigDecimal beginNotOutAccount;

    private BigDecimal endNotOutAccount;

    private String createBy;

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

    public String getSettleDate() {
        return settleDate;
    }

    public void setSettleDate(String settleDate) {
        this.settleDate = settleDate == null ? null : settleDate.trim();
    }

    public Integer getMchtId() {
        return mchtId;
    }

    public void setMchtId(Integer mchtId) {
        this.mchtId = mchtId;
    }

    public BigDecimal getBeginUnpay() {
        return beginUnpay;
    }

    public void setBeginUnpay(BigDecimal beginUnpay) {
        this.beginUnpay = beginUnpay;
    }

    public Integer getProductNum() {
        return productNum;
    }

    public void setProductNum(Integer productNum) {
        this.productNum = productNum;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public BigDecimal getCommissionAmount() {
        return commissionAmount;
    }

    public void setCommissionAmount(BigDecimal commissionAmount) {
        this.commissionAmount = commissionAmount;
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

    public BigDecimal getSettleAmount() {
        return settleAmount;
    }

    public void setSettleAmount(BigDecimal settleAmount) {
        this.settleAmount = settleAmount;
    }

    public BigDecimal getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(BigDecimal refundAmount) {
        this.refundAmount = refundAmount;
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

    public BigDecimal getUnpayAmount() {
        return unpayAmount;
    }

    public void setUnpayAmount(BigDecimal unpayAmount) {
        this.unpayAmount = unpayAmount;
    }

    public BigDecimal getEndUnpay() {
        return endUnpay;
    }

    public void setEndUnpay(BigDecimal endUnpay) {
        this.endUnpay = endUnpay;
    }

    public BigDecimal getDepositTotal() {
        return depositTotal;
    }

    public void setDepositTotal(BigDecimal depositTotal) {
        this.depositTotal = depositTotal;
    }

    public BigDecimal getDeductionDepositTotal() {
        return deductionDepositTotal;
    }

    public void setDeductionDepositTotal(BigDecimal deductionDepositTotal) {
        this.deductionDepositTotal = deductionDepositTotal;
    }

    public BigDecimal getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(BigDecimal productAmount) {
        this.productAmount = productAmount;
    }

    public BigDecimal getPlatformPreferential() {
        return platformPreferential;
    }

    public void setPlatformPreferential(BigDecimal platformPreferential) {
        this.platformPreferential = platformPreferential;
    }

    public BigDecimal getIntegralPreferential() {
        return integralPreferential;
    }

    public void setIntegralPreferential(BigDecimal integralPreferential) {
        this.integralPreferential = integralPreferential;
    }

    public Integer getReturnProductNum() {
        return returnProductNum;
    }

    public void setReturnProductNum(Integer returnProductNum) {
        this.returnProductNum = returnProductNum;
    }

    public BigDecimal getReturnProductAmount() {
        return returnProductAmount;
    }

    public void setReturnProductAmount(BigDecimal returnProductAmount) {
        this.returnProductAmount = returnProductAmount;
    }

    public BigDecimal getReturnMchtPreferential() {
        return returnMchtPreferential;
    }

    public void setReturnMchtPreferential(BigDecimal returnMchtPreferential) {
        this.returnMchtPreferential = returnMchtPreferential;
    }

    public BigDecimal getReturnPlatformPreferential() {
        return returnPlatformPreferential;
    }

    public void setReturnPlatformPreferential(BigDecimal returnPlatformPreferential) {
        this.returnPlatformPreferential = returnPlatformPreferential;
    }

    public BigDecimal getReturnIntegralPreferential() {
        return returnIntegralPreferential;
    }

    public void setReturnIntegralPreferential(BigDecimal returnIntegralPreferential) {
        this.returnIntegralPreferential = returnIntegralPreferential;
    }

    public BigDecimal getReturnCommissionAmount() {
        return returnCommissionAmount;
    }

    public void setReturnCommissionAmount(BigDecimal returnCommissionAmount) {
        this.returnCommissionAmount = returnCommissionAmount;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public String getDiscountRemarks() {
        return discountRemarks;
    }

    public void setDiscountRemarks(String discountRemarks) {
        this.discountRemarks = discountRemarks == null ? null : discountRemarks.trim();
    }

    public BigDecimal getDiscountEndUnpay() {
        return discountEndUnpay;
    }

    public void setDiscountEndUnpay(BigDecimal discountEndUnpay) {
        this.discountEndUnpay = discountEndUnpay;
    }

    public BigDecimal getReturnAmount() {
        return returnAmount;
    }

    public void setReturnAmount(BigDecimal returnAmount) {
        this.returnAmount = returnAmount;
    }

    public BigDecimal getReturnOrderAmount() {
        return returnOrderAmount;
    }

    public void setReturnOrderAmount(BigDecimal returnOrderAmount) {
        this.returnOrderAmount = returnOrderAmount;
    }

    public BigDecimal getCurrentMonthSettleAmount() {
        return currentMonthSettleAmount;
    }

    public void setCurrentMonthSettleAmount(BigDecimal currentMonthSettleAmount) {
        this.currentMonthSettleAmount = currentMonthSettleAmount;
    }

    public BigDecimal getAcrossMonthSettleAmount() {
        return acrossMonthSettleAmount;
    }

    public void setAcrossMonthSettleAmount(BigDecimal acrossMonthSettleAmount) {
        this.acrossMonthSettleAmount = acrossMonthSettleAmount;
    }

    public BigDecimal getAcrossMonthReturnAmount() {
        return acrossMonthReturnAmount;
    }

    public void setAcrossMonthReturnAmount(BigDecimal acrossMonthReturnAmount) {
        this.acrossMonthReturnAmount = acrossMonthReturnAmount;
    }

    public BigDecimal getBeginSettleAmout() {
        return beginSettleAmout;
    }

    public void setBeginSettleAmout(BigDecimal beginSettleAmout) {
        this.beginSettleAmout = beginSettleAmout;
    }

    public BigDecimal getEndSettleAmount() {
        return endSettleAmount;
    }

    public void setEndSettleAmount(BigDecimal endSettleAmount) {
        this.endSettleAmount = endSettleAmount;
    }

    public BigDecimal getBeginNotOutAccount() {
        return beginNotOutAccount;
    }

    public void setBeginNotOutAccount(BigDecimal beginNotOutAccount) {
        this.beginNotOutAccount = beginNotOutAccount;
    }

    public BigDecimal getEndNotOutAccount() {
        return endNotOutAccount;
    }

    public void setEndNotOutAccount(BigDecimal endNotOutAccount) {
        this.endNotOutAccount = endNotOutAccount;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
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