package com.jf.entity;

import java.math.BigDecimal;
import java.util.Date;

public class ToutiaoAdvertiserInfo {
    private Integer id;

    private String advertiserId;

    private String name;

    private String description;

    private String email;

    private String contacter;

    private String phonenumber;

    private String role;

    private String status;

    private String telephone;

    private String address;

    private String licenseUrl;

    private String licenseNo;

    private String licenseProvince;

    private String licenseCity;

    private String company;

    private String brand;

    private String promotionArea;

    private String promotionCenterProvince;

    private String promotionCenterCity;

    private String industry;

    private String reason;

    private BigDecimal balance;

    private Integer campaignPage;

    private Integer campaignPageSize;

    private Integer campaignTotalNumber;

    private Integer campaignTotalPage;

    private Integer adPage;

    private Integer adPageSize;

    private Integer adTotalNumber;

    private Integer adTotalPage;

    private Integer batchCode;

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

    public String getAdvertiserId() {
        return advertiserId;
    }

    public void setAdvertiserId(String advertiserId) {
        this.advertiserId = advertiserId == null ? null : advertiserId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getContacter() {
        return contacter;
    }

    public void setContacter(String contacter) {
        this.contacter = contacter == null ? null : contacter.trim();
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber == null ? null : phonenumber.trim();
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role == null ? null : role.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getLicenseUrl() {
        return licenseUrl;
    }

    public void setLicenseUrl(String licenseUrl) {
        this.licenseUrl = licenseUrl == null ? null : licenseUrl.trim();
    }

    public String getLicenseNo() {
        return licenseNo;
    }

    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo == null ? null : licenseNo.trim();
    }

    public String getLicenseProvince() {
        return licenseProvince;
    }

    public void setLicenseProvince(String licenseProvince) {
        this.licenseProvince = licenseProvince == null ? null : licenseProvince.trim();
    }

    public String getLicenseCity() {
        return licenseCity;
    }

    public void setLicenseCity(String licenseCity) {
        this.licenseCity = licenseCity == null ? null : licenseCity.trim();
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand == null ? null : brand.trim();
    }

    public String getPromotionArea() {
        return promotionArea;
    }

    public void setPromotionArea(String promotionArea) {
        this.promotionArea = promotionArea == null ? null : promotionArea.trim();
    }

    public String getPromotionCenterProvince() {
        return promotionCenterProvince;
    }

    public void setPromotionCenterProvince(String promotionCenterProvince) {
        this.promotionCenterProvince = promotionCenterProvince == null ? null : promotionCenterProvince.trim();
    }

    public String getPromotionCenterCity() {
        return promotionCenterCity;
    }

    public void setPromotionCenterCity(String promotionCenterCity) {
        this.promotionCenterCity = promotionCenterCity == null ? null : promotionCenterCity.trim();
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry == null ? null : industry.trim();
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Integer getCampaignPage() {
        return campaignPage;
    }

    public void setCampaignPage(Integer campaignPage) {
        this.campaignPage = campaignPage;
    }

    public Integer getCampaignPageSize() {
        return campaignPageSize;
    }

    public void setCampaignPageSize(Integer campaignPageSize) {
        this.campaignPageSize = campaignPageSize;
    }

    public Integer getCampaignTotalNumber() {
        return campaignTotalNumber;
    }

    public void setCampaignTotalNumber(Integer campaignTotalNumber) {
        this.campaignTotalNumber = campaignTotalNumber;
    }

    public Integer getCampaignTotalPage() {
        return campaignTotalPage;
    }

    public void setCampaignTotalPage(Integer campaignTotalPage) {
        this.campaignTotalPage = campaignTotalPage;
    }

    public Integer getAdPage() {
        return adPage;
    }

    public void setAdPage(Integer adPage) {
        this.adPage = adPage;
    }

    public Integer getAdPageSize() {
        return adPageSize;
    }

    public void setAdPageSize(Integer adPageSize) {
        this.adPageSize = adPageSize;
    }

    public Integer getAdTotalNumber() {
        return adTotalNumber;
    }

    public void setAdTotalNumber(Integer adTotalNumber) {
        this.adTotalNumber = adTotalNumber;
    }

    public Integer getAdTotalPage() {
        return adTotalPage;
    }

    public void setAdTotalPage(Integer adTotalPage) {
        this.adTotalPage = adTotalPage;
    }

    public Integer getBatchCode() {
        return batchCode;
    }

    public void setBatchCode(Integer batchCode) {
        this.batchCode = batchCode;
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