package com.jf.entity;

import java.math.BigDecimal;
import java.util.ArrayList;


/**
 * 
 * @ClassName SingleProductActivityCustom
 * @Description TODO(单品活动表Custom)
 * @author pengl
 * @date 2017年9月30日 下午3:49:54
 */
public class SingleProductActivityCustom extends SingleProductActivity {

	private String typeDesc;
	private String productPic;// 商品主图
	private String brandName;// 品牌名称
	private String artNo;// 货号
	private String shopName;// 店铺名
	private String mchtType;// 商家类型
	private String productName;// 商品名称
	private Integer stockSum;// 最新库存数
	private String firstAuditName;// 初审专员名称
	private String scheduleAuditName;// 排期专员名称
	private String againAuditName;// 复审专员名称
	private String quantitySum;// 销量
	private String code;// 商品编号（前端用商品id）
	private BigDecimal tagPriceMin;// 吊牌价（最小）
	private BigDecimal tagPriceMax;// 吊牌价（最大）
	private BigDecimal costPriceMin;// 结算价（最小）
	private BigDecimal costPriceMax;// 结算价（最大）
	private BigDecimal salePriceMin;// 活动价（最小）
	private BigDecimal salePriceMax;// 活动价（最大）
	private String discount;// 折扣
	private String averageSalePrice; // 最低醒购价
	private String cutPriceCnfCreateName; // 砍价设置人员
	private String remarksLog; // 原因说明
	private String customFlag; // 自定义状态
	private Integer superCutPriceOrderDtlCount; // 邀请人数
	private Integer cjCutPriceOrderCount; // 实际砍价人数
	private Integer yqCutPriceOrderCount; // 实际邀请人数
	private BigDecimal activityPriceMin; // 商家最低活动价
	private String mchtGradeDesc; // 商家等级
	private Double originalPriceBegin;// 报名搜索开始价格
	private Double originalPriceEnd;// 报名搜索结束价格
	private Integer maxInviteTimes;// 助力减价上线人数
	private Integer fixedAmount;// 助力减价助力一人立减
	private Integer singleProductSum;// 助力减价商品购买数量
	private Integer singleProductCount;// 助力减价商品成交笔数
	private Integer activeTime;// 活动时长（以小时为单位）
	private BigDecimal productLogMinSalePrice; // 近90天内活动价格的最低值（不包含当前活动价格）
	private Integer totalQuantitySum;// 往期销量
	private String goodCommentRate;// 好评率
	private BigDecimal productlogminsalePrice;//助力减价商品审核取同商家同品牌同货号商品中近三个月活动价格的最低值(不包含当前活动价格)
	private ArrayList<SingleProductActivityCustom> productQuantitySums; //商品历史销量
	private BigDecimal totalPayAmount; //商品实付金额
	private BigDecimal mallPriceMin;
	private BigDecimal mallPriceMax;
	private BigDecimal popCommissionRate; //pop佣金
	private BigDecimal svipDiscount;
	//用于前端展示毛利和毛利率
	private BigDecimal grossProfitMax;
	private BigDecimal grossProfitMin;
	private BigDecimal grossProfitRateMax;
	private BigDecimal grossProfitRateMin;
	private String  isManageSelf; //是否自营

	public String getIsManageSelf() {
		return isManageSelf;
	}

	public void setIsManageSelf(String isManageSelf) {
		this.isManageSelf = isManageSelf;
	}

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

	public BigDecimal getSvipDiscount() {
		return svipDiscount;
	}

	public void setSvipDiscount(BigDecimal svipDiscount) {
		this.svipDiscount = svipDiscount;
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

	public BigDecimal getPopCommissionRate() {
		return popCommissionRate;
	}

	public void setPopCommissionRate(BigDecimal popCommissionRate) {
		this.popCommissionRate = popCommissionRate;
	}



	public BigDecimal getTotalPayAmount() {
		return totalPayAmount;
	}

	public void setTotalPayAmount(BigDecimal totalPayAmount) {
		this.totalPayAmount = totalPayAmount;
	}


	public SingleProductActivityCustom() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ArrayList<SingleProductActivityCustom> getProductQuantitySums() {
		return productQuantitySums;
	}

	public void setProductQuantitySums(
			ArrayList<SingleProductActivityCustom> productQuantitySums) {
		this.productQuantitySums = productQuantitySums;
	}

	public String getTypeDesc() {
		return typeDesc;
	}

	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}

	public String getFirstAuditName() {
		return firstAuditName;
	}

	public void setFirstAuditName(String firstAuditName) {
		this.firstAuditName = firstAuditName;
	}

	public String getScheduleAuditName() {
		return scheduleAuditName;
	}

	public void setScheduleAuditName(String scheduleAuditName) {
		this.scheduleAuditName = scheduleAuditName;
	}

	public String getAgainAuditName() {
		return againAuditName;
	}

	public void setAgainAuditName(String againAuditName) {
		this.againAuditName = againAuditName;
	}

	public String getProductPic() {
		return productPic;
	}

