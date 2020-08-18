package com.jf.entity;

import com.jf.common.ext.core.Model;

import java.math.BigDecimal;
import java.util.Date;

public class Product extends Model {
    private Integer id;

    private Integer mchtId;

    private String code;

    private String source;

    private String refId;

    private String name;

    private String status;

    private String offReason;

    private String auditStatus;

    private Integer auditBy;

    private String auditRemarks;

    private Integer brandId;

    private Integer productType1Id;

    private Integer productType2Id;

    private Integer productTypeId;

    private String artNo;

    private String suitSex;

    private String suitGroup;

    private String year;

    private String season;

    private Integer limitBuy;

    private Integer csTempletId;

    private String remarksColor;

    private String saleType;

    private Date delDate;

    private String isSingleProp;

    private String shopProductCustomTypeIdGroup;

    private Integer weights;

    private String isActivity;

    private String isShow;

    private Integer minSalePriceItemId;

    private Integer minPriceItemId;

    private BigDecimal minMallPrice;

    private BigDecimal minSalePrice;

    private BigDecimal minTagPrice;

    private Integer saleWeight;

    private Integer freightTemplateId;

    private String isShopShow;

    private String searchFields;

    private String thirdPlatformCouponInfo;

    private Integer sort;

    private Integer commentWeight;

    private BigDecimal svipDiscount;

    private String isReturn7days;

    private String shelfLife;

    private String cccNo;

    private String yearOfListing;

    private Integer virtualSales;

    private Integer virtualPayers;

    private Integer createBy;

    private Date createDate;

    private Integer updateBy;

    private Date updateDate;

    private String remarks;

    private String delFlag;

    private String recommendRemarks;

    private String productDesc;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId == null ? null : refId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getOffReason() {
        return offReason;
    }

    public void setOffReason(String offReason) {
        this.offReason = offReason == null ? null : offReason.trim();
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus == null ? null : auditStatus.trim();
    }

    public Integer getAuditBy() {
        return auditBy;
    }

    public void setAuditBy(Integer auditBy) {
        this.auditBy = auditBy;
    }

    public String getAuditRemarks() {
        return auditRemarks;
    }

    public void setAuditRemarks(String auditRemarks) {
        this.auditRemarks = auditRemarks == null ? null : auditRemarks.trim();
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public Integer getProductType1Id() {
        return productType1Id;
    }

    public void setProductType1Id(Integer productType1Id) {
        this.productType1Id = productType1Id;
    }

    public Integer getProductType2Id() {
        return productType2Id;
    }

    public void setProductType2Id(Integer productType2Id) {
        this.productType2Id = productType2Id;
    }

    public Integer getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Integer productTypeId) {
        this.productTypeId = productTypeId;
    }

    public String getArtNo() {
        return artNo;
    }

    public void setArtNo(String artNo) {
        this.artNo = artNo == null ? null : artNo.trim();
    }

    public String getSuitSex() {
        return suitSex;
    }

    public void setSuitSex(String suitSex) {
        this.suitSex = suitSex == null ? null : suitSex.trim();
    }

    public String getSuitGroup() {
        return suitGroup;
    }

    public void setSuitGroup(String suitGroup) {
        this.suitGroup = suitGroup == null ? null : suitGroup.trim();
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year == null ? null : year.trim();
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season == null ? null : season.trim();
    }

    public Integer getLimitBuy() {
        return limitBuy;
    }

    public void setLimitBuy(Integer limitBuy) {
        this.limitBuy = limitBuy;
    }

    public Integer getCsTempletId() {
        return csTempletId;
    }

    public void setCsTempletId(Integer csTempletId) {
        this.csTempletId = csTempletId;
    }

    public String getRemarksColor() {
        return remarksColor;
    }

    public void setRemarksColor(String remarksColor) {
        this.remarksColor = remarksColor == null ? null : remarksColor.trim();
    }

    public String getSaleType() {
        return saleType;
    }

    public void setSaleType(String saleType) {
        this.saleType = saleType == null ? null : saleType.trim();
    }

    public Date getDelDate() {
        return delDate;
    }

    public void setDelDate(Date delDate) {
        this.delDate = delDate;
    }

    public String getIsSingleProp() {
        return isSingleProp;
    }

    public void setIsSingleProp(String isSingleProp) {
        this.isSingleProp = isSingleProp == null ? null : isSingleProp.trim();
    }

    public String getShopProductCustomTypeIdGroup() {
        return shopProductCustomTypeIdGroup;
    }

