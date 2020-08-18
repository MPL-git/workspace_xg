package com.jf.entity;

import java.math.BigDecimal;

public class ProductCustom extends Product{
	private String pic;
	private String productBrandName;
	private String productTypeName;
	private String activityStatusDesc;
	private String mchtName;
	private String companyName;
	private String shopName;
	private String mchtCode;
	private String mchtType;
	private String statusDesc;
	private String offReasonDesc;
	private String seasonDesc;
	private String priceModel;
	private String priceModelDesc;
	private String auditStatusDesc;
	private String saleTypeDesc;
	private BigDecimal tagPriceMin;
	private BigDecimal tagPriceMax;
	private BigDecimal salePriceMin;
	private BigDecimal salePriceMax;
	private BigDecimal mallPriceMin;
	private BigDecimal mallPriceMax;
	private BigDecimal costPriceMin;
	private BigDecimal costPriceMax;
	private BigDecimal popCommissionRate;
	//折扣
	private BigDecimal discountMax;
	private BigDecimal discountMin;
	//毛利率
	private BigDecimal profitRateMax;
	private BigDecimal profitRateMin;
	
	private String productActivityStatus;
	private String auditByName;
	private Integer stock;
	
	private String productType1Name;
	private String productType2Name;
	private String supportQuantity;
	private String acType;
	//库存
	private Integer stockSum;
	//商品对应店铺的商管运营人员
	private String yyContactName;

	//商品流量报表数据
	private Integer memberVisitorsNum;//会员访客数
	private Integer unmemberVisitorsNum;//非会员访客数
	private Integer memberPageView;//会员浏览数
	private Integer unmemberPageView;//非会员访客数
	private Integer addProductAmount;//加购数
	private Integer submitOrderAmount;//提交订单数
	private Integer paymentAmount;//付款件数
	private String addProductRate;//加购转化
	private String submitOrderRate;//下单转化
	private String paymentRate;//付款转化
	private BigDecimal payMoney;//销售金额
	 
	 public String getYyContactName() {
		return yyContactName;
	}
	public void setYyContactName(String yyContactName) {
		this.yyContactName = yyContactName;
	}
	public String getAcType() {
	  return acType;
	 }
	public void setAcType(String acType) {
	  this.acType = acType;
	 }
	
	
	public Integer getStockSum() {
		return stockSum;
	}
	public void setStockSum(Integer stockSum) {
		this.stockSum = stockSum;
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
	public String getStatusDesc() {
		return statusDesc;
	}
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	public String getOffReasonDesc() {
		return offReasonDesc;
	}
	public void setOffReasonDesc(String offReasonDesc) {
		this.offReasonDesc = offReasonDesc;
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
	public String getSaleTypeDesc() {
		return saleTypeDesc;
	}
	public void setSaleTypeDesc(String saleTypeDesc) {
		this.saleTypeDesc = saleTypeDesc;
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
	public String getProductActivityStatus() {
		return productActivityStatus;
	}
	public void setProductActivityStatus(String productActivityStatus) {
		this.productActivityStatus = productActivityStatus;
	}
	public String getAuditByName() {
		return auditByName;
	}
	public void setAuditByName(String auditByName) {
		this.auditByName = auditByName;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
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
	public String getMchtCode() {
		return mchtCode;
	}
	public void setMchtCode(String mchtCode) {
		this.mchtCode = mchtCode;
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
	public String getSupportQuantity() {
		return supportQuantity;
	}
	public void setSupportQuantity(String supportQuantity) {
		this.supportQuantity = supportQuantity;
	}

	public Integer getMemberVisitorsNum() {
		return memberVisitorsNum;
	}

	public void setMemberVisitorsNum(Integer memberVisitorsNum) {
		this.memberVisitorsNum = memberVisitorsNum;
	}

	public Integer getUnmemberVisitorsNum() {
		return unmemberVisitorsNum;
	}

	public void setUnmemberVisitorsNum(Integer unmemberVisitorsNum) {
		this.unmemberVisitorsNum = unmemberVisitorsNum;
	}

	public Integer getMemberPageView() {
		return memberPageView;
	}

	public void setMemberPageView(Integer memberPageView) {
		this.memberPageView = memberPageView;
	}

	public Integer getUnmemberPageView() {
		return unmemberPageView;
	}

	public void setUnmemberPageView(Integer unmemberPageView) {
		this.unmemberPageView = unmemberPageView;
	}

	public Integer getAddProductAmount() {
		return addProductAmount;
	}

	public void setAddProductAmount(Integer addProductAmount) {
		this.addProductAmount = addProductAmount;
	}

	public Integer getSubmitOrderAmount() {
		return submitOrderAmount;
	}

	public void setSubmitOrderAmount(Integer submitOrderAmount) {
		this.submitOrderAmount = submitOrderAmount;
	}

	public Integer getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(Integer paymentAmount) {
		this.paymentAmount = paymentAmount;
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


	public BigDecimal getPayMoney() {
		return payMoney;
	}

	public void setPayMoney(BigDecimal payMoney) {
		this.payMoney = payMoney;
	}
}
