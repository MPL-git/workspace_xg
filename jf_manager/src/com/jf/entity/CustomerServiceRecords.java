package com.jf.entity;

import java.util.Date;

public class CustomerServiceRecords {
    private Integer id;

    private Integer businessCirclesAppealOrderId;

    private Date mchtDealDate;

    private String mchtComplain;

    private String mchtProcessingProgress;

    private Date platformDealDate;

    private String platformProcessingProgress;

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

    public Integer getBusinessCirclesAppealOrderId() {
        return businessCirclesAppealOrderId;
    }

    public void setBusinessCirclesAppealOrderId(Integer businessCirclesAppealOrderId) {
        this.businessCirclesAppealOrderId = businessCirclesAppealOrderId;
    }

    public Date getMchtDealDate() {
        return mchtDealDate;
    }

    public void setMchtDealDate(Date mchtDealDate) {
        this.mchtDealDate = mchtDealDate;
    }

    public String getMchtComplain() {
        return mchtComplain;
    }

    public void setMchtComplain(String mchtComplain) {
        this.mchtComplain = mchtComplain == null ? null : mchtComplain.trim();
    }

    public String getMchtProcessingProgress() {
        return mchtProcessingProgress;
    }

    public void setMchtProcessingProgress(String mchtProcessingProgress) {
        this.mchtProcessingProgress = mchtProcessingProgress == null ? null : mchtProcessingProgress.trim();
    }

    public Date getPlatformDealDate() {
        return platformDealDate;
    }

    public void setPlatformDealDate(Date platformDealDate) {
        this.platformDealDate = platformDealDate;
    }

    public String getPlatformProcessingProgress() {
        return platformProcessingProgress;
    }

    public void setPlatformProcessingProgress(String platformProcessingProgress) {
        this.platformProcessingProgress = platformProcessingProgress == null ? null : platformProcessingProgress.trim();
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