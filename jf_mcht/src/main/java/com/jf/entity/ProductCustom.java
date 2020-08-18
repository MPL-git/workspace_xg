package com.jf.entity;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;

public class ProductCustom extends Product{
	private String pic;
	private String productBrandName;
	private String firstProductTypeName;
	private String secondProductTypeName;
	private String productTypeName;
	private String activityStatusDesc;
	private String mchtName;
	private String mchtType;
	private String seasonDesc;
	private String offReasonDesc;
	private String priceModel;
	private String priceModelDesc;
	private BigDecimal tagPriceMin;
	private BigDecimal tagPriceMax;
	private BigDecimal salePriceMin;
	private BigDecimal mallPriceMin;
	private BigDecimal salePriceMax;
	private BigDecimal mallPriceMax;
	private BigDecimal costPriceMin;
	private BigDecimal costPriceMax;
	private BigDecimal popCommissionRate;
	
	//折扣
	private BigDecimal discountMax;
	private BigDecimal discountMin;
	
	private BigDecimal discountMaxTruncate;
	private BigDecimal discountMinTruncate;
	
	//毛利率
	private BigDecimal profitRateMax;
	private BigDecimal profitRateMin;
	
	
	
	private String auditStatusDesc;
	
	private Integer seqNo;
	private Integer activityProductId;
	private Integer stock;
	private String customTypes;
	private String skus;
	private String productPropValues;
	private String templetName;
	private String freightTempletName;
	
	private boolean canDelete;

	private Integer goodCommentCount;//好评数
	private Integer totalCommentCount;//总评价数
	private String goodCommentRate;//好评率
	private String mallPrice;
	private String tagPrice;
	private Integer totalSalesVolume;

	//sku
	private List<ProductItemCustom> productItemList;

	//尺码
	private HashSet<ProductPropValue> sizeValueList;
	//颜色
	private HashSet<ProductPropValue> colorValueList;

	public HashSet<ProductPropValue> getSizeValueList() {
		return sizeValueList;
	}

	public void setSizeValueList(HashSet<ProductPropValue> sizeValueList) {
		this.sizeValueList = sizeValueList;
	}

	public HashSet<ProductPropValue> getColorValueList() {
		return colorValueList;
	}

	public void setColorValueList(HashSet<ProductPropValue> colorValueList) {
		this.colorValueList = colorValueList;
	}

	public List<ProductItemCustom> getProductItemList() {
		return productItemList;
	}

