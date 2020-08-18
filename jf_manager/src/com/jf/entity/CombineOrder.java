package com.jf.entity;

import java.math.BigDecimal;
import java.util.Date;

public class CombineOrder {
    private Integer id;

    private String combineOrderCode;

    private Integer memberId;

    private String memberNick;

    private Integer addressId;

    private String receiverName;

    private String receiverPhone;

    private String receiverAddress;

    private BigDecimal totalAmount;

    private BigDecimal totalPayAmount;

    private BigDecimal totalPlatformPreferential;

    private BigDecimal totalMchtPreferential;

    private BigDecimal totalIntegralPreferential;

    private String status;

    private String cancelType;

    private String cancelReason;

    private Date cancelDate;

    private Date statusDate;

    private String isUserDel;

    private Integer paymentId;

    private String paymentNo;

    private Date payDate;

    private String payStatus;

    private String financialStatus;

    private String financialNo;

    private Date collectionRegisterDate;

    private Integer financialStaff;

    private Date financialUpdateDate;

    private String sourceClient;

    private String orderType;

    private String payExtraInfo;

    private String orderExtraInfo;

    private BigDecimal freight;
    
    private  String promotionType;
    
    private BigDecimal distributionAmount;

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

    public String getCombineOrderCode() {
        return combineOrderCode;
    }

    public void setCombineOrderCode(String combineOrderCode) {
        this.combineOrderCode = combineOrderCode == null ? null : combineOrderCode.trim();
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getMemberNick() {
        return memberNick;
    }

    public void setMemberNick(String memberNick) {
        this.memberNick = memberNick == null ? null : memberNick.trim();
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName == null ? null : receiverName.trim();
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone == null ? null : receiverPhone.trim();
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress == null ? null : receiverAddress.trim();
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getTotalPayAmount() {
        return totalPayAmount;
    }

    public void setTotalPayAmount(BigDecimal totalPayAmount) {
        this.totalPayAmount = totalPayAmount;
    }

    public BigDecimal getTotalPlatformPreferential() {
        return totalPlatformPreferential;
    }

    public void setTotalPlatformPreferential(BigDecimal totalPlatformPreferential) {
        this.totalPlatformPreferential = totalPlatformPreferential;
    }

    public BigDecimal getTotalMchtPreferential() {
        return totalMchtPreferential;
    }

    public void setTotalMchtPreferential(BigDecimal totalMchtPreferential) {
        this.totalMchtPreferential = totalMchtPreferential;
    }

    public BigDecimal getTotalIntegralPreferential() {
        return totalIntegralPreferential;
    }

    public void setTotalIntegralPreferential(BigDecimal totalIntegralPreferential) {
        this.totalIntegralPreferential = totalIntegralPreferential;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getCancelType() {
        return cancelType;
    }

    public void setCancelType(String cancelType) {
        this.cancelType = cancelType == null ? null : cancelType.trim();
    }

    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason == null ? null : cancelReason.trim();
    }

    public Date getCancelDate() {
        return cancelDate;
    }

    public void setCancelDate(Date cancelDate) {
        this.cancelDate = cancelDate;
    }

    public Date getStatusDate() {
        return statusDate;
    }

    public void setStatusDate(Date statusDate) {
        this.statusDate = statusDate;
    }

    public String getIsUserDel() {
        return isUserDel;
    }

    public void setIsUserDel(String isUserDel) {
        this.isUserDel = isUserDel == null ? null : isUserDel.trim();
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

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus == null ? null : payStatus.trim();
    }

    public String getFinancialStatus() {
        return financialStatus;
    }

    public void setFinancialStatus(String financialStatus) {
        this.financialStatus = financialStatus == null ? null : financialStatus.trim();
    }

    public String getFinancialNo() {
        return financialNo;
    }

    public void setFinancialNo(String financialNo) {
        this.financialNo = financialNo == null ? null : financialNo.trim();
    }

    public Date getCollectionRegisterDate() {
        return collectionRegisterDate;
    }

    public void setCollectionRegisterDate(Date collectionRegisterDate) {
        this.collectionRegisterDate = collectionRegisterDate;
    }

    public Integer getFinancialStaff() {
        return financialStaff;
    }

    public void setFinancialStaff(Integer financialStaff) {
        this.financialStaff = financialStaff;
    }

    public Date getFinancialUpdateDate() {
        return financialUpdateDate;
    }

    public void setFinancialUpdateDate(Date financialUpdateDate) {
        this.financialUpdateDate = financialUpdateDate;
    }

    public String getSourceClient() {
        return sourceClient;
    }

    public void setSourceClient(String sourceClient) {
        this.sourceClient = sourceClient == null ? null : sourceClient.trim();
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType == null ? null : orderType.trim();
    }

    public String getPayExtraInfo() {
        return payExtraInfo;
    }

    public void setPayExtraInfo(String payExtraInfo) {
        this.payExtraInfo = payExtraInfo == null ? null : payExtraInfo.trim();
    }

    public String getOrderExtraInfo() {
        return orderExtraInfo;
    }

    public void setOrderExtraInfo(String orderExtraInfo) {
        this.orderExtraInfo = orderExtraInfo == null ? null : orderExtraInfo.trim();
    }

    public BigDecimal getFreight() {
        return freight;
    }

    public void setFreight(BigDecimal freight) {
        this.freight = freight;
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

	public String getPromotionType() {
		return promotionType;
	}

	public void setPromotionType(String promotionType) {
		this.promotionType = promotionType;
	}

	public BigDecimal getDistributionAmount() {
		return distributionAmount;
	}

	public void setDistributionAmount(BigDecimal distributionAmount) {
		this.distributionAmount = distributionAmount;
	}
    
}