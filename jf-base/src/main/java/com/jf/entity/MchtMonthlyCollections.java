package com.jf.entity;

import java.math.BigDecimal;
import java.util.Date;

public class MchtMonthlyCollections {
    private Integer id;

    private String collectionDate;

    private Integer mchtId;

    private BigDecimal beginUnpay;

    private BigDecimal orderAmount;

    private BigDecimal settleAmount;

    private BigDecimal refundAmount;

    private BigDecimal returnAmount;

    private BigDecimal returnOrderAmount;

    private BigDecimal needPayAmount;

    private BigDecimal payAmount;

    private BigDecimal endUnpay;

    private String createBy;

    private Date createDate;

    private Integer updateBy;

    private Date updateDate;

    private String remarks;

    private String delFlag;

    private BigDecimal deductionDepositTotal;

    private BigDecimal collectionProductAmount;

    private Integer collectionProductCount;

    private BigDecimal collectionMchtPreferential;

    private BigDecimal collectionPlatformPreferential;

    private BigDecimal collectionIntegralPreferential;

    private BigDecimal collectionCommissionAmount;

    private BigDecimal refundProductAmount;

    private Integer refundProductCount;

    private BigDecimal refundMchtPreferential;

    private BigDecimal refundPlatformPreferential;

    private BigDecimal refundIntegralPreferential;

    private BigDecimal refundCommissionAmount;

    private BigDecimal discount;

    private String discountDesc;

    private BigDecimal discountedEndNeedPay;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCollectionDate() {
        return collectionDate;
    }

    public void setCollectionDate(String collectionDate) {
        this.collectionDate = collectionDate == null ? null : collectionDate.trim();
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

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
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

    public BigDecimal getEndUnpay() {
        return endUnpay;
    }

    public void setEndUnpay(BigDecimal endUnpay) {
        this.endUnpay = endUnpay;
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

    public BigDecimal getDeductionDepositTotal() {
        return deductionDepositTotal;
    }

    public void setDeductionDepositTotal(BigDecimal deductionDepositTotal) {
        this.deductionDepositTotal = deductionDepositTotal;
    }

    public BigDecimal getCollectionProductAmount() {
        return collectionProductAmount;
    }

    public void setCollectionProductAmount(BigDecimal collectionProductAmount) {
        this.collectionProductAmount = collectionProductAmount;
    }

    public Integer getCollectionProductCount() {
        return collectionProductCount;
    }

    public void setCollectionProductCount(Integer collectionProductCount) {
        this.collectionProductCount = collectionProductCount;
    }

    public BigDecimal getCollectionMchtPreferential() {
        return collectionMchtPreferential;
    }

    public void setCollectionMchtPreferential(BigDecimal collectionMchtPreferential) {
        this.collectionMchtPreferential = collectionMchtPreferential;
    }

    public BigDecimal getCollectionPlatformPreferential() {
        return collectionPlatformPreferential;
    }

    public void setCollectionPlatformPreferential(BigDecimal collectionPlatformPreferential) {
        this.collectionPlatformPreferential = collectionPlatformPreferential;
    }

    public BigDecimal getCollectionIntegralPreferential() {
        return collectionIntegralPreferential;
    }

    public void setCollectionIntegralPreferential(BigDecimal collectionIntegralPreferential) {
        this.collectionIntegralPreferential = collectionIntegralPreferential;
    }

    public BigDecimal getCollectionCommissionAmount() {
        return collectionCommissionAmount;
    }

    public void setCollectionCommissionAmount(BigDecimal collectionCommissionAmount) {
        this.collectionCommissionAmount = collectionCommissionAmount;
    }

    public BigDecimal getRefundProductAmount() {
        return refundProductAmount;
    }

    public void setRefundProductAmount(BigDecimal refundProductAmount) {
        this.refundProductAmount = refundProductAmount;
    }

    public Integer getRefundProductCount() {
        return refundProductCount;
    }

    public void setRefundProductCount(Integer refundProductCount) {
        this.refundProductCount = refundProductCount;
    }

    public BigDecimal getRefundMchtPreferential() {
        return refundMchtPreferential;
    }

    public void setRefundMchtPreferential(BigDecimal refundMchtPreferential) {
        this.refundMchtPreferential = refundMchtPreferential;
    }

    public BigDecimal getRefundPlatformPreferential() {
        return refundPlatformPreferential;
    }

    public void setRefundPlatformPreferential(BigDecimal refundPlatformPreferential) {
        this.refundPlatformPreferential = refundPlatformPreferential;
    }

    public BigDecimal getRefundIntegralPreferential() {
        return refundIntegralPreferential;
    }

    public void setRefundIntegralPreferential(BigDecimal refundIntegralPreferential) {
        this.refundIntegralPreferential = refundIntegralPreferential;
    }

    public BigDecimal getRefundCommissionAmount() {
        return refundCommissionAmount;
    }

    public void setRefundCommissionAmount(BigDecimal refundCommissionAmount) {
        this.refundCommissionAmount = refundCommissionAmount;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public String getDiscountDesc() {
        return discountDesc;
    }

    public void setDiscountDesc(String discountDesc) {
        this.discountDesc = discountDesc == null ? null : discountDesc.trim();
    }

    public BigDecimal getDiscountedEndNeedPay() {
        return discountedEndNeedPay;
    }

    public void setDiscountedEndNeedPay(BigDecimal discountedEndNeedPay) {
        this.discountedEndNeedPay = discountedEndNeedPay;
    }
}