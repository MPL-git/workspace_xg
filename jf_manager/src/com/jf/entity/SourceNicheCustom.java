package com.jf.entity;

import java.math.BigDecimal;
import java.util.Date;


public class SourceNicheCustom extends SourceNiche{
	private BigDecimal tagPriceMin;
	private BigDecimal tagPriceMax;//吊牌价
	private BigDecimal salePriceMin;//活动价格
	private BigDecimal salePriceMax;
	private BigDecimal mallPriceMin;
	private BigDecimal mallPriceMax;
	private String pic;//主图
	private String productCode; //商品id
	private String artbrandGroup;//所属品牌
	private String artbrandGroupEasy;
	private String productName;//商品名称
	private String producttype1Name;
	private String producttype2Name;
	private String producttype3Name;//所属品类
	private String suitSex;
	private String suitGroup;
	private Date cooperateBeginDate;
	private Date agreementEndDate;
	private String companyName;
	private String mchtType;
	private String mchtProductbrandName;
	private String shopName;
	private String pShopName;
	private String mchtCode;
	private String mchtStatusDesc;
	private String degreeAuditriskDesc;
	private String gradeDesc;
	private String productTypeName;
	private String degreeAdaptabilityDesc;
	private String shopStatus;
	private String  productStatusDesc;
	private String shopStatusDesc;
	private String recommendRemarks;//推荐文案
	private Date productUpdateDate;
	private Date productCreateDate;
	private String proutArtno;//货号
	private String auditName;//审核人

	private String avgProductScore;//商品得分
	private String avgMchtScore;//卖家服务分
	private String avgWuliuScore;//物流服务分
	//折扣
	private BigDecimal discountMax;
	private BigDecimal discountMin;
	private BigDecimal svipDiscount;//svip折扣
	private String goodCommentRate;//好评率
	private Integer stockSum;//最新库存
	private Integer totalQuantitySum;//近三个月销量
	private String productlogminsalePrice;//近三个月最低活动价
	
	private String bannerStatus;//banner轮播图的上下线状态
	
	private Integer thiryQuantitySum;//三十天的销量
	
	private Integer  countProduct;//商家商品数
	private BigDecimal  sub_order_count_30_days;//30天订单量
	private String return_rate_30_days;//30天的退货率
	private BigDecimal  pay_amount_sum_30_days;//30天销售额

	//数据统计
	private Integer memberVisitorsAmount;//会员访客数
	private Integer unmemberVisitorsAmount;//非会员访客数
	private Integer memberPageView;//会员浏览数
	private Integer unmemberPageView;//非会员访客数
	private Integer addProductAmount;//加购数
	private Integer submitOrderAmount;//提交订单数
	private Integer paymentAmount;//付款件数
	private BigDecimal addProductRate;//加购转化
	private BigDecimal submitOrderRate;//下单转化
	private BigDecimal paymentRate;//付款转化
	private Integer lotteryCount;//抽中数量


	public String getpShopName() {
		return pShopName;
	}
	public void setpShopName(String pShopName) {
		this.pShopName = pShopName;
	}

	public Integer getCountProduct() {
		return countProduct;
	}
	public void setCountProduct(Integer countProduct) {
		this.countProduct = countProduct;
	}
	public BigDecimal getSub_order_count_30_days() {
		return sub_order_count_30_days;
	}
	public void setSub_order_count_30_days(BigDecimal sub_order_count_30_days) {
		this.sub_order_count_30_days = sub_order_count_30_days;
	}
	public String getReturn_rate_30_days() {
		return return_rate_30_days;
	}
	public void setReturn_rate_30_days(String return_rate_30_days) {
		this.return_rate_30_days = return_rate_30_days;
	}
	public BigDecimal getPay_amount_sum_30_days() {
		return pay_amount_sum_30_days;
	}
	public void setPay_amount_sum_30_days(BigDecimal pay_amount_sum_30_days) {
		this.pay_amount_sum_30_days = pay_amount_sum_30_days;
	}

