package com.jf.entity;

import java.math.BigDecimal;
import java.util.Date;

public class CustomerServiceOrder {
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

    private String mchtExpressCompany;

    private String mchtExpressNo;

    private String memberExpressCompany;

    private String memberExpressNo;

    private Integer mchtSettleOrderId;

    private String initiator;

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