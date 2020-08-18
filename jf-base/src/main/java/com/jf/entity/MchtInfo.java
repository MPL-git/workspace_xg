package com.jf.entity;

import com.jf.common.ext.core.Model;

import java.math.BigDecimal;
import java.util.Date;

public class MchtInfo extends Model {
    private Integer id;

    private String mchtCode;

    private String shortName;

    private String mchtType;

    private Date cooperateBeginDate;

    private String isManageSelf;

    private Date agreementBeginDate;

    private Date agreementEndDate;

    private String shopName;

    private String shopCatalog;

    private String businessFirms;

    private String productType;

    private String brand;

    private String shopType;

    private String shopNameAuditStatus;

    private String shopNameAuditRemarks;

    private String status;

    private Date statusDate;

    private String totalAuditStatus;

    private String totalAuditRemarks;

    private Date totalAuditDate;

    private Date commitAuditDate;

    private String isTotalAuditReCommit;

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

    private String isCompanyInfPerfect;

    private String companyInfAuditRemarks;

    private BigDecimal contractDeposit;

    private BigDecimal brandDeposit;

    private String selectContractDeposit;

    private String selectBrandDeposit;

    private String depositType;

    private String depositContent;

    private String customServiceType;

    private Integer sobotId;

    private Integer xiaonengId;

    private String inBlacklist;

    private String zsAuditStatus;

    private Integer zsAuditBy;

    private String financeConfirmStatus;

    private Integer financeConfirmBy;

    private String shopStatus;

    private Date shopStatusDate;

    private String shopLogo;

    private String grade;

    private String activityAuthStatus;

    private String shopNameAuditInnerRemarks;

    private String zsTotalAuditStatus;

    private Integer zsTotalAuditBy;

    private String zsTotalAuditRemarks;

    private Date zsTotalAuditDate;

    private String isZsTotalAuditReCommit;

    private Date zsCommitAuditDate;

    private Integer totalAuditBy;

    private String idcardImg1ArchiveStatus;

    private String idcardImg2ArchiveStatus;

    private String occPicArchiveStatus;

    private String trcPicArchiveStatus;

    private String atqPicArchiveStatus;

    private String boaalPicArchiveStatus;

    private String companyInfAuditInnerRemarks;

    private Date activityAuthStatusDate;

    private String operateStatus;

    private Date operateStatusDate;

    private String operateRemarks;

    private BigDecimal feeRate;

    private String archiveStatus;

    private String allowMchtApplyClose;

    private String companyInfoArchiveStatus;

    private String mchtBrandArchiveStatus;

    private String scopeOfBusiness;

    private String delayStatus;

    private Integer delayDays;

    private String changeApplyType;

    private Date changeEndDate;

    private String settledType;

    private String speciallyApprovedStatus;

    private String speciallyApprovedRemarks;

    private Date lastCloseDate;

    private String lastCloseRemarks;

    private String supplyChainStatus;

    private String licenseIsMust;

    private String businessLicensePic;

    private String licenseStatus;

    private String licenseRejectReason;

    private String licenseArchiveStatus;

    private String other;

    private String closeReason;

    private String brandType;

    private String brandTypeDesc;

    private Integer productType2Id;

    private Integer createBy;

    private Date createDate;

    private Integer updateBy;

    private Date updateDate;

    private String delFlag;

    private String remarks;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMchtCode() {
        return mchtCode;
    }

    public void setMchtCode(String mchtCode) {
        this.mchtCode = mchtCode == null ? null : mchtCode.trim();
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName == null ? null : shortName.trim();
    }

    public String getMchtType() {
        return mchtType;
    }

    public void setMchtType(String mchtType) {
        this.mchtType = mchtType == null ? null : mchtType.trim();
    }

    public Date getCooperateBeginDate() {
        return cooperateBeginDate;
    }

    public void setCooperateBeginDate(Date cooperateBeginDate) {
        this.cooperateBeginDate = cooperateBeginDate;
    }

    public String getIsManageSelf() {
        return isManageSelf;
    }

    public void setIsManageSelf(String isManageSelf) {
        this.isManageSelf = isManageSelf == null ? null : isManageSelf.trim();
    }

    public Date getAgreementBeginDate() {
        return agreementBeginDate;
    }

    public void setAgreementBeginDate(Date agreementBeginDate) {
        this.agreementBeginDate = agreementBeginDate;
    }

