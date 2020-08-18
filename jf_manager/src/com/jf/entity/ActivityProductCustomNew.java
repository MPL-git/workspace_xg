package com.jf.entity;

import java.math.BigDecimal;

public class ActivityProductCustomNew extends ActivityProduct {

	private String productCode; // 商品编号
	private String productPic; // 商品主图
	private String productName; // 商品名称
	private String productBrandName; // 商品品牌
	private String productArtNo; // 商品货号
	private BigDecimal salePriceMin; // 商品最小活动价
	private BigDecimal salePriceMax; // 商品最大活动价
	private BigDecimal discountMin; // 商品最小折扣
	private BigDecimal discountMax; // 商品最大折扣
	private Integer productStock; // 商品库存
	private String operateAuditStatusDesc; // 专员审核状态
	private String cooAuditStatusDesc; // 排期审核状态
	private String lawAuditStatusDesc; // 法务审核状态
	private String operateAuditName; // 专员审核人名称
	private String cooAuditName; // 排期审核人名称
	private String productAuditStatus; // 商品表的法务审核状态
	private String productAuditStatusDesc; // 商品表的法务审核状态
	private String averageSalePrice; // 最低醒购价
	private BigDecimal activityPriceMin; // 商家最低活动价
	private BigDecimal deposit; //
	private BigDecimal deductAmount; //
	private BigDecimal productSvipDiscount; // 商品SVIP折扣
	private BigDecimal favorableRate; // 商品好评率
	private Integer salesVolume; // 商品销量
	private BigDecimal mallPriceMin;//最低商城价	
	private BigDecimal mallPriceMax;//最高商城价
	private BigDecimal salePrice;//商品实时活动价
	private String mchtType;//商家类型
	private BigDecimal costPrice;//SPOP价
	private BigDecimal costPriceMin;//最低结算价
	private BigDecimal costPriceMax;//最高结算价
	private BigDecimal tagPriceMax;//最高吊牌价
	private BigDecimal tagPriceMin;//最低吊牌价
	private BigDecimal svipDiscount;//svip折扣价
	private BigDecimal popCommissionRate;//pop佣金
	//用于前端展示毛利和毛利率
	private BigDecimal grossProfitMax;
	private BigDecimal grossProfitMin;
	private BigDecimal grossProfitRateMax;
	private BigDecimal grossProfitRateMin;

	public BigDecimal getGrossProfitMax() {
		return grossProfitMax;
	}

	public void setGrossProfitMax(BigDecimal grossProfitMax) {
		this.grossProfitMax = grossProfitMax;
	}

	public BigDecimal getGrossProfitMin() {
		return grossProfitMin;
	}

	public void setGrossProfitMin(BigDecimal grossProfitMin) {
		this.grossProfitMin = grossProfitMin;
	}

	public BigDecimal getGrossProfitRateMax() {
		return grossProfitRateMax;
	}

	public void setGrossProfitRateMax(BigDecimal grossProfitRateMax) {
		this.grossProfitRateMax = grossProfitRateMax;
	}

	public BigDecimal getGrossProfitRateMin() {
		return grossProfitRateMin;
	}

	public void setGrossProfitRateMin(BigDecimal grossProfitRateMin) {
		this.grossProfitRateMin = grossProfitRateMin;
	}

	public BigDecimal getTagPriceMax() {
		return tagPriceMax;
	}

	public void setTagPriceMax(BigDecimal tagPriceMax) {
		this.tagPriceMax = tagPriceMax;
	}

	public BigDecimal getTagPriceMin() {
		return tagPriceMin;
	}

	public void setTagPriceMin(BigDecimal tagPriceMin) {
		this.tagPriceMin = tagPriceMin;
	}

	public BigDecimal getSvipDiscount() {
		return svipDiscount;
	}

	public void setSvipDiscount(BigDecimal svipDiscount) {
		this.svipDiscount = svipDiscount;
	}

	public BigDecimal getPopCommissionRate() {
		return popCommissionRate;
	}

	public void setPopCommissionRate(BigDecimal popCommissionRate) {
		this.popCommissionRate = popCommissionRate;
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

	public BigDecimal getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}

	public BigDecimal getFavorableRate() {
		return favorableRate;
	}

	public void setFavorableRate(BigDecimal favorableRate) {
		this.favorableRate = favorableRate;
	}

	public Integer getSalesVolume() {
		return salesVolume;
	}

	public void setSalesVolume(Integer salesVolume) {
		this.salesVolume = salesVolume;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
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

	public String getProductBrandName() {
		return productBrandName;
	}

	public void setProductBrandName(String productBrandName) {
		this.productBrandName = productBrandName;
	}

	public String getProductArtNo() {
		return productArtNo;
	}

	public void setProductArtNo(String productArtNo) {
		this.productArtNo = productArtNo;
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

	public BigDecimal getDiscountMin() {
		return discountMin;
	}

	public void setDiscountMin(BigDecimal discountMin) {
		this.discountMin = discountMin;
	}

	public BigDecimal getDiscountMax() {
		return discountMax;
	}

	public void setDiscountMax(BigDecimal discountMax) {
		this.discountMax = discountMax;
	}

	public Integer getProductStock() {
		return productStock;
	}

	public void setProductStock(Integer productStock) {
		this.productStock = productStock;
	}

	public String getOperateAuditStatusDesc() {
		return operateAuditStatusDesc;
	}

	public void setOperateAuditStatusDesc(String operateAuditStatusDesc) {
		this.operateAuditStatusDesc = operateAuditStatusDesc;
	}

	public String getCooAuditStatusDesc() {
		return cooAuditStatusDesc;
	}

	public void setCooAuditStatusDesc(String cooAuditStatusDesc) {
		this.cooAuditStatusDesc = cooAuditStatusDesc;
	}

	public String getLawAuditStatusDesc() {
		return lawAuditStatusDesc;
	}

	public void setLawAuditStatusDesc(String lawAuditStatusDesc) {
		this.lawAuditStatusDesc = lawAuditStatusDesc;
	}

	public String getOperateAuditName() {
		return operateAuditName;
	}

	public void setOperateAuditName(String operateAuditName) {
		this.operateAuditName = operateAuditName;
	}

	public String getCooAuditName() {
		return cooAuditName;
	}

	public void setCooAuditName(String cooAuditName) {
		this.cooAuditName = cooAuditName;
	}

	public String getProductAuditStatus() {
		return productAuditStatus;
	}

	public void setProductAuditStatus(String productAuditStatus) {
		this.productAuditStatus = productAuditStatus;
	}

	public String getProductAuditStatusDesc() {
		return productAuditStatusDesc;
	}

	public void setProductAuditStatusDesc(String productAuditStatusDesc) {
		this.productAuditStatusDesc = productAuditStatusDesc;
	}

	public String getAverageSalePrice() {
		return averageSalePrice;
	}

	public void setAverageSalePrice(String averageSalePrice) {
		this.averageSalePrice = averageSalePrice;
	}

	public BigDecimal getActivityPriceMin() {
		return activityPriceMin;
	}

	public void setActivityPriceMin(BigDecimal activityPriceMin) {
		this.activityPriceMin = activityPriceMin;
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

	public BigDecimal getProductSvipDiscount() {
		return productSvipDiscount;
	}

	public void setProductSvipDiscount(BigDecimal productSvipDiscount) {
		this.productSvipDiscount = productSvipDiscount;
	}

	public String getMchtType() {
		return mchtType;
	}

	public void setMchtType(String mchtType) {
		this.mchtType = mchtType;
	}

	public BigDecimal getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(BigDecimal costPrice) {
		this.costPrice = costPrice;
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

}
