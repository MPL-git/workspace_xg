package com.jf.entity;

import java.math.BigDecimal;
import java.util.Date;

public class SubOrder {
    private Integer id;

    private Integer combineOrderId;

    private String subOrderCode;

    private Integer mchtId;

    private String mchtType;

    private String isManageSelf;

    private BigDecimal amount;

    private BigDecimal payAmount;

    private BigDecimal platformPreferential;

    private BigDecimal mchtPreferential;

    private BigDecimal integralPreferential;

    private String status;

    private Date receiptDate;

    private String receiverType;

    private String isUserDel;

    private String remarksColor;

    private Date statusDate;

    private String expressId;

    private String expressNo;

    private Date deliveryDate;

    private Date completeDate;

    private Date closeDate;

    private Date deliveryLastDate;

    private Integer deliveryOvertime;

    private Integer followBy;

    private String isComment;

    private String isAllowModifyComment;

    private BigDecimal freight;

    private Integer memberRemindCount;

    private Date memberRemindDate;

    private String isSpecial;

    private BigDecimal distributionAmount;

    private String merchantCode;

    private String auditStatus;

    private Date auditDate;

    private String auditReasonStatus;

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

    public String getSubOrderCode() {
        return subOrderCode;
    }

    public void setSubOrderCode(String subOrderCode) {
        this.subOrderCode = subOrderCode == null ? null : subOrderCode.trim();
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

    public String getIsManageSelf() {
        return isManageSelf;
    }

    public void setIsManageSelf(String isManageSelf) {
        this.isManageSelf = isManageSelf == null ? null : isManageSelf.trim();
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(Date receiptDate) {
        this.receiptDate = receiptDate;
    }

    public String getReceiverType() {
        return receiverType;
    }

    public void setReceiverType(String receiverType) {
        this.receiverType = receiverType == null ? null : receiverType.trim();
    }

    public String getIsUserDel() {
        return isUserDel;
    }

    public void setIsUserDel(String isUserDel) {
        this.isUserDel = isUserDel == null ? null : isUserDel.trim();
    }

    public String getRemarksColor() {
        return remarksColor;
    }

    public void setRemarksColor(String remarksColor) {
        this.remarksColor = remarksColor == null ? null : remarksColor.trim();
    }

    public Date getStatusDate() {
        return statusDate;
    }

    public void setStatusDate(Date statusDate) {
        this.statusDate = statusDate;
    }

    public String getExpressId() {
        return expressId;
    }

    public void setExpressId(String expressId) {
        this.expressId = expressId == null ? null : expressId.trim();
    }

    public String getExpressNo() {
        return expressNo;
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo == null ? null : expressNo.trim();
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Date getCompleteDate() {
        return completeDate;
    }

    public void setCompleteDate(Date completeDate) {
        this.completeDate = completeDate;
    }

    public Date getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
    }

    public Date getDeliveryLastDate() {
        return deliveryLastDate;
    }

    public void setDeliveryLastDate(Date deliveryLastDate) {
        this.deliveryLastDate = deliveryLastDate;
    }

    public Integer getDeliveryOvertime() {
        return deliveryOvertime;
    }

    public void setDeliveryOvertime(Integer deliveryOvertime) {
        this.deliveryOvertime = deliveryOvertime;
    }

    public Integer getFollowBy() {
        return followBy;
    }

    public void setFollowBy(Integer followBy) {
        this.followBy = followBy;
    }

    public String getIsComment() {
        return isComment;
    }

    public void setIsComment(String isComment) {
        this.isComment = isComment == null ? null : isComment.trim();
    }

    public String getIsAllowModifyComment() {
        return isAllowModifyComment;
    }

    public void setIsAllowModifyComment(String isAllowModifyComment) {
        this.isAllowModifyComment = isAllowModifyComment == null ? null : isAllowModifyComment.trim();
    }

    public BigDecimal getFreight() {
        return freight;
    }

    public void setFreight(BigDecimal freight) {
        this.freight = freight;
    }

    public Integer getMemberRemindCount() {
        return memberRemindCount;
    }

    public void setMemberRemindCount(Integer memberRemindCount) {
        this.memberRemindCount = memberRemindCount;
    }

    public Date getMemberRemindDate() {
        return memberRemindDate;
    }

    public void setMemberRemindDate(Date memberRemindDate) {
        this.memberRemindDate = memberRemindDate;
    }

    public String getIsSpecial() {
        return isSpecial;
    }

    public void setIsSpecial(String isSpecial) {
        this.isSpecial = isSpecial == null ? null : isSpecial.trim();
    }

    public BigDecimal getDistributionAmount() {
        return distributionAmount;
    }

    public void setDistributionAmount(BigDecimal distributionAmount) {
        this.distributionAmount = distributionAmount;
    }

    public String getMerchantCode() {
        return merchantCode;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode == null ? null : merchantCode.trim();
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus == null ? null : auditStatus.trim();
    }

    public Date getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(Date auditDate) {
        this.auditDate = auditDate;
    }

    public String getAuditReasonStatus() {
        return auditReasonStatus;
    }

    public void setAuditReasonStatus(String auditReasonStatus) {
        this.auditReasonStatus = auditReasonStatus == null ? null : auditReasonStatus.trim();
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