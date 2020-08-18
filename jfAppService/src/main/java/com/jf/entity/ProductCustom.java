package com.jf.entity;

import java.math.BigDecimal;

public class ProductCustom extends Product {

	private String propValId;

	private Integer lockedAmount;

	private Integer stock;
	/**
	 * 总库存stockSum = stock - lockedAmount
	 */
	private Integer stockSum;
	/**
	 * 销售价
	 */
	private BigDecimal salePrice;
	/**
	 * 吊牌价
	 */
	private BigDecimal tagPrice;

	private String propName;

	private String propValue;

	private Integer propValueId;

	private String pic;

	private Integer propId;
	private Integer productSaleQuantity;
	private Integer goodProductScoreCount;

	/**
	 * 品牌名
	 */
	private String brandName;
	/**
	 * 商品购买数量
	 */
	private Integer productBuyNum;

	/**
	 * 商城价
	 */
	private BigDecimal mallPrice;

	/**
	 * 首字母
	 */
	private String letterIndex;

    /**
     * 店铺状态(0关闭 1开通)
     */
    private String shopStatus;
    /**
     * 一级品类名称
     */
    private String productType1Name;
    /**
     * 二级品类名称
     */
    private String productType2Name;
    
    /**
     * 三级品类名称
     */
    private String productTypeName;
    
    /**
     * sku名称
     */
    private String productPropdesc;
    
    /**
     * sku
     */
    private String sku;
    
    /**
     * pop佣金比例（pop使用）
     */
    private BigDecimal feeRate;
    
    /**
	 * 成本价
	 */
	private BigDecimal costPrice;
	
	/**
	 * 商家类型
	 */
    private String mchtType;
    /**
	 * 获得类型类型
	 */
    private String activityType;
    /**
     * 月销量
     */
    private Integer monthlySales;
    /**
	 * 淘宝客商品优惠券优惠金额
	 */
	private BigDecimal couponAmount;
	/**
	 * 淘宝客商品折后价格
	 */
	private BigDecimal zkFinalPrice;
	/**
	 * 淘宝客商品原价
	 */
	private BigDecimal reservePrice;
	/**
	 * 是否支持7天无理由退换货\r\n0 否\r\n1 是\r\n2 可选择（商家自主决定）
	 */
	private String productType7days;
	/**
	 * 每日好货商品点赞数量
	 */
	private Integer supportQuantity;
	/**
	 * 每日好货资源位管理id
	 */
	private Integer sourceNicheId;
	
	private Integer totalCommentCount;
	
	private String activityInfo;
	
	private String fullCutRule;
	
	private String storageConditions;
	
	private String placeOfOrigin;
	
	private String producerInformation;
	
	private String approvalNumber;

	//爆款推荐
	private Integer orderCount90Days; //商品90天销量

	/**
	 * 是否自营（SPOP）
	 */
	private String isManageSelf;

	public String getIsManageSelf() {
		return isManageSelf;
	}

	public void setIsManageSelf(String isManageSelf) {
		this.isManageSelf = isManageSelf;
	}

	public Integer getPayCount() {
		return payCount;
	}

	public void setPayCount(Integer payCount) {
		this.payCount = payCount;
	}

	private Integer payCount;

	public Integer getOrderCount90Days() {
		return orderCount90Days;
	}

	public void setOrderCount90Days(Integer orderCount90Days) {
		this.orderCount90Days = orderCount90Days;
	}

	public String getPropValId() {
		return propValId;
	}

	public void setPropValId(String propValId) {
		this.propValId = propValId;
	}

	public Integer getLockedAmount() {
		return lockedAmount;
	}

	public void setLockedAmount(Integer lockedAmount) {
		this.lockedAmount = lockedAmount;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public String getPropName() {
		return propName;
	}

	public void setPropName(String propName) {
		this.propName = propName;
	}

	public String getPropValue() {
		return propValue;
	}

	public void setPropValue(String propValue) {
		this.propValue = propValue;
	}

	public Integer getPropValueId() {
		return propValueId;
	}

	public void setPropValueId(Integer propValueId) {
		this.propValueId = propValueId;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public Integer getPropId() {
		return propId;
	}

	public void setPropId(Integer propId) {
		this.propId = propId;
	}

	/**
	 * 品牌名
	 * 
	 * @return brandName 品牌名
	 */
	public String getBrandName() {
		return brandName;
	}

	/**
	 * 品牌名
	 * 
	 * @param brandName
	 *            品牌名
	 */
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	/**
	 * 销售价
	 * 
	 * @return salePrice 销售价
	 */
	public BigDecimal getSalePrice() {
		return salePrice;
	}

	/**
	 * 销售价
	 * 
	 * @param salePrice
	 *            销售价
	 */
	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}

	/**
	 * 吊牌价
	 * 
	 * @return tagPrice 吊牌价
	 */
	public BigDecimal getTagPrice() {
		return tagPrice;
	}

	/**
	 * 吊牌价
	 * 
	 * @param tagPrice
	 *            吊牌价
	 */
	public void setTagPrice(BigDecimal tagPrice) {
		this.tagPrice = tagPrice;
	}

	/**
	 * 商品购买数量
	 * 
	 * @return productBuyNum 商品购买数量
	 */
	public Integer getProductBuyNum() {
		return productBuyNum;
	}

	/**
	 * 商品购买数量
	 * 
	 * @param productBuyNum
	 *            商品购买数量
	 */
	public void setProductBuyNum(Integer productBuyNum) {
		this.productBuyNum = productBuyNum;
	}

	/**
	 * 商城价
	 * 
	 * @return mallPrice 商城价
	 */
	public BigDecimal getMallPrice() {
		return mallPrice;
	}

	/**
	 * 商城价
	 * 
	 * @param mallPrice
	 *            商城价
	 */
	public void setMallPrice(BigDecimal mallPrice) {
		this.mallPrice = mallPrice;
	}

	/**
	 * 总库存stockSum=stock-lockedAmount
	 * 
	 * @return stockSum 总库存stockSum=stock-lockedAmount
	 */
	public Integer getStockSum() {
		return stockSum;
	}

	/**
	 * 总库存stockSum=stock-lockedAmount
	 * 
	 * @param stockSum
	 *            总库存stockSum=stock-lockedAmount
	 */
	public void setStockSum(Integer stockSum) {
		this.stockSum = stockSum;
	}

	public String getLetterIndex() {
		return letterIndex;
	}

	public void setLetterIndex(String letterIndex) {
		this.letterIndex = letterIndex;
	}

	public Integer getProductSaleQuantity() {
		return productSaleQuantity;
	}

	public void setProductSaleQuantity(Integer productSaleQuantity) {
		this.productSaleQuantity = productSaleQuantity;
	}

	public Integer getGoodProductScoreCount() {
		return goodProductScoreCount;
	}

	public void setGoodProductScoreCount(Integer goodProductScoreCount) {
		this.goodProductScoreCount = goodProductScoreCount;
	}

	public String getShopStatus() {
		return shopStatus;
	}
	

	public void setShopStatus(String shopStatus) {
		this.shopStatus = shopStatus;
	}

	public String getProductTypeName() {
		return productTypeName;
	}

	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}

