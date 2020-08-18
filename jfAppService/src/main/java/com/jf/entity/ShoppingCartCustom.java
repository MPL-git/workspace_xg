package com.jf.entity;

import java.math.BigDecimal;


public class ShoppingCartCustom extends ShoppingCart{
	private String productName;
	private String activityAreaName;
	private String productPropDesc;
	private BigDecimal tagPrice;
	private BigDecimal salePrice;
	private BigDecimal mallPrice;
	private BigDecimal manageSelfFreight;
	private String productPic;
	private String preferentialType;
	private Integer limitBuy;
	private String templetSuffix;
	private String activityAreaType;
	private String shopName;
	private String propValId;
	private Integer freightTemplateId;
	private String suitGroup;
	private String suitSex;
	private String saleType;
	private String season;
	private String productDesc;
	private String mainPicGroup;
	private String descPicGroup;
	private Integer stockSum;
	private Integer cloudProductItemId;
	private String isSingleProp;
	private String isSvipBuy;
	private Integer mchtId;
	private Integer brandId;
	private Integer productType1Id;
	private Integer productType2Id;
	private Integer productTypeId;
	private BigDecimal popFeeRate;
	/**
	* svip折扣
	*/
	private BigDecimal svipDiscount;
	/**
	 * 商品状态 0下架 1上架
	 */
	private String productStatus;
	/**
	 * 库存冻结数量
	 */
	private Integer lockedAmount;
	/**
	 * 库存
	 */
	private Integer stock;
	/**
	 * 会场形式(1会场 2品牌特卖)
	 */
	private String source;
	/**
	 * 商品id
	 */
	private Integer productId;
	/**
	 * 商品券优惠归属
	 */
	private String productCouponBelong;
	/**
	 * 商品券优惠金额
	 */
	private BigDecimal productCouponAmount;
	/**
	 * sku定金优惠总金额
	 */
	private BigDecimal skuDepositTotalAmount;
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductPropDesc() {
		return productPropDesc;
	}
	public void setProductPropDesc(String productPropDesc) {
		this.productPropDesc = productPropDesc;
	}
	public BigDecimal getTagPrice() {
		return tagPrice;
	}
	public void setTagPrice(BigDecimal tagPrice) {
		this.tagPrice = tagPrice;
	}
	public BigDecimal getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}
	public String getProductPic() {
		return productPic;
	}
	public void setProductPic(String productPic) {
		this.productPic = productPic;
	}
	public String getActivityAreaName() {
		return activityAreaName;
	}
	public void setActivityAreaName(String activityAreaName) {
		this.activityAreaName = activityAreaName;
	}
	public String getPreferentialType() {
		return preferentialType;
	}
	public void setPreferentialType(String preferentialType) {
		this.preferentialType = preferentialType;
	}
	public Integer getLimitBuy() {
		return limitBuy;
	}
	public void setLimitBuy(Integer limitBuy) {
		this.limitBuy = limitBuy;
	}


	public BigDecimal getManageSelfFreight() {
		return manageSelfFreight;
	}

	public void setManageSelfFreight(BigDecimal manageSelfFreight) {
		this.manageSelfFreight = manageSelfFreight;
	}

	/**
	 * 会场形式(1会场2品牌特卖)  
	 * @return source 会场形式(1会场2品牌特卖)  
	 */
	public String getSource() {
		return source;
	}
	
	/**  
	 * 会场形式(1会场2品牌特卖)  
	 * @param source 会场形式(1会场2品牌特卖)  
	 */
	public void setSource(String source) {
		this.source = source;
	}
	/**  
	 * 库存冻结数量  
	 * @return lockedAmount 库存冻结数量  
	 */
	public Integer getLockedAmount() {
		return lockedAmount;
	}
	
	/**  
	 * 库存冻结数量  
	 * @param lockedAmount 库存冻结数量  
	 */
	public void setLockedAmount(Integer lockedAmount) {
		this.lockedAmount = lockedAmount;
	}
	
	/**  
	 * 库存  
	 * @return stock 库存  
	 */
	public Integer getStock() {
		return stock;
	}
	
	/**  
	 * 库存  
	 * @param stock 库存  
	 */
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	/**  
	 * 商品id  
	 * @return productId 商品id  
	 */
	public Integer getProductId() {
		return productId;
	}
	
	/**  
	 * 商品id  
	 * @param productId 商品id  
	 */
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	/**  
	 * 商品状态0下架1上架  
	 * @return productStatus 商品状态0下架1上架  
	 */
	public String getProductStatus() {
		return productStatus;
	}
	
	/**  
	 * 商品状态0下架1上架  
	 * @param productStatus 商品状态0下架1上架  
	 */
	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}
	/**  
	 * templetSuffix  
	 * @return templetSuffix templetSuffix  
	 */
	public String getTempletSuffix() {
		return templetSuffix;
	}
	
	/**  
	 * templetSuffix  
	 * @param templetSuffix templetSuffix  
	 */
	public void setTempletSuffix(String templetSuffix) {
		this.templetSuffix = templetSuffix;
	}
	/**  
	 * activityAreaType  
	 * @return activityAreaType activityAreaType  
	 */
	public String getActivityAreaType() {
		return activityAreaType;
	}
	
	/**  
	 * activityAreaType  
	 * @param activityAreaType activityAreaType  
	 */
	public void setActivityAreaType(String activityAreaType) {
		this.activityAreaType = activityAreaType;
	}
	/**  
	 * shopName  
	 * @return shopName shopName  
	 */
	public String getShopName() {
		return shopName;
	}
	
	/**  
	 * shopName  
	 * @param shopName shopName  
	 */
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	/**  
	 * mallPrice  
	 * @return mallPrice mallPrice  
	 */
	public BigDecimal getMallPrice() {
		return mallPrice;
	}
	
	/**  
	 * mallPrice  
	 * @param mallPrice mallPrice  
	 */
	public void setMallPrice(BigDecimal mallPrice) {
		this.mallPrice = mallPrice;
	}
	/**  
	 * propValId  
	 * @return propValId propValId  
	 */
	public String getPropValId() {
		return propValId;
	}
	
	/**  
	 * propValId  
	 * @param propValId propValId  
	 */
	public void setPropValId(String propValId) {
		this.propValId = propValId;
	}
	public Integer getFreightTemplateId() {
		return freightTemplateId;
	}
	
	public void setFreightTemplateId(Integer freightTemplateId) {
		this.freightTemplateId = freightTemplateId;
	}
	public String getSuitGroup() {
		return suitGroup;
	}
	
	public void setSuitGroup(String suitGroup) {
		this.suitGroup = suitGroup;
	}
	
	public String getSuitSex() {
		return suitSex;
	}
	
	public void setSuitSex(String suitSex) {
		this.suitSex = suitSex;
	}
	
	public String getSeason() {
		return season;
	}
	
	public void setSeason(String season) {
		this.season = season;
	}
	
	public String getProductDesc() {
		return productDesc;
	}
	
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	
	public String getMainPicGroup() {
		return mainPicGroup;
	}
	
	public void setMainPicGroup(String mainPicGroup) {
		this.mainPicGroup = mainPicGroup;
	}
	
	public String getDescPicGroup() {
		return descPicGroup;
	}
	
	public void setDescPicGroup(String descPicGroup) {
		this.descPicGroup = descPicGroup;
	}
	public Integer getProductType1Id() {
		return productType1Id;
	}
	
	public void setProductType1Id(Integer productType1Id) {
		this.productType1Id = productType1Id;
	}
	public Integer getStockSum() {
		return stockSum;
	}
	
	public void setStockSum(Integer stockSum) {
		this.stockSum = stockSum;
	}
	public String getIsSingleProp() {
		return isSingleProp;
	}
	
	public void setIsSingleProp(String isSingleProp) {
		this.isSingleProp = isSingleProp;
	}
	public Integer getCloudProductItemId() {
		return cloudProductItemId;
	}
	
	public void setCloudProductItemId(Integer cloudProductItemId) {
		this.cloudProductItemId = cloudProductItemId;
	}
	public BigDecimal getSvipDiscount() {
		return svipDiscount;
	}
	
	public void setSvipDiscount(BigDecimal svipDiscount) {
		this.svipDiscount = svipDiscount;
	}
	public String getIsSvipBuy() {
		return isSvipBuy;
	}
	
	public void setIsSvipBuy(String isSvipBuy) {
		this.isSvipBuy = isSvipBuy;
	}
	public BigDecimal getSkuDepositTotalAmount() {
		return skuDepositTotalAmount;
	}
	
	public void setSkuDepositTotalAmount(BigDecimal skuDepositTotalAmount) {
		this.skuDepositTotalAmount = skuDepositTotalAmount;
	}
	
	public String getProductCouponBelong() {
		return productCouponBelong;
	}
	
	public void setProductCouponBelong(String productCouponBelong) {
		this.productCouponBelong = productCouponBelong;
	}
	
	public BigDecimal getProductCouponAmount() {
		return productCouponAmount;
	}
	
	public void setProductCouponAmount(BigDecimal productCouponAmount) {
		this.productCouponAmount = productCouponAmount;
	}
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
	public BigDecimal getPopFeeRate() {
		return popFeeRate;
	}
	
	public void setPopFeeRate(BigDecimal popFeeRate) {
		this.popFeeRate = popFeeRate;
	}

	public String getSaleType() {
		return saleType;
	}

	public void setSaleType(String saleType) {
		this.saleType = saleType;
	}
}