	public void setProductItemList(List<ProductItemCustom> productItemList) {
		this.productItemList = productItemList;
	}

	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getProductBrandName() {
		return productBrandName;
	}
	public void setProductBrandName(String productBrandName) {
		this.productBrandName = productBrandName;
	}
	public String getProductTypeName() {
		return productTypeName;
	}
	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}
	public String getActivityStatusDesc() {
		return activityStatusDesc;
	}
	public void setActivityStatusDesc(String activityStatusDesc) {
		this.activityStatusDesc = activityStatusDesc;
	}
	public String getMchtName() {
		return mchtName;
	}
	public void setMchtName(String mchtName) {
		this.mchtName = mchtName;
	}
	public String getSeasonDesc() {
		return seasonDesc;
	}
	public void setSeasonDesc(String seasonDesc) {
		this.seasonDesc = seasonDesc;
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
	public BigDecimal getCostPriceMin() {
		return costPriceMin;
	}
	public void setCostPriceMin(BigDecimal costPriceMin) {
		this.costPriceMin = costPriceMin;
	}
	public BigDecimal getCostPriceMax() {
		return costPriceMax;
	}
	public void setCostPriceMax(BigDecimal costPriceMax) {
		this.costPriceMax = costPriceMax;
	}
	public String getMchtType() {
		return mchtType;
	}
	public void setMchtType(String mchtType) {
		this.mchtType = mchtType;
	}
	public BigDecimal getPopCommissionRate() {
		return popCommissionRate;
	}
	public void setPopCommissionRate(BigDecimal popCommissionRate) {
		this.popCommissionRate = popCommissionRate;
	}
	public String getPriceModel() {
		return priceModel;
	}
	public void setPriceModel(String priceModel) {
		this.priceModel = priceModel;
	}
	public String getAuditStatusDesc() {
		return auditStatusDesc;
	}
	public void setAuditStatusDesc(String auditStatusDesc) {
		this.auditStatusDesc = auditStatusDesc;
	}
	public BigDecimal getDiscountMax() {
		return discountMax;
	}
	public void setDiscountMax(BigDecimal discountMax) {
		this.discountMax = discountMax;
	}
	public BigDecimal getDiscountMin() {
		return discountMin;
	}
	public void setDiscountMin(BigDecimal discountMin) {
		this.discountMin = discountMin;
	}
	public BigDecimal getProfitRateMax() {
		return profitRateMax;
	}
	public void setProfitRateMax(BigDecimal profitRateMax) {
		this.profitRateMax = profitRateMax;
	}
	public BigDecimal getProfitRateMin() {
		return profitRateMin;
	}
	public void setProfitRateMin(BigDecimal profitRateMin) {
		this.profitRateMin = profitRateMin;
	}
	public String getPriceModelDesc() {
		return priceModelDesc;
	}
	public void setPriceModelDesc(String priceModelDesc) {
		this.priceModelDesc = priceModelDesc;
	}
	public String getOffReasonDesc() {
		return offReasonDesc;
	}
	public void setOffReasonDesc(String offReasonDesc) {
		this.offReasonDesc = offReasonDesc;
	}
	public Integer getSeqNo() {
		return seqNo;
	}
	public void setSeqNo(Integer seqNo) {
		this.seqNo = seqNo;
	}
	public Integer getActivityProductId() {
		return activityProductId;
	}
	public void setActivityProductId(Integer activityProductId) {
		this.activityProductId = activityProductId;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public String getCustomTypes() {
		return customTypes;
	}
	public void setCustomTypes(String customTypes) {
		this.customTypes = customTypes;
	}
	public BigDecimal getMallPriceMin() {
		return mallPriceMin;
	}
	public void setMallPriceMin(BigDecimal mallPriceMin) {
		this.mallPriceMin = mallPriceMin;
	}
	public BigDecimal getMallPriceMax() {
		return mallPriceMax;
	}
	public void setMallPriceMax(BigDecimal mallPriceMax) {
		this.mallPriceMax = mallPriceMax;
	}
	public String getFirstProductTypeName() {
		return firstProductTypeName;
	}
	public void setFirstProductTypeName(String firstProductTypeName) {
		this.firstProductTypeName = firstProductTypeName;
	}
	public String getSecondProductTypeName() {
		return secondProductTypeName;
	}
	public void setSecondProductTypeName(String secondProductTypeName) {
		this.secondProductTypeName = secondProductTypeName;
	}
	public String getSkus() {
		return skus;
	}
	public void setSkus(String skus) {
		this.skus = skus;
	}
	public String getProductPropValues() {
		return productPropValues;
	}
	public void setProductPropValues(String productPropValues) {
		this.productPropValues = productPropValues;
	}
	public String getTempletName() {
		return templetName;
	}
	public void setTempletName(String templetName) {
		this.templetName = templetName;
	}
	public boolean isCanDelete() {
		return canDelete;
	}
	public void setCanDelete(boolean canDelete) {
		this.canDelete = canDelete;
	}
	public String getFreightTempletName() {
		return freightTempletName;
	}
	public void setFreightTempletName(String freightTempletName) {
		this.freightTempletName = freightTempletName;
	}
	public BigDecimal getDiscountMaxTruncate() {
		return discountMaxTruncate;
	}
	public void setDiscountMaxTruncate(BigDecimal discountMaxTruncate) {
		this.discountMaxTruncate = discountMaxTruncate;
	}
	public BigDecimal getDiscountMinTruncate() {
		return discountMinTruncate;
	}
	public void setDiscountMinTruncate(BigDecimal discountMinTruncate) {
		this.discountMinTruncate = discountMinTruncate;
	}

	public Integer getGoodCommentCount() {
		return goodCommentCount;
	}

	public void setGoodCommentCount(Integer goodCommentCount) {
		this.goodCommentCount = goodCommentCount;
	}

	public Integer getTotalCommentCount() {
		return totalCommentCount;
	}

	public void setTotalCommentCount(Integer totalCommentCount) {
		this.totalCommentCount = totalCommentCount;
	}

	public String getGoodCommentRate() {
		return goodCommentRate;
	}

	public void setGoodCommentRate(String goodCommentRate) {
		this.goodCommentRate = goodCommentRate;
	}

	public String getMallPrice() {
		return mallPrice;
	}

	public void setMallPrice(String mallPrice) {
		this.mallPrice = mallPrice;
	}

	public String getTagPrice() {
		return tagPrice;
	}

	public void setTagPrice(String tagPrice) {
		this.tagPrice = tagPrice;
	}
	public Integer getTotalSalesVolume() {
		return totalSalesVolume;
	}
	public void setTotalSalesVolume(Integer totalSalesVolume) {
		this.totalSalesVolume = totalSalesVolume;
	}
	
}
