package com.jf.entity;

import com.jf.common.ext.core.Model;

import java.util.Date;

public class MchtCloseApply extends Model {
    private Integer id;

    private Integer mchtId;

    private Date stopBeginDate;

    private String stopBeginStatus;

    private Date stopEndDate;

    private String stopEndStatus;

    private String closeFlag;

    private Date closeBeginDate;

    private String closeBeginStatus;

    private Date closeEndDate;

    private String closeEndStatus;

    private String status;

    private Integer requestBy;

    private String requestType;

    private Date requestDate;

    private String requestRemarks;

    private Integer operateAuditBy;

    private String operateAuditStatus;

    private Date operateAuditDate;

    private String operateAuditRemarks;

    private Integer merchantsAuditBy;

    private String merchantsAuditStatus;

    private Date merchantsAuditDate;

    private String merchantsAuditRemarks;

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

    public Integer getMchtId() {
        return mchtId;
    }

    public void setMchtId(Integer mchtId) {
        this.mchtId = mchtId;
    }

    public Date getStopBeginDate() {
        return stopBeginDate;
    }

    public void setStopBeginDate(Date stopBeginDate) {
        this.stopBeginDate = stopBeginDate;
    }

    public String getStopBeginStatus() {
        return stopBeginStatus;
    }

    public void setStopBeginStatus(String stopBeginStatus) {
        this.stopBeginStatus = stopBeginStatus == null ? null : stopBeginStatus.trim();
    }

    public Date getStopEndDate() {
        return stopEndDate;
    }

    public void setStopEndDate(Date stopEndDate) {
        this.stopEndDate = stopEndDate;
    }

    public String getStopEndStatus() {
        return stopEndStatus;
    }

    public void setStopEndStatus(String stopEndStatus) {
        this.stopEndStatus = stopEndStatus == null ? null : stopEndStatus.trim();
    }

    public String getCloseFlag() {
        return closeFlag;
    }

    public void setCloseFlag(String closeFlag) {
        this.closeFlag = closeFlag == null ? null : closeFlag.trim();
    }

    public Date getCloseBeginDate() {
        return closeBeginDate;
    }

    public void setCloseBeginDate(Date closeBeginDate) {
        this.closeBeginDate = closeBeginDate;
    }

    public String getCloseBeginStatus() {
        return closeBeginStatus;
    }

    public void setCloseBeginStatus(String closeBeginStatus) {
        this.closeBeginStatus = closeBeginStatus == null ? null : closeBeginStatus.trim();
    }

    public Date getCloseEndDate() {
        return closeEndDate;
    }

    public void setCloseEndDate(Date closeEndDate) {
        this.closeEndDate = closeEndDate;
    }

    public String getCloseEndStatus() {
        return closeEndStatus;
    }

    public void setCloseEndStatus(String closeEndStatus) {
        this.closeEndStatus = closeEndStatus == null ? null : closeEndStatus.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Integer getRequestBy() {
        return requestBy;
    }

    public void setRequestBy(Integer requestBy) {
        this.requestBy = requestBy;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType == null ? null : requestType.trim();
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public String getRequestRemarks() {
        return requestRemarks;
    }

    public void setRequestRemarks(String requestRemarks) {
        this.requestRemarks = requestRemarks == null ? null : requestRemarks.trim();
    }

    public Integer getOperateAuditBy() {
        return operateAuditBy;
    }

    public void setOperateAuditBy(Integer operateAuditBy) {
        this.operateAuditBy = operateAuditBy;
    }

    public String getOperateAuditStatus() {
        return operateAuditStatus;
    }

    public void setOperateAuditStatus(String operateAuditStatus) {
        this.operateAuditStatus = operateAuditStatus == null ? null : operateAuditStatus.trim();
    }

    public Date getOperateAuditDate() {
        return operateAuditDate;
    }

    public void setOperateAuditDate(Date operateAuditDate) {
        this.operateAuditDate = operateAuditDate;
    }

    public String getOperateAuditRemarks() {
        return operateAuditRemarks;
    }

    public void setOperateAuditRemarks(String operateAuditRemarks) {
        this.operateAuditRemarks = operateAuditRemarks == null ? null : operateAuditRemarks.trim();
    }

    public Integer getMerchantsAuditBy() {
        return merchantsAuditBy;
    }

    public void setMerchantsAuditBy(Integer merchantsAuditBy) {
        this.merchantsAuditBy = merchantsAuditBy;
    }

    public String getMerchantsAuditStatus() {
        return merchantsAuditStatus;
    }

    public void setMerchantsAuditStatus(String merchantsAuditStatus) {
        this.merchantsAuditStatus = merchantsAuditStatus == null ? null : merchantsAuditStatus.trim();
    }

    public Date getMerchantsAuditDate() {
        return merchantsAuditDate;
    }

    public void setMerchantsAuditDate(Date merchantsAuditDate) {
        this.merchantsAuditDate = merchantsAuditDate;
    }

    public String getMerchantsAuditRemarks() {
        return merchantsAuditRemarks;
    }

    public void setMerchantsAuditRemarks(String merchantsAuditRemarks) {
        this.merchantsAuditRemarks = merchantsAuditRemarks == null ? null : merchantsAuditRemarks.trim();
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