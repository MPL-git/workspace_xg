package com.jf.entity;

import java.util.Date;

public class BusinessCirclesAppealOrder {
    private Integer id;

    private Date consumerAppealDate;

    private String registrationNumber;

    private String appealName;

    private String appealMobile;

    private String appealAddress;

    private String appealOrderType;

    private String typesOfComplaints;

    private String consumerAppealContent;

    private String appealStatus;

    private Integer customerServiceHandler;

    private Date customerServiceHandleDate;

    private String memberIds;

    private String mchtIds;

    private String mchtProductBrandIds;

    private String subOrderCode;

    private Date signForDate;

    private String memberSituation;

    private Integer mchtAppealedCount;

    private String customerServiceResult;

    private Date customerServiceResultDate;

    private String fwResult;

    private Date fwResultDate;

    private String businessCirclesResult;

    private Date businessCirclesDate;

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

    public Date getConsumerAppealDate() {
        return consumerAppealDate;
    }

    public void setConsumerAppealDate(Date consumerAppealDate) {
        this.consumerAppealDate = consumerAppealDate;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber == null ? null : registrationNumber.trim();
    }

    public String getAppealName() {
        return appealName;
    }

    public void setAppealName(String appealName) {
        this.appealName = appealName == null ? null : appealName.trim();
    }

    public String getAppealMobile() {
        return appealMobile;
    }

    public void setAppealMobile(String appealMobile) {
        this.appealMobile = appealMobile == null ? null : appealMobile.trim();
    }

    public String getAppealAddress() {
        return appealAddress;
    }

    public void setAppealAddress(String appealAddress) {
        this.appealAddress = appealAddress == null ? null : appealAddress.trim();
    }

    public String getAppealOrderType() {
        return appealOrderType;
    }

    public void setAppealOrderType(String appealOrderType) {
        this.appealOrderType = appealOrderType == null ? null : appealOrderType.trim();
    }

    public String getTypesOfComplaints() {
        return typesOfComplaints;
    }

    public void setTypesOfComplaints(String typesOfComplaints) {
        this.typesOfComplaints = typesOfComplaints == null ? null : typesOfComplaints.trim();
    }

    public String getConsumerAppealContent() {
        return consumerAppealContent;
    }

    public void setConsumerAppealContent(String consumerAppealContent) {
        this.consumerAppealContent = consumerAppealContent == null ? null : consumerAppealContent.trim();
    }

    public String getAppealStatus() {
        return appealStatus;
    }

    public void setAppealStatus(String appealStatus) {
        this.appealStatus = appealStatus == null ? null : appealStatus.trim();
    }

    public Integer getCustomerServiceHandler() {
        return customerServiceHandler;
    }

    public void setCustomerServiceHandler(Integer customerServiceHandler) {
        this.customerServiceHandler = customerServiceHandler;
    }

    public Date getCustomerServiceHandleDate() {
        return customerServiceHandleDate;
    }

    public void setCustomerServiceHandleDate(Date customerServiceHandleDate) {
        this.customerServiceHandleDate = customerServiceHandleDate;
    }

    public String getMemberIds() {
        return memberIds;
    }

    public void setMemberIds(String memberIds) {
        this.memberIds = memberIds == null ? null : memberIds.trim();
    }

    public String getMchtIds() {
        return mchtIds;
    }

    public void setMchtIds(String mchtIds) {
        this.mchtIds = mchtIds == null ? null : mchtIds.trim();
    }

    public String getMchtProductBrandIds() {
        return mchtProductBrandIds;
    }

    public void setMchtProductBrandIds(String mchtProductBrandIds) {
        this.mchtProductBrandIds = mchtProductBrandIds == null ? null : mchtProductBrandIds.trim();
    }

    public String getSubOrderCode() {
        return subOrderCode;
    }

    public void setSubOrderCode(String subOrderCode) {
        this.subOrderCode = subOrderCode == null ? null : subOrderCode.trim();
    }

    public Date getSignForDate() {
        return signForDate;
    }

    public void setSignForDate(Date signForDate) {
        this.signForDate = signForDate;
    }

    public String getMemberSituation() {
        return memberSituation;
    }

    public void setMemberSituation(String memberSituation) {
        this.memberSituation = memberSituation == null ? null : memberSituation.trim();
    }

    public Integer getMchtAppealedCount() {
        return mchtAppealedCount;
    }

    public void setMchtAppealedCount(Integer mchtAppealedCount) {
        this.mchtAppealedCount = mchtAppealedCount;
    }

    public String getCustomerServiceResult() {
        return customerServiceResult;
    }

    public void setCustomerServiceResult(String customerServiceResult) {
        this.customerServiceResult = customerServiceResult == null ? null : customerServiceResult.trim();
    }

    public Date getCustomerServiceResultDate() {
        return customerServiceResultDate;
    }

    public void setCustomerServiceResultDate(Date customerServiceResultDate) {
        this.customerServiceResultDate = customerServiceResultDate;
    }

    public String getFwResult() {
        return fwResult;
    }

    public void setFwResult(String fwResult) {
        this.fwResult = fwResult == null ? null : fwResult.trim();
    }

    public Date getFwResultDate() {
        return fwResultDate;
    }

    public void setFwResultDate(Date fwResultDate) {
        this.fwResultDate = fwResultDate;
    }

    public String getBusinessCirclesResult() {
        return businessCirclesResult;
    }

    public void setBusinessCirclesResult(String businessCirclesResult) {
        this.businessCirclesResult = businessCirclesResult == null ? null : businessCirclesResult.trim();
    }

    public Date getBusinessCirclesDate() {
        return businessCirclesDate;
    }

    public void setBusinessCirclesDate(Date businessCirclesDate) {
        this.businessCirclesDate = businessCirclesDate;
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