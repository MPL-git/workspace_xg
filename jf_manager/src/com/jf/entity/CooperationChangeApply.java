package com.jf.entity;

import java.math.BigDecimal;
import java.util.Date;

public class CooperationChangeApply {
    private Integer id;

    private Integer mchtId;

    private String shopType;

    private String businessFirms;

    private String brand;

    private String preProductType;

    private String productType;

    private String preShopName;

    private String shopName;

    private Integer preProductTypeId;

    private Integer productTypeId;

    private BigDecimal preDeposit;

    private BigDecimal deposit;

    private String zsAuditStatus;

    private String zsAuditRemarks;

    private Integer zsAuditBy;

    private Date zsAuditDate;

    private String fwAuditStatus;

    private String fwAuditRemarks;

    private Integer fwAuditBy;

    private Date fwAuditDate;

    private String filePath;

    private String uploadStatus;

    private String sendStatus;

    private Integer expressId;

    private String expressNo;

    private String archiveStatus;

    private String archiveRemarks;

    private Integer archiveBy;

    private Date archiveDate;

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

    public String getShopType() {
        return shopType;
    }

    public void setShopType(String shopType) {
        this.shopType = shopType == null ? null : shopType.trim();
    }

    public String getBusinessFirms() {
        return businessFirms;
    }

    public void setBusinessFirms(String businessFirms) {
        this.businessFirms = businessFirms == null ? null : businessFirms.trim();
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand == null ? null : brand.trim();
    }

    public String getPreProductType() {
        return preProductType;
    }

    public void setPreProductType(String preProductType) {
        this.preProductType = preProductType == null ? null : preProductType.trim();
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType == null ? null : productType.trim();
    }

    public String getPreShopName() {
        return preShopName;
    }

    public void setPreShopName(String preShopName) {
        this.preShopName = preShopName == null ? null : preShopName.trim();
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName == null ? null : shopName.trim();
    }

    public Integer getPreProductTypeId() {
        return preProductTypeId;
    }

    public void setPreProductTypeId(Integer preProductTypeId) {
        this.preProductTypeId = preProductTypeId;
    }

    public Integer getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Integer productTypeId) {
        this.productTypeId = productTypeId;
    }

    public BigDecimal getPreDeposit() {
        return preDeposit;
    }

    public void setPreDeposit(BigDecimal preDeposit) {
        this.preDeposit = preDeposit;
    }

    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
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

    public Integer getZsAuditBy() {
        return zsAuditBy;
    }

    public void setZsAuditBy(Integer zsAuditBy) {
        this.zsAuditBy = zsAuditBy;
    }

    public Date getZsAuditDate() {
        return zsAuditDate;
    }

    public void setZsAuditDate(Date zsAuditDate) {
        this.zsAuditDate = zsAuditDate;
    }

    public String getFwAuditStatus() {
        return fwAuditStatus;
    }

    public void setFwAuditStatus(String fwAuditStatus) {
        this.fwAuditStatus = fwAuditStatus == null ? null : fwAuditStatus.trim();
    }

    public String getFwAuditRemarks() {
        return fwAuditRemarks;
    }

    public void setFwAuditRemarks(String fwAuditRemarks) {
        this.fwAuditRemarks = fwAuditRemarks == null ? null : fwAuditRemarks.trim();
    }

    public Integer getFwAuditBy() {
        return fwAuditBy;
    }

    public void setFwAuditBy(Integer fwAuditBy) {
        this.fwAuditBy = fwAuditBy;
    }

    public Date getFwAuditDate() {
        return fwAuditDate;
    }

    public void setFwAuditDate(Date fwAuditDate) {
        this.fwAuditDate = fwAuditDate;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath == null ? null : filePath.trim();
    }

    public String getUploadStatus() {
        return uploadStatus;
    }

    public void setUploadStatus(String uploadStatus) {
        this.uploadStatus = uploadStatus == null ? null : uploadStatus.trim();
    }

    public String getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(String sendStatus) {
        this.sendStatus = sendStatus == null ? null : sendStatus.trim();
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

    public String getArchiveStatus() {
        return archiveStatus;
    }

    public void setArchiveStatus(String archiveStatus) {
        this.archiveStatus = archiveStatus == null ? null : archiveStatus.trim();
    }

    public String getArchiveRemarks() {
        return archiveRemarks;
    }

    public void setArchiveRemarks(String archiveRemarks) {
        this.archiveRemarks = archiveRemarks == null ? null : archiveRemarks.trim();
    }

    public Integer getArchiveBy() {
        return archiveBy;
    }

    public void setArchiveBy(Integer archiveBy) {
        this.archiveBy = archiveBy;
    }

    public Date getArchiveDate() {
        return archiveDate;
    }

    public void setArchiveDate(Date archiveDate) {
        this.archiveDate = archiveDate;
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