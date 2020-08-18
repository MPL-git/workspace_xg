package com.jf.entity;

import java.math.BigDecimal;

public class ActivityAreaCustom extends ActivityArea{
	/**
	 * 活动名称
	 */
	private String activityName;
	/**
	 * 活动入口图
	 */
	private String activityEntryPic;
	/**
	 * 活动品牌logo图
	 */
	private String logo;
	/**
	 * 活动id
	 */
	private Integer activityId;
	/**
	 * 商品id
	 */
	private Integer productId;
	/**
	 * 商品名称
	 */
	private String productName;
	/**
	 * 适合性别  00 都不选 11 都选  10 选男不选女   01 选女不选男
	 */
	private String suitSex;
	/**
	 * 适合人群 100青年人   010儿童幼儿   001中老年人
	 */
	private String suitGroup;
	/**
	 * 商品类目id
	 */
	private Integer productTypeId;
	/**
	 * 商品默认主图
	 */
	private String pic;
	/**
	 * 商品sku库存
	 */
	private Integer stock;
	/**
	 * 商品sku冻结库存
	 */
    private Integer lockedAmount;
    /**
	 * 商品吊牌价
	 */
    private BigDecimal tagPrice;
    /**
	 * 商品销售价
	 */
    private BigDecimal salePrice;
    /**
	 * 商品折扣
	 */
    private BigDecimal discount;
    /**
	 * 商品sku总库存（总的stock-总的lockedAmount）
	 */
    private Integer stockSum;
    /**
     * 是否添加水印
     */
    private String isWatermark;
    
    /**
     * 商品svip折扣
     */
    private BigDecimal svipDiscount;
    /**
     * 定金
     */
    private BigDecimal deposit;
    /**
     * 抵扣金额
     */
    private BigDecimal deductAmount;
    private BigDecimal maxSalePrice;
	/**  
	 * 活动名称  
	 * @return activityName 活动名称  
	 */
	public String getActivityName() {
		return activityName;
	}
	
	/**  
	 * 活动名称  
	 * @param activityName 活动名称  
	 */
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	
	/**  
	 * 活动入口图  
	 * @return activityEntryPic 活动入口图  
	 */
	public String getActivityEntryPic() {
		return activityEntryPic;
	}
	
	/**  
	 * 活动入口图  
	 * @param activityEntryPic 活动入口图  
	 */
	public void setActivityEntryPic(String activityEntryPic) {
		this.activityEntryPic = activityEntryPic;
	}
	
	/**  
	 * 活动品牌logo图  
	 * @return logo 活动品牌logo图  
	 */
	public String getLogo() {
		return logo;
	}
	
	/**  
	 * 活动品牌logo图  
	 * @param logo 活动品牌logo图  
	 */
	public void setLogo(String logo) {
		this.logo = logo;
	}
	
	/**  
	 * 活动id  
	 * @return activityId 活动id  
	 */
	public Integer getActivityId() {
		return activityId;
	}
	
	/**  
	 * 活动id  
	 * @param activityId 活动id  
	 */
	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
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
	 * 商品名称  
	 * @return productName 商品名称  
	 */
	public String getProductName() {
		return productName;
	}
	

	/**  
	 * 商品名称  
	 * @param productName 商品名称  
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}
	

	/**  
	 * 适合性别00都不选11都选10选男不选女01选女不选男  
	 * @return suitSex 适合性别00都不选11都选10选男不选女01选女不选男  
	 */
	public String getSuitSex() {
		return suitSex;
	}
	

	/**  
	 * 适合性别00都不选11都选10选男不选女01选女不选男  
	 * @param suitSex 适合性别00都不选11都选10选男不选女01选女不选男  
	 */
	public void setSuitSex(String suitSex) {
		this.suitSex = suitSex;
	}
	

	/**  
	 * 适合人群100青年人010儿童幼儿001中老年人  
	 * @return suitGroup 适合人群100青年人010儿童幼儿001中老年人  
	 */
	public String getSuitGroup() {
		return suitGroup;
	}
	