    public void setShopProductCustomTypeIdGroup(String shopProductCustomTypeIdGroup) {
        this.shopProductCustomTypeIdGroup = shopProductCustomTypeIdGroup == null ? null : shopProductCustomTypeIdGroup.trim();
    }

    public Integer getWeights() {
        return weights;
    }

    public void setWeights(Integer weights) {
        this.weights = weights;
    }

    public String getIsActivity() {
        return isActivity;
    }

    public void setIsActivity(String isActivity) {
        this.isActivity = isActivity == null ? null : isActivity.trim();
    }

    public String getIsShow() {
        return isShow;
    }

    public void setIsShow(String isShow) {
        this.isShow = isShow == null ? null : isShow.trim();
    }

    public Integer getMinSalePriceItemId() {
        return minSalePriceItemId;
    }

    public void setMinSalePriceItemId(Integer minSalePriceItemId) {
        this.minSalePriceItemId = minSalePriceItemId;
    }

    public Integer getMinPriceItemId() {
        return minPriceItemId;
    }

    public void setMinPriceItemId(Integer minPriceItemId) {
        this.minPriceItemId = minPriceItemId;
    }

    public BigDecimal getMinMallPrice() {
        return minMallPrice;
    }

    public void setMinMallPrice(BigDecimal minMallPrice) {
        this.minMallPrice = minMallPrice;
    }

    public BigDecimal getMinSalePrice() {
        return minSalePrice;
    }

    public void setMinSalePrice(BigDecimal minSalePrice) {
        this.minSalePrice = minSalePrice;
    }

    public BigDecimal getMinTagPrice() {
        return minTagPrice;
    }

    public void setMinTagPrice(BigDecimal minTagPrice) {
        this.minTagPrice = minTagPrice;
    }

    public Integer getSaleWeight() {
        return saleWeight;
    }

    public void setSaleWeight(Integer saleWeight) {
        this.saleWeight = saleWeight;
    }

    public Integer getFreightTemplateId() {
        return freightTemplateId;
    }

    public void setFreightTemplateId(Integer freightTemplateId) {
        this.freightTemplateId = freightTemplateId;
    }

    public String getIsShopShow() {
        return isShopShow;
    }

    public void setIsShopShow(String isShopShow) {
        this.isShopShow = isShopShow == null ? null : isShopShow.trim();
    }

    public String getSearchFields() {
        return searchFields;
    }

    public void setSearchFields(String searchFields) {
        this.searchFields = searchFields == null ? null : searchFields.trim();
    }

    public String getThirdPlatformCouponInfo() {
        return thirdPlatformCouponInfo;
    }

    public void setThirdPlatformCouponInfo(String thirdPlatformCouponInfo) {
        this.thirdPlatformCouponInfo = thirdPlatformCouponInfo == null ? null : thirdPlatformCouponInfo.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getCommentWeight() {
        return commentWeight;
    }

    public void setCommentWeight(Integer commentWeight) {
        this.commentWeight = commentWeight;
    }

    public BigDecimal getSvipDiscount() {
        return svipDiscount;
    }

    public void setSvipDiscount(BigDecimal svipDiscount) {
        this.svipDiscount = svipDiscount;
    }

    public String getIsReturn7days() {
        return isReturn7days;
    }

    public void setIsReturn7days(String isReturn7days) {
        this.isReturn7days = isReturn7days == null ? null : isReturn7days.trim();
    }

    public String getShelfLife() {
        return shelfLife;
    }

    public void setShelfLife(String shelfLife) {
        this.shelfLife = shelfLife == null ? null : shelfLife.trim();
    }

    public String getCccNo() {
        return cccNo;
    }

    public void setCccNo(String cccNo) {
        this.cccNo = cccNo == null ? null : cccNo.trim();
    }

    public String getYearOfListing() {
        return yearOfListing;
    }

    public void setYearOfListing(String yearOfListing) {
        this.yearOfListing = yearOfListing == null ? null : yearOfListing.trim();
    }

    public Integer getVirtualSales() {
        return virtualSales;
    }

    public void setVirtualSales(Integer virtualSales) {
        this.virtualSales = virtualSales;
    }

    public Integer getVirtualPayers() {
        return virtualPayers;
    }

    public void setVirtualPayers(Integer virtualPayers) {
        this.virtualPayers = virtualPayers;
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

    public String getRecommendRemarks() {
        return recommendRemarks;
    }

    public void setRecommendRemarks(String recommendRemarks) {
        this.recommendRemarks = recommendRemarks == null ? null : recommendRemarks.trim();
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc == null ? null : productDesc.trim();
    }
}