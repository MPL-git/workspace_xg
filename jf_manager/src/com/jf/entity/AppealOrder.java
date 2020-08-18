package com.jf.entity;

import java.util.Date;

public class AppealOrder {
    private Integer id;

    private String orderCode;

    private Integer userId;

    private String userType;

    private String userName;

    private Integer mchtId;

    private Integer subOrderId;

    private Integer orderDtlId;

    private String appealType;

    private String status;

    private String serviceStatus;

    private Integer platformProcessor;

    private String serviceSponsorType;

    private String contactName;

    private String contactPhone;

    private String liability;

    private String platformRemarks;

    private String mchtRemarks;

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

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode == null ? null : orderCode.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType == null ? null : userType.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public Integer getMchtId() {
        return mchtId;
    }

    public void setMchtId(Integer mchtId) {
        this.mchtId = mchtId;
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

    public String getAppealType() {
        return appealType;
    }

    public void setAppealType(String appealType) {
        this.appealType = appealType == null ? null : appealType.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getServiceStatus() {
        return serviceStatus;
    }

    public void setServiceStatus(String serviceStatus) {
        this.serviceStatus = serviceStatus == null ? null : serviceStatus.trim();
    }

    public Integer getPlatformProcessor() {
        return platformProcessor;
    }

    public void setPlatformProcessor(Integer platformProcessor) {
        this.platformProcessor = platformProcessor;
    }

    public String getServiceSponsorType() {
        return serviceSponsorType;
    }

    public void setServiceSponsorType(String serviceSponsorType) {
        this.serviceSponsorType = serviceSponsorType == null ? null : serviceSponsorType.trim();
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName == null ? null : contactName.trim();
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone == null ? null : contactPhone.trim();
    }

    public String getLiability() {
        return liability;
    }

    public void setLiability(String liability) {
        this.liability = liability == null ? null : liability.trim();
    }

    public String getPlatformRemarks() {
        return platformRemarks;
    }

    public void setPlatformRemarks(String platformRemarks) {
        this.platformRemarks = platformRemarks == null ? null : platformRemarks.trim();
    }

    public String getMchtRemarks() {
        return mchtRemarks;
    }

    public void setMchtRemarks(String mchtRemarks) {
        this.mchtRemarks = mchtRemarks == null ? null : mchtRemarks.trim();
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