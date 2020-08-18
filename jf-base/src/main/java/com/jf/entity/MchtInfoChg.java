package com.jf.entity;

import java.math.BigDecimal;
import java.util.Date;

public class MchtInfoChg {
    private Integer id;

    private Integer mchtId;

    private String mchtCode;

    private String status;

    private String companyName;

    private String companyType;

    private String companyRegName;

    private String uscc;

    private String companyAddress;

    private Date foundedDate;

    private BigDecimal regCapital;

    private String regFeeType;

    private String corporationName;

    private String corporationIdcardNo;

    private Date corporationIdcardDate;

    private String corporationMobile;

    private String corporationIdcardImg1;

    private String corporationIdcardImg2;

    private String blPic;

    private String occPic;

    private String trcPic;

    private String atqPic;

    private String boaalPic;

    private Date yearlyInspectionDate;

    private String companyTel;

    private Integer contactProvince;

    private Integer contactCity;

    private Integer contactCounty;

    private String contactAddress;

    private String auditRemarks;

    private Date commitDate;

    private String scopeOfBusiness;

    private Integer expressId;

    private String expressNo;

    private String companyInfoArchiveStatus;

    private String archiveDealStatus;

    private String archiveDealRemarks;

    private String archiveDealInnerRemarks;

    private String brandType;

    private String brandTypeDesc;

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

    public String getMchtCode() {
        return mchtCode;
    }

    public void setMchtCode(String mchtCode) {
        this.mchtCode = mchtCode == null ? null : mchtCode.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType == null ? null : companyType.trim();
    }

    public String getCompanyRegName() {
        return companyRegName;
    }

    public void setCompanyRegName(String companyRegName) {
        this.companyRegName = companyRegName == null ? null : companyRegName.trim();
    }

    public String getUscc() {
        return uscc;
    }

    public void setUscc(String uscc) {
        this.uscc = uscc == null ? null : uscc.trim();
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress == null ? null : companyAddress.trim();
    }

    public Date getFoundedDate() {
        return foundedDate;
    }

    public void setFoundedDate(Date foundedDate) {
        this.foundedDate = foundedDate;
    }

    public BigDecimal getRegCapital() {
        return regCapital;
    }

    public void setRegCapital(BigDecimal regCapital) {
        this.regCapital = regCapital;
    }

    public String getRegFeeType() {
        return regFeeType;
    }

    public void setRegFeeType(String regFeeType) {
        this.regFeeType = regFeeType == null ? null : regFeeType.trim();
    }

    public String getCorporationName() {
        return corporationName;
    }

    public void setCorporationName(String corporationName) {
        this.corporationName = corporationName == null ? null : corporationName.trim();
    }

    public String getCorporationIdcardNo() {
        return corporationIdcardNo;
    }

    public void setCorporationIdcardNo(String corporationIdcardNo) {
        this.corporationIdcardNo = corporationIdcardNo == null ? null : corporationIdcardNo.trim();
    }

    public Date getCorporationIdcardDate() {
        return corporationIdcardDate;
    }

    public void setCorporationIdcardDate(Date corporationIdcardDate) {
        this.corporationIdcardDate = corporationIdcardDate;
    }

    public String getCorporationMobile() {
        return corporationMobile;
    }

    public void setCorporationMobile(String corporationMobile) {
        this.corporationMobile = corporationMobile == null ? null : corporationMobile.trim();
    }

    public String getCorporationIdcardImg1() {
        return corporationIdcardImg1;
    }

    public void setCorporationIdcardImg1(String corporationIdcardImg1) {
        this.corporationIdcardImg1 = corporationIdcardImg1 == null ? null : corporationIdcardImg1.trim();
    }

    public String getCorporationIdcardImg2() {
        return corporationIdcardImg2;
    }

    public void setCorporationIdcardImg2(String corporationIdcardImg2) {
        this.corporationIdcardImg2 = corporationIdcardImg2 == null ? null : corporationIdcardImg2.trim();
    }

    public String getBlPic() {
        return blPic;
    }

    public void setBlPic(String blPic) {
        this.blPic = blPic == null ? null : blPic.trim();
    }

    public String getOccPic() {
        return occPic;
    }

    public void setOccPic(String occPic) {
        this.occPic = occPic == null ? null : occPic.trim();
    }

    public String getTrcPic() {
        return trcPic;
    }

    public void setTrcPic(String trcPic) {
        this.trcPic = trcPic == null ? null : trcPic.trim();
    }

    public String getAtqPic() {
        return atqPic;
    }

    public void setAtqPic(String atqPic) {
        this.atqPic = atqPic == null ? null : atqPic.trim();
    }

    public String getBoaalPic() {
        return boaalPic;
    }

    public void setBoaalPic(String boaalPic) {
        this.boaalPic = boaalPic == null ? null : boaalPic.trim();
    }

    public Date getYearlyInspectionDate() {
        return yearlyInspectionDate;
    }

    public void setYearlyInspectionDate(Date yearlyInspectionDate) {
        this.yearlyInspectionDate = yearlyInspectionDate;
    }

    public String getCompanyTel() {
        return companyTel;
    }

    public void setCompanyTel(String companyTel) {
        this.companyTel = companyTel == null ? null : companyTel.trim();
    }

    public Integer getContactProvince() {
        return contactProvince;
    }

    public void setContactProvince(Integer contactProvince) {
        this.contactProvince = contactProvince;
    }

    public Integer getContactCity() {
        return contactCity;
    }

    public void setContactCity(Integer contactCity) {
        this.contactCity = contactCity;
    }

    public Integer getContactCounty() {
        return contactCounty;
    }

    public void setContactCounty(Integer contactCounty) {
        this.contactCounty = contactCounty;
    }

    public String getContactAddress() {
        return contactAddress;
    }

    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress == null ? null : contactAddress.trim();
    }

    public String getAuditRemarks() {
        return auditRemarks;
    }

    public void setAuditRemarks(String auditRemarks) {
        this.auditRemarks = auditRemarks == null ? null : auditRemarks.trim();
    }

    public Date getCommitDate() {
        return commitDate;
    }

    public void setCommitDate(Date commitDate) {
        this.commitDate = commitDate;
    }

    public String getScopeOfBusiness() {
        return scopeOfBusiness;
    }

    public void setScopeOfBusiness(String scopeOfBusiness) {
        this.scopeOfBusiness = scopeOfBusiness == null ? null : scopeOfBusiness.trim();
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

    public String getCompanyInfoArchiveStatus() {
        return companyInfoArchiveStatus;
    }

    public void setCompanyInfoArchiveStatus(String companyInfoArchiveStatus) {
        this.companyInfoArchiveStatus = companyInfoArchiveStatus == null ? null : companyInfoArchiveStatus.trim();
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

    public String getBrandType() {
        return brandType;
    }

    public void setBrandType(String brandType) {
        this.brandType = brandType == null ? null : brandType.trim();
    }

    public String getBrandTypeDesc() {
        return brandTypeDesc;
    }

    public void setBrandTypeDesc(String brandTypeDesc) {
        this.brandTypeDesc = brandTypeDesc == null ? null : brandTypeDesc.trim();
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