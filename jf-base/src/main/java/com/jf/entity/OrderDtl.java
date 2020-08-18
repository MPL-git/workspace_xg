package com.jf.entity;

import com.jf.common.ext.core.Model;

import java.math.BigDecimal;
import java.util.Date;

public class OrderDtl extends Model {
    private Integer id;

    private Integer subOrderId;

    private Integer activityId;

    private Integer activityAreaId;

    private Integer singleProductActivityId;

    private Integer productId;

    private Integer productItemId;

    private Integer cloudProductItemId;

    private String skuPic;

    private String sku;

    private String productName;

    private String artNo;

    private String brandName;

    private String productPropDesc;

    private Integer quantity;

    private BigDecimal tagPrice;

    private BigDecimal salePrice;

    private BigDecimal payAmount;

    private BigDecimal settlePrice;

    private BigDecimal popCommissionRate;

    private BigDecimal platformPreferential;

    private BigDecimal mchtPreferential;

    private BigDecimal integralPreferential;

    private BigDecimal allowance;

    private BigDecimal settleAmount;

    private BigDecimal commissionAmount;

    private Date refundDate;

    private String productStatus;

    private Date productStatusDate;

    private String isGive;

    private Integer mchtSettleOrderId;

    private BigDecimal freight;

    private BigDecimal originalPrice;

    private String isSvipBuy;

    private BigDecimal svipDiscount;

    private BigDecimal sellingPrice;

    private Integer dtlExpressId;

    private String dtlExpressNo;

    private String deliveryStatus;

    private Date deliveryDate;

    private String markedOutOfStock;

    private Integer distributionMemberId;

    private BigDecimal distributionAmount;

    private String distributionSettleStatus;

    private Date distributionSettleDate;

    private String merchantCode;

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

    public Integer getSubOrderId() {
        return subOrderId;
    }