    public Date getAgreementEndDate() {
        return agreementEndDate;
    }

    public void setAgreementEndDate(Date agreementEndDate) {
        this.agreementEndDate = agreementEndDate;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName == null ? null : shopName.trim();
    }

    public String getShopCatalog() {
        return shopCatalog;
    }

    public void setShopCatalog(String shopCatalog) {
        this.shopCatalog = shopCatalog == null ? null : shopCatalog.trim();
    }

    public String getBusinessFirms() {
        return businessFirms;
    }

    public void setBusinessFirms(String businessFirms) {
        this.businessFirms = businessFirms == null ? null : businessFirms.trim();
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType == null ? null : productType.trim();
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand == null ? null : brand.trim();
    }

    public String getShopType() {
        return shopType;
    }

    public void setShopType(String shopType) {
        this.shopType = shopType == null ? null : shopType.trim();
    }

    public String getShopNameAuditStatus() {
        return shopNameAuditStatus;
    }

    public void setShopNameAuditStatus(String shopNameAuditStatus) {
        this.shopNameAuditStatus = shopNameAuditStatus == null ? null : shopNameAuditStatus.trim();
    }

    public String getShopNameAuditRemarks() {
        return shopNameAuditRemarks;
    }

    public void setShopNameAuditRemarks(String shopNameAuditRemarks) {
        this.shopNameAuditRemarks = shopNameAuditRemarks == null ? null : shopNameAuditRemarks.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getStatusDate() {
        return statusDate;
    }

    public void setStatusDate(Date statusDate) {
        this.statusDate = statusDate;
    }

    public String getTotalAuditStatus() {
        return totalAuditStatus;
    }

    public void setTotalAuditStatus(String totalAuditStatus) {
        this.totalAuditStatus = totalAuditStatus == null ? null : totalAuditStatus.trim();
    }

    public String getTotalAuditRemarks() {
        return totalAuditRemarks;
    }

    public void setTotalAuditRemarks(String totalAuditRemarks) {
        this.totalAuditRemarks = totalAuditRemarks == null ? null : totalAuditRemarks.trim();
    }

    public Date getTotalAuditDate() {
        return totalAuditDate;
    }

    public void setTotalAuditDate(Date totalAuditDate) {
        this.totalAuditDate = totalAuditDate;
    }

    public Date getCommitAuditDate() {
        return commitAuditDate;
    }

    public void setCommitAuditDate(Date commitAuditDate) {
        this.commitAuditDate = commitAuditDate;
    }

    public String getIsTotalAuditReCommit() {
        return isTotalAuditReCommit;
    }

    public void setIsTotalAuditReCommit(String isTotalAuditReCommit) {
        this.isTotalAuditReCommit = isTotalAuditReCommit == null ? null : isTotalAuditReCommit.trim();
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

    public String getIsCompanyInfPerfect() {
        return isCompanyInfPerfect;
    }

    public void setIsCompanyInfPerfect(String isCompanyInfPerfect) {
        this.isCompanyInfPerfect = isCompanyInfPerfect == null ? null : isCompanyInfPerfect.trim();
    }

    public String getCompanyInfAuditRemarks() {
        return companyInfAuditRemarks;
    }

    public void setCompanyInfAuditRemarks(String companyInfAuditRemarks) {
        this.companyInfAuditRemarks = companyInfAuditRemarks == null ? null : companyInfAuditRemarks.trim();
    }

    public BigDecimal getContractDeposit() {
        return contractDeposit;
    }

    public void setContractDeposit(BigDecimal contractDeposit) {
        this.contractDeposit = contractDeposit;
    }

    public BigDecimal getBrandDeposit() {
        return brandDeposit;
    }

    public void setBrandDeposit(BigDecimal brandDeposit) {
        this.brandDeposit = brandDeposit;
    }

    public String getSelectContractDeposit() {
        return selectContractDeposit;
    }

    public void setSelectContractDeposit(String selectContractDeposit) {
        this.selectContractDeposit = selectContractDeposit == null ? null : selectContractDeposit.trim();
    }

    public String getSelectBrandDeposit() {
        return selectBrandDeposit;
    }

    public void setSelectBrandDeposit(String selectBrandDeposit) {
        this.selectBrandDeposit = selectBrandDeposit == null ? null : selectBrandDeposit.trim();
    }

    public String getDepositType() {
        return depositType;
    }

    public void setDepositType(String depositType) {
        this.depositType = depositType == null ? null : depositType.trim();
    }

    public String getDepositContent() {
        return depositContent;
    }

    public void setDepositContent(String depositContent) {
        this.depositContent = depositContent == null ? null : depositContent.trim();
    }

    public String getCustomServiceType() {
        return customServiceType;
    }

    public void setCustomServiceType(String customServiceType) {
        this.customServiceType = customServiceType == null ? null : customServiceType.trim();
    }

    public Integer getSobotId() {
        return sobotId;
    }

    public void setSobotId(Integer sobotId) {
        this.sobotId = sobotId;
    }

    public Integer getXiaonengId() {
        return xiaonengId;
    }

    public void setXiaonengId(Integer xiaonengId) {
        this.xiaonengId = xiaonengId;
    }

    public String getInBlacklist() {
        return inBlacklist;
    }

    public void setInBlacklist(String inBlacklist) {
        this.inBlacklist = inBlacklist == null ? null : inBlacklist.trim();
    }

    public String getZsAuditStatus() {
        return zsAuditStatus;
    }

    public void setZsAuditStatus(String zsAuditStatus) {
        this.zsAuditStatus = zsAuditStatus == null ? null : zsAuditStatus.trim();
    }

    public Integer getZsAuditBy() {
        return zsAuditBy;
    }

    public void setZsAuditBy(Integer zsAuditBy) {
        this.zsAuditBy = zsAuditBy;
    }

    public String getFinanceConfirmStatus() {
        return financeConfirmStatus;
    }

    public void setFinanceConfirmStatus(String financeConfirmStatus) {
        this.financeConfirmStatus = financeConfirmStatus == null ? null : financeConfirmStatus.trim();
    }

    public Integer getFinanceConfirmBy() {
        return financeConfirmBy;
    }

    public void setFinanceConfirmBy(Integer financeConfirmBy) {
        this.financeConfirmBy = financeConfirmBy;
    }

    public String getShopStatus() {
        return shopStatus;
    }

    public void setShopStatus(String shopStatus) {
        this.shopStatus = shopStatus == null ? null : shopStatus.trim();
    }

    public Date getShopStatusDate() {
        return shopStatusDate;
    }

    public void setShopStatusDate(Date shopStatusDate) {
        this.shopStatusDate = shopStatusDate;
    }

    public String getShopLogo() {
        return shopLogo;
    }

    public void setShopLogo(String shopLogo) {
        this.shopLogo = shopLogo == null ? null : shopLogo.trim();
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade == null ? null : grade.trim();
    }

    public String getActivityAuthStatus() {
        return activityAuthStatus;
    }

    public void setActivityAuthStatus(String activityAuthStatus) {
        this.activityAuthStatus = activityAuthStatus == null ? null : activityAuthStatus.trim();
    }

    public String getShopNameAuditInnerRemarks() {
        return shopNameAuditInnerRemarks;
    }

    public void setShopNameAuditInnerRemarks(String shopNameAuditInnerRemarks) {
        this.shopNameAuditInnerRemarks = shopNameAuditInnerRemarks == null ? null : shopNameAuditInnerRemarks.trim();
    }

    public String getZsTotalAuditStatus() {
        return zsTotalAuditStatus;
    }

    public void setZsTotalAuditStatus(String zsTotalAuditStatus) {
        this.zsTotalAuditStatus = zsTotalAuditStatus == null ? null : zsTotalAuditStatus.trim();
    }

    public Integer getZsTotalAuditBy() {
        return zsTotalAuditBy;
    }

    public void setZsTotalAuditBy(Integer zsTotalAuditBy) {
        this.zsTotalAuditBy = zsTotalAuditBy;
    }

    public String getZsTotalAuditRemarks() {
        return zsTotalAuditRemarks;
    }

    public void setZsTotalAuditRemarks(String zsTotalAuditRemarks) {
        this.zsTotalAuditRemarks = zsTotalAuditRemarks == null ? null : zsTotalAuditRemarks.trim();
    }

    public Date getZsTotalAuditDate() {
        return zsTotalAuditDate;
    }

    public void setZsTotalAuditDate(Date zsTotalAuditDate) {
        this.zsTotalAuditDate = zsTotalAuditDate;
    }

    public String getIsZsTotalAuditReCommit() {
        return isZsTotalAuditReCommit;
    }

    public void setIsZsTotalAuditReCommit(String isZsTotalAuditReCommit) {
        this.isZsTotalAuditReCommit = isZsTotalAuditReCommit == null ? null : isZsTotalAuditReCommit.trim();
    }

    public Date getZsCommitAuditDate() {
        return zsCommitAuditDate;
    }

    public void setZsCommitAuditDate(Date zsCommitAuditDate) {
        this.zsCommitAuditDate = zsCommitAuditDate;
    }

    public Integer getTotalAuditBy() {
        return totalAuditBy;
    }

    public void setTotalAuditBy(Integer totalAuditBy) {
        this.totalAuditBy = totalAuditBy;
    }

    public String getIdcardImg1ArchiveStatus() {
        return idcardImg1ArchiveStatus;
    }

    public void setIdcardImg1ArchiveStatus(String idcardImg1ArchiveStatus) {
        this.idcardImg1ArchiveStatus = idcardImg1ArchiveStatus == null ? null : idcardImg1ArchiveStatus.trim();
    }

    public String getIdcardImg2ArchiveStatus() {
        return idcardImg2ArchiveStatus;
    }

    public void setIdcardImg2ArchiveStatus(String idcardImg2ArchiveStatus) {
        this.idcardImg2ArchiveStatus = idcardImg2ArchiveStatus == null ? null : idcardImg2ArchiveStatus.trim();
    }

    public String getOccPicArchiveStatus() {
        return occPicArchiveStatus;
    }

    public void setOccPicArchiveStatus(String occPicArchiveStatus) {
        this.occPicArchiveStatus = occPicArchiveStatus == null ? null : occPicArchiveStatus.trim();
    }

    public String getTrcPicArchiveStatus() {
        return trcPicArchiveStatus;
    }

    public void setTrcPicArchiveStatus(String trcPicArchiveStatus) {
        this.trcPicArchiveStatus = trcPicArchiveStatus == null ? null : trcPicArchiveStatus.trim();
    }

    public String getAtqPicArchiveStatus() {
        return atqPicArchiveStatus;
    }

    public void setAtqPicArchiveStatus(String atqPicArchiveStatus) {
        this.atqPicArchiveStatus = atqPicArchiveStatus == null ? null : atqPicArchiveStatus.trim();
    }

    public String getBoaalPicArchiveStatus() {
        return boaalPicArchiveStatus;
    }

    public void setBoaalPicArchiveStatus(String boaalPicArchiveStatus) {
        this.boaalPicArchiveStatus = boaalPicArchiveStatus == null ? null : boaalPicArchiveStatus.trim();
    }

    public String getCompanyInfAuditInnerRemarks() {
        return companyInfAuditInnerRemarks;
    }

    public void setCompanyInfAuditInnerRemarks(String companyInfAuditInnerRemarks) {
        this.companyInfAuditInnerRemarks = companyInfAuditInnerRemarks == null ? null : companyInfAuditInnerRemarks.trim();
    }

    public Date getActivityAuthStatusDate() {
        return activityAuthStatusDate;
    }

    public void setActivityAuthStatusDate(Date activityAuthStatusDate) {
        this.activityAuthStatusDate = activityAuthStatusDate;
    }

    public String getOperateStatus() {
        return operateStatus;
    }

    public void setOperateStatus(String operateStatus) {
        this.operateStatus = operateStatus == null ? null : operateStatus.trim();
    }

    public Date getOperateStatusDate() {
        return operateStatusDate;
    }

    public void setOperateStatusDate(Date operateStatusDate) {
        this.operateStatusDate = operateStatusDate;
    }

    public String getOperateRemarks() {
        return operateRemarks;
    }

    public void setOperateRemarks(String operateRemarks) {
        this.operateRemarks = operateRemarks == null ? null : operateRemarks.trim();
    }

    public BigDecimal getFeeRate() {
        return feeRate;
    }

    public void setFeeRate(BigDecimal feeRate) {
        this.feeRate = feeRate;
    }

    public String getArchiveStatus() {
        return archiveStatus;
    }

    public void setArchiveStatus(String archiveStatus) {
        this.archiveStatus = archiveStatus == null ? null : archiveStatus.trim();
    }

    public String getAllowMchtApplyClose() {
        return allowMchtApplyClose;
    }

    public void setAllowMchtApplyClose(String allowMchtApplyClose) {
        this.allowMchtApplyClose = allowMchtApplyClose == null ? null : allowMchtApplyClose.trim();
    }

    public String getCompanyInfoArchiveStatus() {
        return companyInfoArchiveStatus;
    }

    public void setCompanyInfoArchiveStatus(String companyInfoArchiveStatus) {
        this.companyInfoArchiveStatus = companyInfoArchiveStatus == null ? null : companyInfoArchiveStatus.trim();
    }

    public String getMchtBrandArchiveStatus() {
        return mchtBrandArchiveStatus;
    }

    public void setMchtBrandArchiveStatus(String mchtBrandArchiveStatus) {
        this.mchtBrandArchiveStatus = mchtBrandArchiveStatus == null ? null : mchtBrandArchiveStatus.trim();
    }

    public String getScopeOfBusiness() {
        return scopeOfBusiness;
    }

    public void setScopeOfBusiness(String scopeOfBusiness) {
        this.scopeOfBusiness = scopeOfBusiness == null ? null : scopeOfBusiness.trim();
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

    public String getChangeApplyType() {
        return changeApplyType;
    }

    public void setChangeApplyType(String changeApplyType) {
        this.changeApplyType = changeApplyType == null ? null : changeApplyType.trim();
    }

    public Date getChangeEndDate() {
        return changeEndDate;
    }

    public void setChangeEndDate(Date changeEndDate) {
        this.changeEndDate = changeEndDate;
    }

    public String getSettledType() {
        return settledType;
    }

    public void setSettledType(String settledType) {
        this.settledType = settledType == null ? null : settledType.trim();
    }

    public String getSpeciallyApprovedStatus() {
        return speciallyApprovedStatus;
    }

    public void setSpeciallyApprovedStatus(String speciallyApprovedStatus) {
        this.speciallyApprovedStatus = speciallyApprovedStatus == null ? null : speciallyApprovedStatus.trim();
    }

    public String getSpeciallyApprovedRemarks() {
        return speciallyApprovedRemarks;
    }

    public void setSpeciallyApprovedRemarks(String speciallyApprovedRemarks) {
        this.speciallyApprovedRemarks = speciallyApprovedRemarks == null ? null : speciallyApprovedRemarks.trim();
    }

    public Date getLastCloseDate() {
        return lastCloseDate;
    }

    public void setLastCloseDate(Date lastCloseDate) {
        this.lastCloseDate = lastCloseDate;
    }

    public String getLastCloseRemarks() {
        return lastCloseRemarks;
    }

    public void setLastCloseRemarks(String lastCloseRemarks) {
        this.lastCloseRemarks = lastCloseRemarks == null ? null : lastCloseRemarks.trim();
    }

    public String getSupplyChainStatus() {
        return supplyChainStatus;
    }

    public void setSupplyChainStatus(String supplyChainStatus) {
        this.supplyChainStatus = supplyChainStatus == null ? null : supplyChainStatus.trim();
    }

    public String getLicenseIsMust() {
        return licenseIsMust;
    }

    public void setLicenseIsMust(String licenseIsMust) {
        this.licenseIsMust = licenseIsMust == null ? null : licenseIsMust.trim();
    }

    public String getBusinessLicensePic() {
        return businessLicensePic;
    }

    public void setBusinessLicensePic(String businessLicensePic) {
        this.businessLicensePic = businessLicensePic == null ? null : businessLicensePic.trim();
    }

    public String getLicenseStatus() {
        return licenseStatus;
    }

    public void setLicenseStatus(String licenseStatus) {
        this.licenseStatus = licenseStatus == null ? null : licenseStatus.trim();
    }

    public String getLicenseRejectReason() {
        return licenseRejectReason;
    }

    public void setLicenseRejectReason(String licenseRejectReason) {
        this.licenseRejectReason = licenseRejectReason == null ? null : licenseRejectReason.trim();
    }

    public String getLicenseArchiveStatus() {
        return licenseArchiveStatus;
    }

    public void setLicenseArchiveStatus(String licenseArchiveStatus) {
        this.licenseArchiveStatus = licenseArchiveStatus == null ? null : licenseArchiveStatus.trim();
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other == null ? null : other.trim();
    }

    public String getCloseReason() {
        return closeReason;
    }

    public void setCloseReason(String closeReason) {
        this.closeReason = closeReason == null ? null : closeReason.trim();
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

    public Integer getProductType2Id() {
        return productType2Id;
    }

    public void setProductType2Id(Integer productType2Id) {
        this.productType2Id = productType2Id;
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

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : delFlag.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }
}