	public String getArtbrandGroupEasy() {
		return artbrandGroupEasy;
	}
	public void setArtbrandGroupEasy(String artbrandGroupEasy) {
		this.artbrandGroupEasy = artbrandGroupEasy;
	}
	public Integer getThiryQuantitySum() {
		return thiryQuantitySum;
	}
	public void setThiryQuantitySum(Integer thiryQuantitySum) {
		this.thiryQuantitySum = thiryQuantitySum;
	}
	public String getAuditName() {
		return auditName;
	}
	public void setAuditName(String auditName) {
		this.auditName = auditName;
	}
	
	public String getAvgProductScore() {
		return avgProductScore;
	}
	public void setAvgProductScore(String avgProductScore) {
		this.avgProductScore = avgProductScore;
	}
	public String getAvgMchtScore() {
		return avgMchtScore;
	}
	public void setAvgMchtScore(String avgMchtScore) {
		this.avgMchtScore = avgMchtScore;
	}
	public String getAvgWuliuScore() {
		return avgWuliuScore;
	}
	public void setAvgWuliuScore(String avgWuliuScore) {
		this.avgWuliuScore = avgWuliuScore;
	}
	public String getBannerStatus() {
		return bannerStatus;
	}
	public void setBannerStatus(String bannerStatus) {
		this.bannerStatus = bannerStatus;
	}
	public BigDecimal getSvipDiscount() {
		return svipDiscount;
	}
	public void setSvipDiscount(BigDecimal svipDiscount) {
		this.svipDiscount = svipDiscount;
	}
	public String getGoodCommentRate() {
		return goodCommentRate;
	}
	public void setGoodCommentRate(String goodCommentRate) {
		this.goodCommentRate = goodCommentRate;
	}
	public Integer getStockSum() {
		return stockSum;
	}
	public void setStockSum(Integer stockSum) {
		this.stockSum = stockSum;
	}
	public Integer getTotalQuantitySum() {
		return totalQuantitySum;
	}
	public void setTotalQuantitySum(Integer totalQuantitySum) {
		this.totalQuantitySum = totalQuantitySum;
	}
	public String getProductlogminsalePrice() {
		return productlogminsalePrice;
	}
	public void setProductlogminsalePrice(String productlogminsalePrice) {
		this.productlogminsalePrice = productlogminsalePrice;
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
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getArtbrandGroup() {
		return artbrandGroup;
	}
	public void setArtbrandGroup(String artbrandGroup) {
		this.artbrandGroup = artbrandGroup;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProducttype1Name() {
		return producttype1Name;
	}
	public void setProducttype1Name(String producttype1Name) {
		this.producttype1Name = producttype1Name;
	}
	public String getProducttype2Name() {
		return producttype2Name;
	}
	public void setProducttype2Name(String producttype2Name) {
		this.producttype2Name = producttype2Name;
	}
	public String getProducttype3Name() {
		return producttype3Name;
	}
	public void setProducttype3Name(String producttype3Name) {
		this.producttype3Name = producttype3Name;
	}
	public String getSuitSex() {
		return suitSex;
	}
	public void setSuitSex(String suitSex) {
		this.suitSex = suitSex;
	}
	public String getSuitGroup() {
		return suitGroup;
	}
	public void setSuitGroup(String suitGroup) {
		this.suitGroup = suitGroup;
	}
	public Date getCooperateBeginDate() {
		return cooperateBeginDate;
	}
	public void setCooperateBeginDate(Date cooperateBeginDate) {
		this.cooperateBeginDate = cooperateBeginDate;
	}
	public Date getAgreementEndDate() {
		return agreementEndDate;
	}
	public void setAgreementEndDate(Date agreementEndDate) {
		this.agreementEndDate = agreementEndDate;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getMchtType() {
		return mchtType;
	}
	public void setMchtType(String mchtType) {
		this.mchtType = mchtType;
	}
	public String getMchtProductbrandName() {
		return mchtProductbrandName;
	}
	public void setMchtProductbrandName(String mchtProductbrandName) {
		this.mchtProductbrandName = mchtProductbrandName;
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
	public String getMchtStatusDesc() {
		return mchtStatusDesc;
	}
	public void setMchtStatusDesc(String mchtStatusDesc) {
		this.mchtStatusDesc = mchtStatusDesc;
	}
	
	
	public String getDegreeAuditriskDesc() {
		return degreeAuditriskDesc;
	}
	public void setDegreeAuditriskDesc(String degreeAuditriskDesc) {
		this.degreeAuditriskDesc = degreeAuditriskDesc;
	}
	public String getGradeDesc() {
		return gradeDesc;
	}
	public void setGradeDesc(String gradeDesc) {
		this.gradeDesc = gradeDesc;
	}
	public String getProductTypeName() {
		return productTypeName;
	}
	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}
	public String getDegreeAdaptabilityDesc() {
		return degreeAdaptabilityDesc;
	}
	public void setDegreeAdaptabilityDesc(String degreeAdaptabilityDesc) {
		this.degreeAdaptabilityDesc = degreeAdaptabilityDesc;
	}
	public String getShopStatus() {
		return shopStatus;
	}
	public void setShopStatus(String shopStatus) {
		this.shopStatus = shopStatus;
	}
	public String getProductStatusDesc() {
		return productStatusDesc;
	}
	public void setProductStatusDesc(String productStatusDesc) {
		this.productStatusDesc = productStatusDesc;
	}
	public String getShopStatusDesc() {
		return shopStatusDesc;
	}
	public void setShopStatusDesc(String shopStatusDesc) {
		this.shopStatusDesc = shopStatusDesc;
	}
	public String getRecommendRemarks() {
		return recommendRemarks;
	}
	public void setRecommendRemarks(String recommendRemarks) {
		this.recommendRemarks = recommendRemarks;
	}
	public Date getProductUpdateDate() {
		return productUpdateDate;
	}
	public void setProductUpdateDate(Date productUpdateDate) {
		this.productUpdateDate = productUpdateDate;
	}
	public Date getProductCreateDate() {
		return productCreateDate;
	}
	public void setProductCreateDate(Date productCreateDate) {
		this.productCreateDate = productCreateDate;
	}
	public String getProutArtno() {
		return proutArtno;
	}
	public void setProutArtno(String proutArtno) {
		this.proutArtno = proutArtno;
	}

	public Integer getMemberVisitorsAmount() {
		return memberVisitorsAmount;
	}

	public void setMemberVisitorsAmount(Integer memberVisitorsAmount) {
		this.memberVisitorsAmount = memberVisitorsAmount;
	}

	public Integer getUnmemberVisitorsAmount() {
		return unmemberVisitorsAmount;
	}

	public void setUnmemberVisitorsAmount(Integer unmemberVisitorsAmount) {
		this.unmemberVisitorsAmount = unmemberVisitorsAmount;
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

	public BigDecimal getAddProductRate() {
		return addProductRate;
	}

	public void setAddProductRate(BigDecimal addProductRate) {
		this.addProductRate = addProductRate;
	}

	public BigDecimal getSubmitOrderRate() {
		return submitOrderRate;
	}

	public void setSubmitOrderRate(BigDecimal submitOrderRate) {
		this.submitOrderRate = submitOrderRate;
	}

	public BigDecimal getPaymentRate() {
		return paymentRate;
	}

	public void setPaymentRate(BigDecimal paymentRate) {
		this.paymentRate = paymentRate;
	}

	public Integer getLotteryCount() {
		return lotteryCount;
	}

	public void setLotteryCount(Integer lotteryCount) {
		this.lotteryCount = lotteryCount;
	}
}