    public void setSubOrderId(Integer subOrderId) {
        this.subOrderId = subOrderId;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Integer getActivityAreaId() {
        return activityAreaId;
    }

    public void setActivityAreaId(Integer activityAreaId) {
        this.activityAreaId = activityAreaId;
    }

    public Integer getSingleProductActivityId() {
        return singleProductActivityId;
    }

    public void setSingleProductActivityId(Integer singleProductActivityId) {
        this.singleProductActivityId = singleProductActivityId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getProductItemId() {
        return productItemId;
    }

    public void setProductItemId(Integer productItemId) {
        this.productItemId = productItemId;
    }

    public Integer getCloudProductItemId() {
        return cloudProductItemId;
    }

    public void setCloudProductItemId(Integer cloudProductItemId) {
        this.cloudProductItemId = cloudProductItemId;
    }

    public String getSkuPic() {
        return skuPic;
    }

    public void setSkuPic(String skuPic) {
        this.skuPic = skuPic == null ? null : skuPic.trim();
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku == null ? null : sku.trim();
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public String getArtNo() {
        return artNo;
    }

    public void setArtNo(String artNo) {
        this.artNo = artNo == null ? null : artNo.trim();
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName == null ? null : brandName.trim();
    }

    public String getProductPropDesc() {
        return productPropDesc;
    }

    public void setProductPropDesc(String productPropDesc) {
        this.productPropDesc = productPropDesc == null ? null : productPropDesc.trim();
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTagPrice() {
        return tagPrice;
    }

    public void setTagPrice(BigDecimal tagPrice) {
        this.tagPrice = tagPrice;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public BigDecimal getSettlePrice() {
        return settlePrice;
    }

    public void setSettlePrice(BigDecimal settlePrice) {
        this.settlePrice = settlePrice;
    }

    public BigDecimal getPopCommissionRate() {
        return popCommissionRate;
    }

    public void setPopCommissionRate(BigDecimal popCommissionRate) {
        this.popCommissionRate = popCommissionRate;
    }

    public BigDecimal getPlatformPreferential() {
        return platformPreferential;
    }

    public void setPlatformPreferential(BigDecimal platformPreferential) {
        this.platformPreferential = platformPreferential;
    }

    public BigDecimal getMchtPreferential() {
        return mchtPreferential;
    }

    public void setMchtPreferential(BigDecimal mchtPreferential) {
        this.mchtPreferential = mchtPreferential;
    }

    public BigDecimal getIntegralPreferential() {
        return integralPreferential;
    }

    public void setIntegralPreferential(BigDecimal integralPreferential) {
        this.integralPreferential = integralPreferential;
    }

    public BigDecimal getAllowance() {
        return allowance;
    }

    public void setAllowance(BigDecimal allowance) {
        this.allowance = allowance;
    }

    public BigDecimal getSettleAmount() {
        return settleAmount;
    }

    public void setSettleAmount(BigDecimal settleAmount) {
        this.settleAmount = settleAmount;
    }

    public BigDecimal getCommissionAmount() {
        return commissionAmount;
    }

    public void setCommissionAmount(BigDecimal commissionAmount) {
        this.commissionAmount = commissionAmount;
    }

    public Date getRefundDate() {
        return refundDate;
    }

    public void setRefundDate(Date refundDate) {
        this.refundDate = refundDate;
    }

    public String getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus == null ? null : productStatus.trim();
    }

    public Date getProductStatusDate() {
        return productStatusDate;
    }

    public void setProductStatusDate(Date productStatusDate) {
        this.productStatusDate = productStatusDate;
    }

    public String getIsGive() {
        return isGive;
    }

    public void setIsGive(String isGive) {
        this.isGive = isGive == null ? null : isGive.trim();
    }

    public Integer getMchtSettleOrderId() {
        return mchtSettleOrderId;
    }

    public void setMchtSettleOrderId(Integer mchtSettleOrderId) {
        this.mchtSettleOrderId = mchtSettleOrderId;
    }

    public BigDecimal getFreight() {
        return freight;
    }

    public void setFreight(BigDecimal freight) {
        this.freight = freight;
    }

    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }

    public String getIsSvipBuy() {
        return isSvipBuy;
    }

    public void setIsSvipBuy(String isSvipBuy) {
        this.isSvipBuy = isSvipBuy == null ? null : isSvipBuy.trim();
    }

    public BigDecimal getSvipDiscount() {
        return svipDiscount;
    }

    public void setSvipDiscount(BigDecimal svipDiscount) {
        this.svipDiscount = svipDiscount;
    }

    public BigDecimal getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(BigDecimal sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public Integer getDtlExpressId() {
        return dtlExpressId;
    }

    public void setDtlExpressId(Integer dtlExpressId) {
        this.dtlExpressId = dtlExpressId;
    }

    public String getDtlExpressNo() {
        return dtlExpressNo;
    }

    public void setDtlExpressNo(String dtlExpressNo) {
        this.dtlExpressNo = dtlExpressNo == null ? null : dtlExpressNo.trim();
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus == null ? null : deliveryStatus.trim();
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getMarkedOutOfStock() {
        return markedOutOfStock;
    }

    public void setMarkedOutOfStock(String markedOutOfStock) {
        this.markedOutOfStock = markedOutOfStock == null ? null : markedOutOfStock.trim();
    }

    public Integer getDistributionMemberId() {
        return distributionMemberId;
    }

    public void setDistributionMemberId(Integer distributionMemberId) {
        this.distributionMemberId = distributionMemberId;
    }

    public BigDecimal getDistributionAmount() {
        return distributionAmount;
    }

    public void setDistributionAmount(BigDecimal distributionAmount) {
        this.distributionAmount = distributionAmount;
    }

    public String getDistributionSettleStatus() {
        return distributionSettleStatus;
    }

    public void setDistributionSettleStatus(String distributionSettleStatus) {
        this.distributionSettleStatus = distributionSettleStatus == null ? null : distributionSettleStatus.trim();
    }

    public Date getDistributionSettleDate() {
        return distributionSettleDate;
    }

    public void setDistributionSettleDate(Date distributionSettleDate) {
        this.distributionSettleDate = distributionSettleDate;
    }

    public String getMerchantCode() {
        return merchantCode;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode == null ? null : merchantCode.trim();
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