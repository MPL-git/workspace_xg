package com.jf.entity;

import java.math.BigDecimal;
import java.util.Date;

public class MchtBrandChg {
    private Integer id;

    private Integer mchtProductBrandId;

    private Integer mchtId;

    private Integer productBrandId;

    private String productBrandName;

    private String logo;

    private Date inspectionExpDate;

    private Date otherExpDate;

    private String aptitudeType;

    private Date aptitudeExpDate;

    private Date platformAuthExpDate;

    private String auditStatus;

    private String auditRemarks;

    private String auditInnerRemarks;

    private String archiveStatus;

    private String archiveDealStatus;

    private String archiveDealRemarks;

    private String archiveDealInnerRemarks;

    private Integer expressId;

    private String expressNo;

    private BigDecimal popCommissionRate;

    private String priceModel;

    private String priceModelDesc;

    private Date commitDate;

    private String isAuditRecommit;

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

    public Integer getMchtProductBrandId() {
        return mchtProductBrandId;
    }

    public void setMchtProductBrandId(Integer mchtProductBrandId) {
        this.mchtProductBrandId = mchtProductBrandId;
    }

    public Integer getMchtId() {
        return mchtId;
    }

    public void setMchtId(Integer mchtId) {
        this.mchtId = mchtId;
    }

    public Integer getProductBrandId() {
        return productBrandId;
    }

    public void setProductBrandId(Integer productBrandId) {
        this.productBrandId = productBrandId;
    }

    public String getProductBrandName() {
        return productBrandName;
    }

    public void setProductBrandName(String productBrandName) {
        this.productBrandName = productBrandName == null ? null : productBrandName.trim();
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo == null ? null : logo.trim();
    }

    public Date getInspectionExpDate() {
        return inspectionExpDate;
    }

    public void setInspectionExpDate(Date inspectionExpDate) {
        this.inspectionExpDate = inspectionExpDate;
    }

    public Date getOtherExpDate() {
        return otherExpDate;
    }

    public void setOtherExpDate(Date otherExpDate) {
        this.otherExpDate = otherExpDate;
    }

    public String getAptitudeType() {
        return aptitudeType;
    }

    public void setAptitudeType(String aptitudeType) {
        this.aptitudeType = aptitudeType == null ? null : aptitudeType.trim();
    }

    public Date getAptitudeExpDate() {
        return aptitudeExpDate;
    }

    public void setAptitudeExpDate(Date aptitudeExpDate) {
        this.aptitudeExpDate = aptitudeExpDate;
    }

    public Date getPlatformAuthExpDate() {
        return platformAuthExpDate;
    }

    public void setPlatformAuthExpDate(Date platformAuthExpDate) {
        this.platformAuthExpDate = platformAuthExpDate;
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

    public String getAuditInnerRemarks() {
        return auditInnerRemarks;
    }

    public void setAuditInnerRemarks(String auditInnerRemarks) {
        this.auditInnerRemarks = auditInnerRemarks == null ? null : auditInnerRemarks.trim();
    }

    public String getArchiveStatus() {
        return archiveStatus;
    }

    public void setArchiveStatus(String archiveStatus) {
        this.archiveStatus = archiveStatus == null ? null : archiveStatus.trim();
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

    public BigDecimal getPopCommissionRate() {
        return popCommissionRate;
    }

    public void setPopCommissionRate(BigDecimal popCommissionRate) {
        this.popCommissionRate = popCommissionRate;
    }

    public String getPriceModel() {
        return priceModel;
    }

    public void setPriceModel(String priceModel) {
        this.priceModel = priceModel == null ? null : priceModel.trim();
    }

    public String getPriceModelDesc() {
        return priceModelDesc;
    }

    public void setPriceModelDesc(String priceModelDesc) {
        this.priceModelDesc = priceModelDesc == null ? null : priceModelDesc.trim();
    }

    public Date getCommitDate() {
        return commitDate;
    }

    public void setCommitDate(Date commitDate) {
        this.commitDate = commitDate;
    }

    public String getIsAuditRecommit() {
        return isAuditRecommit;
    }

    public void setIsAuditRecommit(String isAuditRecommit) {
        this.isAuditRecommit = isAuditRecommit == null ? null : isAuditRecommit.trim();
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