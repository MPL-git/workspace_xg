package com.jf.entity;

import java.math.BigDecimal;
import java.util.Date;

public class ActivityCustom extends Activity{
    
    private Date activityBeginTime;
    
    private Date activityEndTime;
    //预热时间
    private Date preheatTime;
    
    private String preferentialType;
    
    private String preferentialIdGroup;
    
    private String productName;
    
    private String activityAreaName;
    
    private String pic;
    
    private String productTypeName;
    
    private String logo;
    
    private Double salePrice;
    
    private Double mallPrice;
    
    private Double tagPrice;
    
    private Integer activityAreaId;
    
    private Integer productId;
    /**
     * 库存
     */
    private Integer stock;
    /**
     * 冻结的库存
     */
    private Integer lockedAmount;
    /**
     * 商品总的可用库存stockSum = sum(stock-lockedAmount)
     */
    private Integer stockSum;
    
    private Integer limitBuy;
    
    private Integer productTypeId;
    
    private String source;
    
    private String type;
    
    private String benefitPoint;
    
    private Double discount;
    /**
     * 是否加水印 0否1是
     */
    private String isWatermark;
    
    /**
     * 活动商品数量
     */
    private Integer activityProductNum;
    
    /**
     * 会场入口图
     */
    private String areaEntryPic;
    
    /**
     * 商品默认主图
     */
    private String productPic;
    /**
     * 商品状态
     */
    private String productStatus;
    /**
     * 会场模板
     */
    private String templetSuffix;
    /**
     * 会场类型(1品牌活动 2单品活动 6单品爆款 7新用户专享)
     */
    private String activityAreaType;
    /**
     * 会场商品总售价
     */
    private BigDecimal activityAreaTotalSalePrice;
    /**
     * 会场分组id
     */
    private Integer areaGroupId;
    /**
     * 特卖品牌团id
     */
    private Integer activityBrandGroupId;
    /**
     * 是否单规格 \r\n0 否\r\n1 是
     */
    private String isSingleProp;
    /**
     * svip折扣
     */
    private BigDecimal svipDiscount;
    
    /**
     * 会场标签
     */
    private String areaLabel;
    /**
     * 会场标签图片
     */
    private String areaLabelPic;
    /**
     * 会场限购0.不限购1.限购
     */
    private String purchaseLimits;
    /**
     * 限购件数
     */
    private Integer purchaseLimitsQuantity;
    /**
     * 定金
     */
    private BigDecimal deposit;
    /**
     * 抵扣金额
     */
    private BigDecimal deductAmount;
    private String isPreSell;
    private BigDecimal maxSalePrice;

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

	public Date getActivityBeginTime() {
		return activityBeginTime;
	}

	public void setActivityBeginTime(Date activityBeginTime) {
		this.activityBeginTime = activityBeginTime;
	}

	public Date getActivityEndTime() {
		return activityEndTime;
	}

	public void setActivityEndTime(Date activityEndTime) {
		this.activityEndTime = activityEndTime;
	}

	public String getPreferentialType() {
		return preferentialType;
	}

	public void setPreferentialType(String preferentialType) {
		this.preferentialType = preferentialType;
	}

	public String getPreferentialIdGroup() {
		return preferentialIdGroup;
	}

	public void setPreferentialIdGroup(String preferentialIdGroup) {
		this.preferentialIdGroup = preferentialIdGroup;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getProductTypeName() {
		return productTypeName;
	}

	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}

