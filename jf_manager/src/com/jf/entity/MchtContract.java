package com.jf.entity;

import com.jf.common.ext.core.Model;

import java.util.Date;

public class MchtContract extends Model {
    private Integer id;

    private Integer mchtId;

    private String contractCode;

    private String isNeedUpload;

    private Date uploadDate;

    private Date auditDate;

    private String auditStatus;

    private String auditRemarks;

    private String isMchtSend;

    private Date mchtSendDate;

    private Integer mchtExpressId;

    private String mchtExpressNo;

    private String archiveStatus;

    private Date archiveDate;

    private String archiveNo;

    private Integer archiveBy;

    private String isPlatformSend;

    private Date platformSendDate;

    private Integer platformExpressId;

    private String platformExpressNo;

    private Date beginDate;

    private Date endDate;

    private Integer lastContractId;

    private String contractType;

    private String renewProStatus;

    private String mchtNotRenewRemarks;

    private String zsNotRenewRemarks;

    private String zsRenewInnerRemarks;

    private String shopCloseReason;

    private String renewStatus;

    private String renewRemarks;

    private String fwInnerRemarks;

    private String filePath;

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

    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode == null ? null : contractCode.trim();
    }

    public String getIsNeedUpload() {
        return isNeedUpload;
    }

    public void setIsNeedUpload(String isNeedUpload) {
        this.isNeedUpload = isNeedUpload == null ? null : isNeedUpload.trim();
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public Date getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(Date auditDate) {
        this.auditDate = auditDate;
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus == null ? null : auditStatus.trim();
    }

    public String getAuditRemarks() {
        return auditRemarks;
    }

    public void setAuditRemarks(String auditRemarks) {
        this.auditRemarks = auditRemarks == null ? null : auditRemarks.trim();
    }

    public String getIsMchtSend() {
        return isMchtSend;
    }

    public void setIsMchtSend(String isMchtSend) {
        this.isMchtSend = isMchtSend == null ? null : isMchtSend.trim();
    }

    public Date getMchtSendDate() {
        return mchtSendDate;
    }

    public void setMchtSendDate(Date mchtSendDate) {
        this.mchtSendDate = mchtSendDate;
    }

    public Integer getMchtExpressId() {
        return mchtExpressId;
    }

    public void setMchtExpressId(Integer mchtExpressId) {
        this.mchtExpressId = mchtExpressId;
    }

    public String getMchtExpressNo() {
        return mchtExpressNo;
    }

    public void setMchtExpressNo(String mchtExpressNo) {
        this.mchtExpressNo = mchtExpressNo == null ? null : mchtExpressNo.trim();
    }

    public String getArchiveStatus() {
        return archiveStatus;
    }

    public void setArchiveStatus(String archiveStatus) {
        this.archiveStatus = archiveStatus == null ? null : archiveStatus.trim();
    }

    public Date getArchiveDate() {
        return archiveDate;
    }

    public void setArchiveDate(Date archiveDate) {
        this.archiveDate = archiveDate;
    }

    public String getArchiveNo() {
        return archiveNo;
    }

    public void setArchiveNo(String archiveNo) {
        this.archiveNo = archiveNo == null ? null : archiveNo.trim();
    }

    public Integer getArchiveBy() {
        return archiveBy;
    }

    public void setArchiveBy(Integer archiveBy) {
        this.archiveBy = archiveBy;
    }

    public String getIsPlatformSend() {
        return isPlatformSend;
    }

    public void setIsPlatformSend(String isPlatformSend) {
        this.isPlatformSend = isPlatformSend == null ? null : isPlatformSend.trim();
    }

    public Date getPlatformSendDate() {
        return platformSendDate;
    }

    public void setPlatformSendDate(Date platformSendDate) {
        this.platformSendDate = platformSendDate;
    }

    public Integer getPlatformExpressId() {
        return platformExpressId;
    }

    public void setPlatformExpressId(Integer platformExpressId) {
        this.platformExpressId = platformExpressId;
    }

    public String getPlatformExpressNo() {
        return platformExpressNo;
    }

    public void setPlatformExpressNo(String platformExpressNo) {
        this.platformExpressNo = platformExpressNo == null ? null : platformExpressNo.trim();
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getLastContractId() {
        return lastContractId;
    }

    public void setLastContractId(Integer lastContractId) {
        this.lastContractId = lastContractId;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType == null ? null : contractType.trim();
    }

    public String getRenewProStatus() {
        return renewProStatus;
    }

    public void setRenewProStatus(String renewProStatus) {
        this.renewProStatus = renewProStatus == null ? null : renewProStatus.trim();
    }

    public String getMchtNotRenewRemarks() {
        return mchtNotRenewRemarks;
    }

    public void setMchtNotRenewRemarks(String mchtNotRenewRemarks) {
        this.mchtNotRenewRemarks = mchtNotRenewRemarks == null ? null : mchtNotRenewRemarks.trim();
    }

    public String getZsNotRenewRemarks() {
        return zsNotRenewRemarks;
    }

    public void setZsNotRenewRemarks(String zsNotRenewRemarks) {
        this.zsNotRenewRemarks = zsNotRenewRemarks == null ? null : zsNotRenewRemarks.trim();
    }

    public String getZsRenewInnerRemarks() {
        return zsRenewInnerRemarks;
    }

    public void setZsRenewInnerRemarks(String zsRenewInnerRemarks) {
        this.zsRenewInnerRemarks = zsRenewInnerRemarks == null ? null : zsRenewInnerRemarks.trim();
    }

    public String getShopCloseReason() {
        return shopCloseReason;
    }

    public void setShopCloseReason(String shopCloseReason) {
        this.shopCloseReason = shopCloseReason == null ? null : shopCloseReason.trim();
    }

    public String getRenewStatus() {
        return renewStatus;
    }

    public void setRenewStatus(String renewStatus) {
        this.renewStatus = renewStatus == null ? null : renewStatus.trim();
    }

    public String getRenewRemarks() {
        return renewRemarks;
    }

    public void setRenewRemarks(String renewRemarks) {
        this.renewRemarks = renewRemarks == null ? null : renewRemarks.trim();
    }

    public String getFwInnerRemarks() {
        return fwInnerRemarks;
    }

    public void setFwInnerRemarks(String fwInnerRemarks) {
        this.fwInnerRemarks = fwInnerRemarks == null ? null : fwInnerRemarks.trim();
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath == null ? null : filePath.trim();
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