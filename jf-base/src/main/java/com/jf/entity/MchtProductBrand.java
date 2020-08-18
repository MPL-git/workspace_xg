package com.jf.entity;

import com.jf.common.ext.core.Model;

import java.math.BigDecimal;
import java.util.Date;

public class MchtProductBrand extends Model {
    private Integer id;

    private Integer mchtId;

    private Integer productBrandId;

    private String productBrandName;

    private String logo;

    private Date inspectionExpDate;

    private Date otherExpDate;

    private String aptitudeType;

    private Date aptitudeExpDate;

    private String aptitudePic;

    private String platformAuthPic;

    private Date platformAuthExpDate;

    private BigDecimal popCommissionRate;

    private String priceModel;

    private String priceModelDesc;

    private String status;

    private String isActivity;

    private String auditStatus;

    private String auditRemarks;

    private String isSpeciallyApproved;

    private String speciallyApprovedRemarks;

    private String speciallyApprovedSource;

    private Date speciallyApprovedDate;

    private String statusRemarks;

    private Date statusDate;

    private Integer statusBy;

    private String zsAuditStatus;

    private String zsAuditRemarks;

    private String zsAuditInnerRemarks;

    private Integer zsAuditBy;

    private Date zsCommitAuditDate;

    private Date zsAuditDate;

    private String isZsAuditRecommit;

    private String auditInnerRemarks;

    private Integer auditBy;

    private Date commitAuditDate;

    private Date auditDate;

    private String isAuditRecommit;

    private String archiveStatus;

    private Integer expressId;

    private String expressNo;

    private String brandSource;

    private String delayStatus;

    private Integer delayDays;

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

    public String getAptitudePic() {
        return aptitudePic;
    }

    public void setAptitudePic(String aptitudePic) {
        this.aptitudePic = aptitudePic == null ? null : aptitudePic.trim();
    }

    public String getPlatformAuthPic() {
        return platformAuthPic;
    }

    public void setPlatformAuthPic(String platformAuthPic) {
        this.platformAuthPic = platformAuthPic == null ? null : platformAuthPic.trim();
    }

    public Date getPlatformAuthExpDate() {
        return platformAuthExpDate;
    }

    public void setPlatformAuthExpDate(Date platformAuthExpDate) {
        this.platformAuthExpDate = platformAuthExpDate;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getIsActivity() {
        return isActivity;
    }

    public void setIsActivity(String isActivity) {
        this.isActivity = isActivity == null ? null : isActivity.trim();
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

    public String getIsSpeciallyApproved() {
        return isSpeciallyApproved;
    }

    public void setIsSpeciallyApproved(String isSpeciallyApproved) {
        this.isSpeciallyApproved = isSpeciallyApproved == null ? null : isSpeciallyApproved.trim();
    }

    public String getSpeciallyApprovedRemarks() {
        return speciallyApprovedRemarks;
    }

    public void setSpeciallyApprovedRemarks(String speciallyApprovedRemarks) {
        this.speciallyApprovedRemarks = speciallyApprovedRemarks == null ? null : speciallyApprovedRemarks.trim();
    }

    public String getSpeciallyApprovedSource() {
        return speciallyApprovedSource;
    }

    public void setSpeciallyApprovedSource(String speciallyApprovedSource) {
        this.speciallyApprovedSource = speciallyApprovedSource == null ? null : speciallyApprovedSource.trim();
    }

    public Date getSpeciallyApprovedDate() {
        return speciallyApprovedDate;
    }

    public void setSpeciallyApprovedDate(Date speciallyApprovedDate) {
        this.speciallyApprovedDate = speciallyApprovedDate;
    }

    public String getStatusRemarks() {
        return statusRemarks;
    }

    public void setStatusRemarks(String statusRemarks) {
        this.statusRemarks = statusRemarks == null ? null : statusRemarks.trim();
    }

    public Date getStatusDate() {
        return statusDate;
    }

    public void setStatusDate(Date statusDate) {
        this.statusDate = statusDate;
    }

    public Integer getStatusBy() {
        return statusBy;
    }

    public void setStatusBy(Integer statusBy) {
        this.statusBy = statusBy;
    }

    public String getZsAuditStatus() {
        return zsAuditStatus;
    }

    public void setZsAuditStatus(String zsAuditStatus) {
        this.zsAuditStatus = zsAuditStatus == null ? null : zsAuditStatus.trim();
    }

    public String getZsAuditRemarks() {
        return zsAuditRemarks;
    }

    public void setZsAuditRemarks(String zsAuditRemarks) {
        this.zsAuditRemarks = zsAuditRemarks == null ? null : zsAuditRemarks.trim();
    }

    public String getZsAuditInnerRemarks() {
        return zsAuditInnerRemarks;
    }

    public void setZsAuditInnerRemarks(String zsAuditInnerRemarks) {
        this.zsAuditInnerRemarks = zsAuditInnerRemarks == null ? null : zsAuditInnerRemarks.trim();
    }

    public Integer getZsAuditBy() {
        return zsAuditBy;
    }

    public void setZsAuditBy(Integer zsAuditBy) {
        this.zsAuditBy = zsAuditBy;
    }

    public Date getZsCommitAuditDate() {
        return zsCommitAuditDate;
    }

    public void setZsCommitAuditDate(Date zsCommitAuditDate) {
        this.zsCommitAuditDate = zsCommitAuditDate;
    }

    public Date getZsAuditDate() {
        return zsAuditDate;
    }

    public void setZsAuditDate(Date zsAuditDate) {
        this.zsAuditDate = zsAuditDate;
    }

    public String getIsZsAuditRecommit() {
        return isZsAuditRecommit;
    }

    public void setIsZsAuditRecommit(String isZsAuditRecommit) {
        this.isZsAuditRecommit = isZsAuditRecommit == null ? null : isZsAuditRecommit.trim();
    }

    public String getAuditInnerRemarks() {
        return auditInnerRemarks;
    }

    public void setAuditInnerRemarks(String auditInnerRemarks) {
        this.auditInnerRemarks = auditInnerRemarks == null ? null : auditInnerRemarks.trim();
    }

    public Integer getAuditBy() {
        return auditBy;
    }

    public void setAuditBy(Integer auditBy) {
        this.auditBy = auditBy;
    }

    public Date getCommitAuditDate() {
        return commitAuditDate;
    }

    public void setCommitAuditDate(Date commitAuditDate) {
        this.commitAuditDate = commitAuditDate;
    }

    public Date getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(Date auditDate) {
        this.auditDate = auditDate;
    }

    public String getIsAuditRecommit() {
        return isAuditRecommit;
    }

    public void setIsAuditRecommit(String isAuditRecommit) {
        this.isAuditRecommit = isAuditRecommit == null ? null : isAuditRecommit.trim();
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

    public String getBrandSource() {
        return brandSource;
    }

    public void setBrandSource(String brandSource) {
        this.brandSource = brandSource == null ? null : brandSource.trim();
    }

    public String getDelayStatus() {
        return delayStatus;
    }

    public void setDelayStatus(String delayStatus) {
        this.delayStatus = delayStatus == null ? null : delayStatus.trim();
    }

    public Integer getDelayDays() {
        return delayDays;
    }

    public void setDelayDays(Integer delayDays) {
        this.delayDays = delayDays;
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