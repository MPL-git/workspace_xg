package com.jf.entity;

import java.math.BigDecimal;
import java.util.Date;

public class MchtSettledApply {
    private Integer id;

    private String isManageSelf;

    private String settledType;

    private String sourceType;

    private String sourceRemark;

    private String clientType;

    private String customerSource;

    private String isimport;

    private String companyName;

    private BigDecimal regCapital;

    private String regFeeType;

    private String corporationName;

    private Integer province;

    private Integer city;

    private Integer county;

    private String address;

    private String contactName;

    private String contactJob;

    private String phone;

    private String email;

    private String qq;

    private String companyTel;

    private String tmallShop;

    private String taobaoShop;

    private String jdShop;

    private String vipsShop;

    private String otherShop;

    private Integer productTypeId;

    private String productTypeMain;

    private String productBrandMain;

    private String shopType;

    private String zsProductTypeIds;

    private Integer platformContactId;

    private String status;

    private String mchtType;

    private Integer mchtId;

    private String productSituation;

    private String otherChannelLink;

    private String logistics;

    private String teamSituation;

    private String companySituation;

    private Date infoConfirmDate;

    private Integer infoConfirmBy;

    private BigDecimal contractDeposit;

    private BigDecimal brandDeposit;

    private String selectContractDeposit;

    private String selectBrandDeposit;

    private String depositType;

    private String depositConfirmStatus;

    private Date depositConfirmDate;

    private Integer depositConfirmBy;

    private String ip;

    private String wechat;

    private String usedExpress;

    private BigDecimal feeRate;

    private String productName;

    private Integer createBy;

    private Date createDate;

    private Integer updateBy;

    private Date updateDate;

    private String remarks;

    private String delFlag;

    public Integer getId() {
        return id;
    }

    public String getIsManageSelf() {
        return isManageSelf;
    }

