package com.jf.entity;

import java.math.BigDecimal;
import java.util.Date;

public class SubDepositOrder {
    private Integer id;

    private Integer combineDepositOrderId;

    private String subDepositOrderCode;

    private Integer mchtId;

    private String mchtType;

    private Integer memberId;

    private Integer activityPreSellProductId;

    private Integer productId;

    private Integer productItemId;

    private String skuPic;

    private String productName;

    private String artNo;

    private String brandName;

    private String productPropDesc;

    private String sku;

    private BigDecimal tagPrice;

    private BigDecimal salePrice;

    private BigDecimal mallPrice;

    private BigDecimal settlePrice;

    private BigDecimal deposit;

    private BigDecimal deductAmount;

    private Integer quantity;

    private BigDecimal payAmount;

    private String status;

    private Integer orderDtlId;

    private BigDecimal popCommissionRate;

    private BigDecimal settleAmount;

    private BigDecimal commissionAmount;

    private String memberRemarks;

    private String mchtRemarks;

    private Date payDate;

    private Date completeDate;

    private Integer mchtSettleOrderId;

    private Integer shoppingCartId;

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

    public Integer getCombineDepositOrderId() {
        return combineDepositOrderId;
    }

    public void setCombineDepositOrderId(Integer combineDepositOrderId) {
        this.combineDepositOrderId = combineDepositOrderId;
    }

    public String getSubDepositOrderCode() {
        return subDepositOrderCode;
    }

    public void setSubDepositOrderCode(String subDepositOrderCode) {
        this.subDepositOrderCode = subDepositOrderCode == null ? null : subDepositOrderCode.trim();
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

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getActivityPreSellProductId() {
        return activityPreSellProductId;
    }

    public void setActivityPreSellProductId(Integer activityPreSellProductId) {
        this.activityPreSellProductId = activityPreSellProductId;
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

    public String getSkuPic() {
        return skuPic;
    }

    public void setSkuPic(String skuPic) {
        this.skuPic = skuPic == null ? null : skuPic.trim();
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

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku == null ? null : sku.trim();
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

    public BigDecimal getMallPrice() {
        return mallPrice;
    }

    public void setMallPrice(BigDecimal mallPrice) {
        this.mallPrice = mallPrice;
    }

    public BigDecimal getSettlePrice() {
        return settlePrice;
    }

    public void setSettlePrice(BigDecimal settlePrice) {
        this.settlePrice = settlePrice;
    }

    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    public BigDecimal getDeductAmount() {
        return deductAmount;
    }

    public void setDeductAmount(BigDecimal deductAmount) {
        this.deductAmount = deductAmount;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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

    public Integer getOrderDtlId() {
        return orderDtlId;
    }

    public void setOrderDtlId(Integer orderDtlId) {
        this.orderDtlId = orderDtlId;
    }

    public BigDecimal getPopCommissionRate() {
        return popCommissionRate;
    }

    public void setPopCommissionRate(BigDecimal popCommissionRate) {
        this.popCommissionRate = popCommissionRate;
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

    public String getMemberRemarks() {
        return memberRemarks;
    }

    public void setMemberRemarks(String memberRemarks) {
        this.memberRemarks = memberRemarks == null ? null : memberRemarks.trim();
    }

    public String getMchtRemarks() {
        return mchtRemarks;
    }

    public void setMchtRemarks(String mchtRemarks) {
        this.mchtRemarks = mchtRemarks == null ? null : mchtRemarks.trim();
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public Date getCompleteDate() {
        return completeDate;
    }

    public void setCompleteDate(Date completeDate) {
        this.completeDate = completeDate;
    }

    public Integer getMchtSettleOrderId() {
        return mchtSettleOrderId;
    }

    public void setMchtSettleOrderId(Integer mchtSettleOrderId) {
        this.mchtSettleOrderId = mchtSettleOrderId;
    }

    public Integer getShoppingCartId() {
        return shoppingCartId;
    }

    public void setShoppingCartId(Integer shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
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