	/**  
	 * 适合人群100青年人010儿童幼儿001中老年人  
	 * @param suitGroup 适合人群100青年人010儿童幼儿001中老年人  
	 */
	public void setSuitGroup(String suitGroup) {
		this.suitGroup = suitGroup;
	}
	

	/**  
	 * 商品类目id  
	 * @return productTypeId 商品类目id  
	 */
	public Integer getProductTypeId() {
		return productTypeId;
	}
	

	/**  
	 * 商品类目id  
	 * @param productTypeId 商品类目id  
	 */
	public void setProductTypeId(Integer productTypeId) {
		this.productTypeId = productTypeId;
	}
	

	/**  
	 * 商品默认主图  
	 * @return pic 商品默认主图  
	 */
	public String getPic() {
		return pic;
	}
	

	/**  
	 * 商品默认主图  
	 * @param pic 商品默认主图  
	 */
	public void setPic(String pic) {
		this.pic = pic;
	}
	

	/**  
	 * 商品sku库存  
	 * @return stock 商品sku库存  
	 */
	public Integer getStock() {
		return stock;
	}
	

	/**  
	 * 商品sku库存  
	 * @param stock 商品sku库存  
	 */
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	

	/**  
	 * 商品sku冻结库存  
	 * @return lockedAmount 商品sku冻结库存  
	 */
	public Integer getLockedAmount() {
		return lockedAmount;
	}
	

	/**  
	 * 商品sku冻结库存  
	 * @param lockedAmount 商品sku冻结库存  
	 */
	public void setLockedAmount(Integer lockedAmount) {
		this.lockedAmount = lockedAmount;
	}
	

	/**  
	 * 商品吊牌价  
	 * @return tagPrice 商品吊牌价  
	 */
	public BigDecimal getTagPrice() {
		return tagPrice;
	}
	

	/**  
	 * 商品吊牌价  
	 * @param tagPrice 商品吊牌价  
	 */
	public void setTagPrice(BigDecimal tagPrice) {
		this.tagPrice = tagPrice;
	}
	

	/**  
	 * 商品销售价  
	 * @return salePrice 商品销售价  
	 */
	public BigDecimal getSalePrice() {
		return salePrice;
	}
	

	/**  
	 * 商品销售价  
	 * @param salePrice 商品销售价  
	 */
	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}
	

	/**  
	 * 商品折扣  
	 * @return discount 商品折扣  
	 */
	public BigDecimal getDiscount() {
		return discount;
	}
	

	/**  
	 * 商品折扣  
	 * @param discount 商品折扣  
	 */
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}
	

	/**  
	 * 商品sku总库存（总的stock-总的lockedAmount）  
	 * @return stockSum 商品sku总库存（总的stock-总的lockedAmount）  
	 */
	public Integer getStockSum() {
		return stockSum;
	}
	

	/**  
	 * 商品sku总库存（总的stock-总的lockedAmount）  
	 * @param stockSum 商品sku总库存（总的stock-总的lockedAmount）  
	 */
	public void setStockSum(Integer stockSum) {
		this.stockSum = stockSum;
	}

	public String getIsWatermark() {
		return isWatermark;
	}
	

	public void setIsWatermark(String isWatermark) {
		this.isWatermark = isWatermark;
	}

	public BigDecimal getSvipDiscount() {
		return svipDiscount;
	}
	

	public void setSvipDiscount(BigDecimal svipDiscount) {
		this.svipDiscount = svipDiscount;
	}

	public BigDecimal getDeposit() {
		return deposit;
	}

	public void setDeposit(BigDecimal deposit) {
		this.deposit = deposit;
	}

	public BigDecimal getDeductAmount() {
		return deductAmount;
	}

	public void setDeductAmount(BigDecimal deductAmount) {
		this.deductAmount = deductAmount;
	}

	public BigDecimal getMaxSalePrice() {
		return maxSalePrice;
	}

	public void setMaxSalePrice(BigDecimal maxSalePrice) {
		this.maxSalePrice = maxSalePrice;
	}

}