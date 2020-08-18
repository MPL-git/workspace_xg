package com.jf.entity;

import com.jf.common.ext.core.Model;

import java.math.BigDecimal;
import java.util.Date;

public class CustomerServiceOrder extends Model {
    private Integer id;

    private Integer subOrderId;

    private Integer orderDtlId;

    private String orderCode;

    private String serviceType;

    private String status;

    private String proStatus;

    private String contactPhone;

    private String reason;

    private Integer quantity;

    private BigDecimal amount;

    private BigDecimal depositAmount;

    private String mchtExpressCompany;

    private String mchtExpressNo;

    private String memberExpressCompany;

    private String memberExpressNo;

    private Integer mchtSettleOrderId;

    private String initiator;

    private String isAllowMchtModify;

    private String addressType;

    private String supplierAddress;

    private Integer cloudProductAfterTempletId;

    private String supplierRemarks;

    private String supplierRejectReason;

    private String productReturnConsignee;

    private String productReturnAddress;

    private String productReturnContactPhone;

    private String productReturnRemarks;

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

    public Integer getOrderDtlId() {
        return orderDtlId;
    }

    public void setOrderDtlId(Integer orderDtlId) {
        this.orderDtlId = orderDtlId;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode == null ? null : orderCode.trim();
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType == null ? null : serviceType.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getProStatus() {
        return proStatus;
    }

    public void setProStatus(String proStatus) {
        this.proStatus = proStatus == null ? null : proStatus.trim();
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone == null ? null : contactPhone.trim();
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(BigDecimal depositAmount) {
        this.depositAmount = depositAmount;
    }

    public String getMchtExpressCompany() {
        return mchtExpressCompany;
    }

    public void setMchtExpressCompany(String mchtExpressCompany) {
        this.mchtExpressCompany = mchtExpressCompany == null ? null : mchtExpressCompany.trim();
    }

    public String getMchtExpressNo() {
        return mchtExpressNo;
    }

    public void setMchtExpressNo(String mchtExpressNo) {
        this.mchtExpressNo = mchtExpressNo == null ? null : mchtExpressNo.trim();
    }

    public String getMemberExpressCompany() {
        return memberExpressCompany;
    }

    public void setMemberExpressCompany(String memberExpressCompany) {
        this.memberExpressCompany = memberExpressCompany == null ? null : memberExpressCompany.trim();
    }

    public String getMemberExpressNo() {
        return memberExpressNo;
    }

    public void setMemberExpressNo(String memberExpressNo) {
        this.memberExpressNo = memberExpressNo == null ? null : memberExpressNo.trim();
    }

    public Integer getMchtSettleOrderId() {
        return mchtSettleOrderId;
    }

    public void setMchtSettleOrderId(Integer mchtSettleOrderId) {
        this.mchtSettleOrderId = mchtSettleOrderId;
    }

    public String getInitiator() {
        return initiator;
    }

    public void setInitiator(String initiator) {
        this.initiator = initiator == null ? null : initiator.trim();
    }

    public String getIsAllowMchtModify() {
        return isAllowMchtModify;
    }

    public void setIsAllowMchtModify(String isAllowMchtModify) {
        this.isAllowMchtModify = isAllowMchtModify == null ? null : isAllowMchtModify.trim();
    }

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType == null ? null : addressType.trim();
    }

    public String getSupplierAddress() {
        return supplierAddress;
    }

    public void setSupplierAddress(String supplierAddress) {
        this.supplierAddress = supplierAddress == null ? null : supplierAddress.trim();
    }

    public Integer getCloudProductAfterTempletId() {
        return cloudProductAfterTempletId;
    }

    public void setCloudProductAfterTempletId(Integer cloudProductAfterTempletId) {
        this.cloudProductAfterTempletId = cloudProductAfterTempletId;
    }

    public String getSupplierRemarks() {
        return supplierRemarks;
    }

    public void setSupplierRemarks(String supplierRemarks) {
        this.supplierRemarks = supplierRemarks == null ? null : supplierRemarks.trim();
    }

    public String getSupplierRejectReason() {
        return supplierRejectReason;
    }

    public void setSupplierRejectReason(String supplierRejectReason) {
        this.supplierRejectReason = supplierRejectReason == null ? null : supplierRejectReason.trim();
    }

    public String getProductReturnConsignee() {
        return productReturnConsignee;
    }

    public void setProductReturnConsignee(String productReturnConsignee) {
        this.productReturnConsignee = productReturnConsignee == null ? null : productReturnConsignee.trim();
    }

    public String getProductReturnAddress() {
        return productReturnAddress;
    }

    public void setProductReturnAddress(String productReturnAddress) {
        this.productReturnAddress = productReturnAddress == null ? null : productReturnAddress.trim();
    }

    public String getProductReturnContactPhone() {
        return productReturnContactPhone;
    }

    public void setProductReturnContactPhone(String productReturnContactPhone) {
        this.productReturnContactPhone = productReturnContactPhone == null ? null : productReturnContactPhone.trim();
    }

    public String getProductReturnRemarks() {
        return productReturnRemarks;
    }

    public void setProductReturnRemarks(String productReturnRemarks) {
        this.productReturnRemarks = productReturnRemarks == null ? null : productReturnRemarks.trim();
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