package com.jf.entity;

import java.util.Date;

public class MchtLicenseChg {
    private Integer id;

    private Integer mchtId;

    private String businessLicensePic;

    private String auditStatus;

    private String licenseRejectReason;

    private String archiveStatus;

    private Integer expressId;

    private String expressNo;

    private String archiveDealStatus;

    private String archiveDealRemarks;

    private String archiveDealInnerRemarks;

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

    public String getBusinessLicensePic() {
        return businessLicensePic;
    }

    public void setBusinessLicensePic(String businessLicensePic) {
        this.businessLicensePic = businessLicensePic == null ? null : businessLicensePic.trim();
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus == null ? null : auditStatus.trim();
    }

    public String getLicenseRejectReason() {
        return licenseRejectReason;
    }

    public void setLicenseRejectReason(String licenseRejectReason) {
        this.licenseRejectReason = licenseRejectReason == null ? null : licenseRejectReason.trim();
    }

    public String getArchiveStatus() {
        return archiveStatus;
    }

    public void setArchiveStatus(String archiveStatus) {
        this.archiveStatus = archiveStatus == null ? null : archiveStatus.trim();
    }

    public Integer getExpressId() {
        return expressId;
    }

    public void setExpressId(Integer expressId) {
        this.expressId = expressId;
    }

    public String getExpressNo() {
        return expressNo;
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo == null ? null : expressNo.trim();
    }

    public String getArchiveDealStatus() {
        return archiveDealStatus;
    }

    public void setArchiveDealStatus(String archiveDealStatus) {
        this.archiveDealStatus = archiveDealStatus == null ? null : archiveDealStatus.trim();
    }

    public String getArchiveDealRemarks() {
        return archiveDealRemarks;
    }

    public void setArchiveDealRemarks(String archiveDealRemarks) {
        this.archiveDealRemarks = archiveDealRemarks == null ? null : archiveDealRemarks.trim();
    }

    public String getArchiveDealInnerRemarks() {
        return archiveDealInnerRemarks;
    }

    public void setArchiveDealInnerRemarks(String archiveDealInnerRemarks) {
        this.archiveDealInnerRemarks = archiveDealInnerRemarks == null ? null : archiveDealInnerRemarks.trim();
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