	public String getProductPropdesc() {
		return productPropdesc;
	}
	

	public void setProductPropdesc(String productPropdesc) {
		this.productPropdesc = productPropdesc;
	}

	public String getSku() {
		return sku;
	}
	

	public void setSku(String sku) {
		this.sku = sku;
	}

	public BigDecimal getFeeRate() {
		return feeRate;
	}
	

	public void setFeeRate(BigDecimal feeRate) {
		this.feeRate = feeRate;
	}

	public BigDecimal getCostPrice() {
		return costPrice;
	}
	

	public void setCostPrice(BigDecimal costPrice) {
		this.costPrice = costPrice;
	}

	public String getMchtType() {
		return mchtType;
	}
	

	public void setMchtType(String mchtType) {
		this.mchtType = mchtType;
	}

	public String getProductType1Name() {
		return productType1Name;
	}
	

	public void setProductType1Name(String productType1Name) {
		this.productType1Name = productType1Name;
	}
	

	public String getProductType2Name() {
		return productType2Name;
	}
	

	public void setProductType2Name(String productType2Name) {
		this.productType2Name = productType2Name;
	}

	public String getActivityType() {
		return activityType;
	}
	

	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}

	public Integer getMonthlySales() {
		return monthlySales;
	}
	

	public void setMonthlySales(Integer monthlySales) {
		this.monthlySales = monthlySales;
	}

	public BigDecimal getCouponAmount() {
		return couponAmount;
	}
	

	public void setCouponAmount(BigDecimal couponAmount) {
		this.couponAmount = couponAmount;
	}

	public BigDecimal getZkFinalPrice() {
		return zkFinalPrice;
	}
	

	public void setZkFinalPrice(BigDecimal zkFinalPrice) {
		this.zkFinalPrice = zkFinalPrice;
	}

	public BigDecimal getReservePrice() {
		return reservePrice;
	}
	

	public void setReservePrice(BigDecimal reservePrice) {
		this.reservePrice = reservePrice;
	}

	public String getProductType7days() {
		return productType7days;
	}
	

	public void setProductType7days(String productType7days) {
		this.productType7days = productType7days;
	}

	public Integer getSupportQuantity() {
		return supportQuantity;
	}
	

	public void setSupportQuantity(Integer supportQuantity) {
		this.supportQuantity = supportQuantity;
	}

	public Integer getSourceNicheId() {
		return sourceNicheId;
	}
	

	public void setSourceNicheId(Integer sourceNicheId) {
		this.sourceNicheId = sourceNicheId;
	}

	public Integer getTotalCommentCount() {
		return totalCommentCount;
	}

	public void setTotalCommentCount(Integer totalCommentCount) {
		this.totalCommentCount = totalCommentCount;
	}

	public String getActivityInfo() {
		return activityInfo;
	}

	public void setActivityInfo(String activityInfo) {
		this.activityInfo = activityInfo;
	}

	public String getFullCutRule() {
		return fullCutRule;
	}

	public void setFullCutRule(String fullCutRule) {
		this.fullCutRule = fullCutRule;
	}

	public String getStorageConditions() {
		return storageConditions;
	}

	public void setStorageConditions(String storageConditions) {
		this.storageConditions = storageConditions;
	}

	public String getPlaceOfOrigin() {
		return placeOfOrigin;
	}

	public void setPlaceOfOrigin(String placeOfOrigin) {
		this.placeOfOrigin = placeOfOrigin;
	}

	public String getProducerInformation() {
		return producerInformation;
	}

	public void setProducerInformation(String producerInformation) {
		this.producerInformation = producerInformation;
	}

	public String getApprovalNumber() {
		return approvalNumber;
	}

	public void setApprovalNumber(String approvalNumber) {
		this.approvalNumber = approvalNumber;
	}


}