	public Double getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
	}

	public Double getTagPrice() {
		return tagPrice;
	}

	public void setTagPrice(Double tagPrice) {
		this.tagPrice = tagPrice;
	}

	public Integer getActivityAreaId() {
		return activityAreaId;
	}

	public void setActivityAreaId(Integer activityAreaId) {
		this.activityAreaId = activityAreaId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Integer getLimitBuy() {
		return limitBuy;
	}

	public void setLimitBuy(Integer limitBuy) {
		this.limitBuy = limitBuy;
	}

	public Integer getProductTypeId() {
		return productTypeId;
	}

	public void setProductTypeId(Integer productTypeId) {
		this.productTypeId = productTypeId;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getActivityAreaName() {
		return activityAreaName;
	}

	public void setActivityAreaName(String activityAreaName) {
		this.activityAreaName = activityAreaName;
	}

	public Date getPreheatTime() {
		return preheatTime;
	}

	public void setPreheatTime(Date preheatTime) {
		this.preheatTime = preheatTime;
	}

	public String getBenefitPoint() {
		return benefitPoint;
	}

	public void setBenefitPoint(String benefitPoint) {
		this.benefitPoint = benefitPoint;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	/**  
	 * 活动商品数量  
	 * @return activityProductNum 活动商品数量  
	 */
	public Integer getActivityProductNum() {
		return activityProductNum;
	}
	

	/**  
	 * 活动商品数量  
	 * @param activityProductNum 活动商品数量  
	 */
	public void setActivityProductNum(Integer activityProductNum) {
		this.activityProductNum = activityProductNum;
	}

	/**  
	 * 会场入口图  
	 * @return areaEntryPic 会场入口图  
	 */
	public String getAreaEntryPic() {
		return areaEntryPic;
	}
	

	/**  
	 * 会场入口图  
	 * @param areaEntryPic 会场入口图  
	 */
	public void setAreaEntryPic(String areaEntryPic) {
		this.areaEntryPic = areaEntryPic;
	}

	/**  
	 * 商品默认主图  
	 * @return productPic 商品默认主图  
	 */
	public String getProductPic() {
		return productPic;
	}
	

	/**  
	 * 商品默认主图  
	 * @param productPic 商品默认主图  
	 */
	public void setProductPic(String productPic) {
		this.productPic = productPic;
	}

	/**  
	 * 商品状态  
	 * @return productStatus 商品状态  
	 */
	public String getProductStatus() {
		return productStatus;
	}
	

	/**  
	 * 商品总的可用库存stockSum=sum(stock-lockedAmount)  
	 * @return stockSum 商品总的可用库存stockSum=sum(stock-lockedAmount)  
	 */
	public Integer getStockSum() {
		return stockSum;
	}
	

	/**  
	 * 商品总的可用库存stockSum=sum(stock-lockedAmount)  
	 * @param stockSum 商品总的可用库存stockSum=sum(stock-lockedAmount)  
	 */
	public void setStockSum(Integer stockSum) {
		this.stockSum = stockSum;
	}
	

	/**  
	 * 商品状态  
	 * @param productStatus 商品状态  
	 */
	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}

	/**  
	 * 冻结的库存  
	 * @return lockedAmount 冻结的库存  
	 */
	public Integer getLockedAmount() {
		return lockedAmount;
	}
	

	/**  
	 * 冻结的库存  
	 * @param lockedAmount 冻结的库存  
	 */
	public void setLockedAmount(Integer lockedAmount) {
		this.lockedAmount = lockedAmount;
	}

	/**  
	 * 会场模板  
	 * @return templetSuffix 会场模板  
	 */
	public String getTempletSuffix() {
		return templetSuffix;
	}
	

	/**  
	 * 会场模板  
	 * @param templetSuffix 会场模板  
	 */
	public void setTempletSuffix(String templetSuffix) {
		this.templetSuffix = templetSuffix;
	}

	/**  
	 * 会场类型(1品牌活动2单品活动6单品爆款7新用户专享)  
	 * @return activityAreaType 会场类型(1品牌活动2单品活动6单品爆款7新用户专享)  
	 */
	public String getActivityAreaType() {
		return activityAreaType;
	}
	

	/**  
	 * 会场类型(1品牌活动2单品活动6单品爆款7新用户专享)  
	 * @param activityAreaType 会场类型(1品牌活动2单品活动6单品爆款7新用户专享)  
	 */
	public void setActivityAreaType(String activityAreaType) {
		this.activityAreaType = activityAreaType;
	}

	/**  
	 * 会场商品总售价  
	 * @return activityAreaTotalSalePrice 会场商品总售价  
	 */
	public BigDecimal getActivityAreaTotalSalePrice() {
		return activityAreaTotalSalePrice;
	}
	

	/**  
	 * 会场商品总售价  
	 * @param activityAreaTotalSalePrice 会场商品总售价  
	 */
	public void setActivityAreaTotalSalePrice(BigDecimal activityAreaTotalSalePrice) {
		this.activityAreaTotalSalePrice = activityAreaTotalSalePrice;
	}

	/**  
	 * 是否加水印0否1是  
	 * @return isWatermark 是否加水印0否1是  
	 */
	public String getIsWatermark() {
		return isWatermark;
	}
	

	/**  
	 * 是否加水印0否1是  
	 * @param isWatermark 是否加水印0否1是  
	 */
	public void setIsWatermark(String isWatermark) {
		this.isWatermark = isWatermark;
	}

	/**  
	 * 会场分组id  
	 * @return areaGroupId 会场分组id  
	 */
	public Integer getAreaGroupId() {
		return areaGroupId;
	}
	

	/**  
	 * 会场分组id  
	 * @param areaGroupId 会场分组id  
	 */
	public void setAreaGroupId(Integer areaGroupId) {
		this.areaGroupId = areaGroupId;
	}

	/**  
	 * mallPrice  
	 * @return mallPrice mallPrice  
	 */
	public Double getMallPrice() {
		return mallPrice;
	}
	

	/**  
	 * mallPrice  
	 * @param mallPrice mallPrice  
	 */
	public void setMallPrice(Double mallPrice) {
		this.mallPrice = mallPrice;
	}

	public Integer getActivityBrandGroupId() {
		return activityBrandGroupId;
	}
	

	public void setActivityBrandGroupId(Integer activityBrandGroupId) {
		this.activityBrandGroupId = activityBrandGroupId;
	}

	public String getIsSingleProp() {
		return isSingleProp;
	}
	

	public void setIsSingleProp(String isSingleProp) {
		this.isSingleProp = isSingleProp;
	}

	public BigDecimal getSvipDiscount() {
		return svipDiscount;
	}
	

	public void setSvipDiscount(BigDecimal svipDiscount) {
		this.svipDiscount = svipDiscount;
	}

	public String getAreaLabel() {
		return areaLabel;
	}
	

	public void setAreaLabel(String areaLabel) {
		this.areaLabel = areaLabel;
	}
	

	public Integer getPurchaseLimitsQuantity() {
		return purchaseLimitsQuantity;
	}
	

	public void setPurchaseLimitsQuantity(Integer purchaseLimitsQuantity) {
		this.purchaseLimitsQuantity = purchaseLimitsQuantity;
	}

	public String getPurchaseLimits() {
		return purchaseLimits;
	}
	

	public void setPurchaseLimits(String purchaseLimits) {
		this.purchaseLimits = purchaseLimits;
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

	public String getIsPreSell() {
		return isPreSell;
	}

	public void setIsPreSell(String isPreSell) {
		this.isPreSell = isPreSell;
	}

	public BigDecimal getMaxSalePrice() {
		return maxSalePrice;
	}

	public void setMaxSalePrice(BigDecimal maxSalePrice) {
		this.maxSalePrice = maxSalePrice;
	}

	public String getAreaLabelPic() {
		return areaLabelPic;
	}

	public void setAreaLabelPic(String areaLabelPic) {
		this.areaLabelPic = areaLabelPic;
	}
}