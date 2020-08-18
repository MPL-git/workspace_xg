package com.jf.entity;

import java.math.BigDecimal;
import java.util.Date;

public class TypeColumnPvStatisticalCustom extends TypeColumnPvStatistical{
    private Integer productType1Id;
    private String name;
    private String addProductRate;
    private String submitOrderRate;
    private String paymentRate;
    private String weight;
    private Integer linkId;
    private Date upDate;
    private String productPic;
    private String productName;
    private String productCode;
    private String artbrandGroupEasy;
    private String producttype1Name;
    private BigDecimal tagPriceMin;
    private BigDecimal tagPriceMax;
    private BigDecimal salePriceMin;
    private BigDecimal salePriceMax;
    private String shopStatus;
    private String mchtType;
    private Date cooperateBeginDate;
    private String mchtCode;
    private String productTypeName;
    private String companyName;
    private String shopName;
    private String mchtProductBrandName;

    private Integer allVisitorsNum;//实时查询会员访客数
    private Integer allVisitorTouristNum;//实时查询非会员访客数

    public String getShopStatus() {
        return shopStatus;
    }

    public void setShopStatus(String shopStatus) {
        this.shopStatus = shopStatus;
    }

    public String getMchtType() {
        return mchtType;
    }

    public void setMchtType(String mchtType) {
        this.mchtType = mchtType;
    }

    public Date getCooperateBeginDate() {
        return cooperateBeginDate;
    }

    public void setCooperateBeginDate(Date cooperateBeginDate) {
        this.cooperateBeginDate = cooperateBeginDate;
    }

    public String getMchtCode() {
        return mchtCode;
    }

    public void setMchtCode(String mchtCode) {
        this.mchtCode = mchtCode;
    }

    public String getProductTypeName() {
        return productTypeName;
    }

    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getMchtProductBrandName() {
        return mchtProductBrandName;
    }

    public void setMchtProductBrandName(String mchtProductBrandName) {
        this.mchtProductBrandName = mchtProductBrandName;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public Integer getLinkId() {
        return linkId;
    }

    public void setLinkId(Integer linkId) {
        this.linkId = linkId;
    }

    public Date getUpDate() {
        return upDate;
    }

    public void setUpDate(Date upDate) {
        this.upDate = upDate;
    }

    public String getProductPic() {
        return productPic;
    }

    public void setProductPic(String productPic) {
        this.productPic = productPic;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getArtbrandGroupEasy() {
        return artbrandGroupEasy;
    }

    public void setArtbrandGroupEasy(String artbrandGroupEasy) {
        this.artbrandGroupEasy = artbrandGroupEasy;
    }

    public String getProducttype1Name() {
        return producttype1Name;
    }

    public void setProducttype1Name(String producttype1Name) {
        this.producttype1Name = producttype1Name;
    }

    public BigDecimal getTagPriceMin() {
        return tagPriceMin;
    }

    public void setTagPriceMin(BigDecimal tagPriceMin) {
        this.tagPriceMin = tagPriceMin;
    }

    public BigDecimal getTagPriceMax() {
        return tagPriceMax;
    }

    public void setTagPriceMax(BigDecimal tagPriceMax) {
        this.tagPriceMax = tagPriceMax;
    }

    public BigDecimal getSalePriceMin() {
        return salePriceMin;
    }

    public void setSalePriceMin(BigDecimal salePriceMin) {
        this.salePriceMin = salePriceMin;
    }

    public BigDecimal getSalePriceMax() {
        return salePriceMax;
    }

    public void setSalePriceMax(BigDecimal salePriceMax) {
        this.salePriceMax = salePriceMax;
    }

   // private String productCode;//商品编号
    private Integer productId;//商品id
    private String pic;//商品图片
    //private String shopName;//店铺名称
   // private String mchtCode;//商家编码
    private Integer mchtId;//商家id
    private Integer brandId;//品牌id
    private Integer typeId;//类目id

    public Integer getProductType1Id() {
        return productType1Id;
    }

    public void setProductType1Id(Integer productType1Id) {
        this.productType1Id = productType1Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddProductRate() {
        return addProductRate;
    }

    public void setAddProductRate(String addProductRate) {
        this.addProductRate = addProductRate;
    }

    public String getSubmitOrderRate() {
        return submitOrderRate;
    }

    public void setSubmitOrderRate(String submitOrderRate) {
        this.submitOrderRate = submitOrderRate;
    }

    public String getPaymentRate() {
        return paymentRate;
    }

    public void setPaymentRate(String paymentRate) {
        this.paymentRate = paymentRate;
    }

/*    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }*/

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

/*    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getMchtCode() {
        return mchtCode;
    }

    public void setMchtCode(String mchtCode) {
        this.mchtCode = mchtCode;
    }*/

    public Integer getMchtId() {
        return mchtId;
    }

    public void setMchtId(Integer mchtId) {
        this.mchtId = mchtId;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getAllVisitorsNum() {
        return allVisitorsNum;
    }

    public void setAllVisitorsNum(Integer allVisitorsNum) {
        this.allVisitorsNum = allVisitorsNum;
    }

    public Integer getAllVisitorTouristNum() {
        return allVisitorTouristNum;
    }

    public void setAllVisitorTouristNum(Integer allVisitorTouristNum) {
        this.allVisitorTouristNum = allVisitorTouristNum;
    }
}