    public void setIsManageSelf(String isManageSelf) {
        this.isManageSelf = isManageSelf == null ? null : isManageSelf.trim();
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSettledType() {
        return settledType;
    }

    public void setSettledType(String settledType) {
        this.settledType = settledType == null ? null : settledType.trim();
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType == null ? null : sourceType.trim();
    }

    public String getSourceRemark() {
        return sourceRemark;
    }

    public void setSourceRemark(String sourceRemark) {
        this.sourceRemark = sourceRemark == null ? null : sourceRemark.trim();
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType == null ? null : clientType.trim();
    }

    public String getCustomerSource() {
        return customerSource;
    }

    public void setCustomerSource(String customerSource) {
        this.customerSource = customerSource == null ? null : customerSource.trim();
    }

    public String getIsimport() {
        return isimport;
    }

    public void setIsimport(String isimport) {
        this.isimport = isimport == null ? null : isimport.trim();
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
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

    public Integer getProvince() {
        return province;
    }

    public void setProvince(Integer province) {
        this.province = province;
    }

    public Integer getCity() {
        return city;
    }

    public void setCity(Integer city) {
        this.city = city;
    }

    public Integer getCounty() {
        return county;
    }

    public void setCounty(Integer county) {
        this.county = county;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName == null ? null : contactName.trim();
    }

    public String getContactJob() {
        return contactJob;
    }

    public void setContactJob(String contactJob) {
        this.contactJob = contactJob == null ? null : contactJob.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq == null ? null : qq.trim();
    }

    public String getCompanyTel() {
        return companyTel;
    }

    public void setCompanyTel(String companyTel) {
        this.companyTel = companyTel == null ? null : companyTel.trim();
    }

    public String getTmallShop() {
        return tmallShop;
    }

    public void setTmallShop(String tmallShop) {
        this.tmallShop = tmallShop == null ? null : tmallShop.trim();
    }

    public String getTaobaoShop() {
        return taobaoShop;
    }

    public void setTaobaoShop(String taobaoShop) {
        this.taobaoShop = taobaoShop == null ? null : taobaoShop.trim();
    }

    public String getJdShop() {
        return jdShop;
    }

    public void setJdShop(String jdShop) {
        this.jdShop = jdShop == null ? null : jdShop.trim();
    }

    public String getVipsShop() {
        return vipsShop;
    }

    public void setVipsShop(String vipsShop) {
        this.vipsShop = vipsShop == null ? null : vipsShop.trim();
    }

    public String getOtherShop() {
        return otherShop;
    }

    public void setOtherShop(String otherShop) {
        this.otherShop = otherShop == null ? null : otherShop.trim();
    }

    public Integer getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Integer productTypeId) {
        this.productTypeId = productTypeId;
    }

    public String getProductTypeMain() {
        return productTypeMain;
    }

    public void setProductTypeMain(String productTypeMain) {
        this.productTypeMain = productTypeMain == null ? null : productTypeMain.trim();
    }

    public String getProductBrandMain() {
        return productBrandMain;
    }

    public void setProductBrandMain(String productBrandMain) {
        this.productBrandMain = productBrandMain == null ? null : productBrandMain.trim();
    }

    public String getShopType() {
        return shopType;
    }

    public void setShopType(String shopType) {
        this.shopType = shopType == null ? null : shopType.trim();
    }

    public String getZsProductTypeIds() {
        return zsProductTypeIds;
    }

    public void setZsProductTypeIds(String zsProductTypeIds) {
        this.zsProductTypeIds = zsProductTypeIds == null ? null : zsProductTypeIds.trim();
    }

    public Integer getPlatformContactId() {
        return platformContactId;
    }

    public void setPlatformContactId(Integer platformContactId) {
        this.platformContactId = platformContactId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getMchtType() {
        return mchtType;
    }

    public void setMchtType(String mchtType) {
        this.mchtType = mchtType == null ? null : mchtType.trim();
    }

    public Integer getMchtId() {
        return mchtId;
    }

    public void setMchtId(Integer mchtId) {
        this.mchtId = mchtId;
    }

    public String getProductSituation() {
        return productSituation;
    }

    public void setProductSituation(String productSituation) {
        this.productSituation = productSituation == null ? null : productSituation.trim();
    }

    public String getOtherChannelLink() {
        return otherChannelLink;
    }

    public void setOtherChannelLink(String otherChannelLink) {
        this.otherChannelLink = otherChannelLink == null ? null : otherChannelLink.trim();
    }

    public String getLogistics() {
        return logistics;
    }

    public void setLogistics(String logistics) {
        this.logistics = logistics == null ? null : logistics.trim();
    }

    public String getTeamSituation() {
        return teamSituation;
    }

    public void setTeamSituation(String teamSituation) {
        this.teamSituation = teamSituation == null ? null : teamSituation.trim();
    }

    public String getCompanySituation() {
        return companySituation;
    }

    public void setCompanySituation(String companySituation) {
        this.companySituation = companySituation == null ? null : companySituation.trim();
    }

    public Date getInfoConfirmDate() {
        return infoConfirmDate;
    }

    public void setInfoConfirmDate(Date infoConfirmDate) {
        this.infoConfirmDate = infoConfirmDate;
    }

    public Integer getInfoConfirmBy() {
        return infoConfirmBy;
    }

    public void setInfoConfirmBy(Integer infoConfirmBy) {
        this.infoConfirmBy = infoConfirmBy;
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

    public String getDepositConfirmStatus() {
        return depositConfirmStatus;
    }

    public void setDepositConfirmStatus(String depositConfirmStatus) {
        this.depositConfirmStatus = depositConfirmStatus == null ? null : depositConfirmStatus.trim();
    }

    public Date getDepositConfirmDate() {
        return depositConfirmDate;
    }

    public void setDepositConfirmDate(Date depositConfirmDate) {
        this.depositConfirmDate = depositConfirmDate;
    }

    public Integer getDepositConfirmBy() {
        return depositConfirmBy;
    }

    public void setDepositConfirmBy(Integer depositConfirmBy) {
        this.depositConfirmBy = depositConfirmBy;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat == null ? null : wechat.trim();
    }

    public String getUsedExpress() {
        return usedExpress;
    }

    public void setUsedExpress(String usedExpress) {
        this.usedExpress = usedExpress == null ? null : usedExpress.trim();
    }

    public BigDecimal getFeeRate() {
        return feeRate;
    }

    public void setFeeRate(BigDecimal feeRate) {
        this.feeRate = feeRate;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
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