	public void setProductPic(String productPic) {
		this.productPic = productPic;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getArtNo() {
		return artNo;
	}

	public void setArtNo(String artNo) {
		this.artNo = artNo;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getStockSum() {
		return stockSum;
	}

	public void setStockSum(Integer stockSum) {
		this.stockSum = stockSum;
	}

	public String getQuantitySum() {
		return quantitySum;
	}

	public void setQuantitySum(String quantitySum) {
		this.quantitySum = quantitySum;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public BigDecimal getTagPriceMin() {
		return tagPriceMin;
	}

	public void setTagPriceMin(BigDecimal tagPriceMin) {
		this.tagPriceMin = tagPriceMin;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getAverageSalePrice() {
		return averageSalePrice;
	}

	public void setAverageSalePrice(String averageSalePrice) {
		this.averageSalePrice = averageSalePrice;
	}

	public String getCutPriceCnfCreateName() {
		return cutPriceCnfCreateName;
	}

	public void setCutPriceCnfCreateName(String cutPriceCnfCreateName) {
		this.cutPriceCnfCreateName = cutPriceCnfCreateName;
	}

	public BigDecimal getTagPriceMax() {
		return tagPriceMax;
	}

	public void setTagPriceMax(BigDecimal tagPriceMax) {
		this.tagPriceMax = tagPriceMax;
	}

	public String getRemarksLog() {
		return remarksLog;
	}

	public void setRemarksLog(String remarksLog) {
		this.remarksLog = remarksLog;
	}

	public String getCustomFlag() {
		return customFlag;
	}

	public void setCustomFlag(String customFlag) {
		this.customFlag = customFlag;
	}

	public Integer getSuperCutPriceOrderDtlCount() {
		return superCutPriceOrderDtlCount;
	}

	public void setSuperCutPriceOrderDtlCount(Integer superCutPriceOrderDtlCount) {
		this.superCutPriceOrderDtlCount = superCutPriceOrderDtlCount;
	}

	public BigDecimal getActivityPriceMin() {
		return activityPriceMin;
	}

	public void setActivityPriceMin(BigDecimal activityPriceMin) {
		this.activityPriceMin = activityPriceMin;
	}

	public Integer getCjCutPriceOrderCount() {
		return cjCutPriceOrderCount;
	}

	public void setCjCutPriceOrderCount(Integer cjCutPriceOrderCount) {
		this.cjCutPriceOrderCount = cjCutPriceOrderCount;
	}

	public Integer getYqCutPriceOrderCount() {
		return yqCutPriceOrderCount;
	}

	public void setYqCutPriceOrderCount(Integer yqCutPriceOrderCount) {
		this.yqCutPriceOrderCount = yqCutPriceOrderCount;
	}

	public String getMchtGradeDesc() {
		return mchtGradeDesc;
	}

	public void setMchtGradeDesc(String mchtGradeDesc) {
		this.mchtGradeDesc = mchtGradeDesc;
	}

	public Double getOriginalPriceBegin() {
		return originalPriceBegin;
	}

	public void setOriginalPriceBegin(Double originalPriceBegin) {
		this.originalPriceBegin = originalPriceBegin;
	}

	public Double getOriginalPriceEnd() {
		return originalPriceEnd;
	}

	public void setOriginalPriceEnd(Double originalPriceEnd) {
		this.originalPriceEnd = originalPriceEnd;
	}

	public Integer getMaxInviteTimes() {
		return maxInviteTimes;
	}

	public void setMaxInviteTimes(Integer maxInviteTimes) {
		this.maxInviteTimes = maxInviteTimes;
	}

	public Integer getFixedAmount() {
		return fixedAmount;
	}

	public void setFixedAmount(Integer fixedAmount) {
		this.fixedAmount = fixedAmount;
	}

	public Integer getSingleProductSum() {
		return singleProductSum;
	}

	public void setSingleProductSum(Integer singleProductSum) {
		this.singleProductSum = singleProductSum;
	}

	public Integer getSingleProductCount() {
		return singleProductCount;
	}

	public void setSingleProductCount(Integer singleProductCount) {
		this.singleProductCount = singleProductCount;
	}

	public Integer getActiveTime() {
		return activeTime;
	}

	public void setActiveTime(Integer activeTime) {
		this.activeTime = activeTime;
	}

	public BigDecimal getProductLogMinSalePrice() {
		return productLogMinSalePrice;
	}

	public void setProductLogMinSalePrice(BigDecimal productLogMinSalePrice) {
		this.productLogMinSalePrice = productLogMinSalePrice;
	}

	public Integer getTotalQuantitySum() {
		return totalQuantitySum;
	}

	public void setTotalQuantitySum(Integer totalQuantitySum) {
		this.totalQuantitySum = totalQuantitySum;
	}

	public String getGoodCommentRate() {
		return goodCommentRate;
	}

	public void setGoodCommentRate(String goodCommentRate) {
		this.goodCommentRate = goodCommentRate;
	}

	public BigDecimal getProductlogminsalePrice() {
		return productlogminsalePrice;
	}

	public void setProductlogminsalePrice(BigDecimal productlogminsalePrice) {
		this.productlogminsalePrice = productlogminsalePrice;
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

	public String getMchtType() {
		return mchtType;
	}

	public void setMchtType(String mchtType) {
		this.mchtType = mchtType;
	}
}