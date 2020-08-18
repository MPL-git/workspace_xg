package com.jf.entity;

import java.math.BigDecimal;
import java.util.Date;

public class ActivityProductCustom extends ActivityProduct {
	
	private String productName;//商品名称
	
	private String productCode;//商品编号
	
	private String productArtNo;//商品货号
	
	private String productAuditStatus;//商品法务审核状态
	
	private String productPic;//商品主图
	
	private String cooAuditStatusDesc;//运营总监审核状态
	
	private String designAuditStatusDesc;//设计部审核状态
	
	private String lawAuditStatusDesc;//法务部审核状态
	
	private String operateAuditStatusDesc;//运营专员审核状态
	
	private String refuseFlagDesc;//是否驳回
	
	private Date productUpdateDate;//商品最新更新时间
	
	private BigDecimal activitySalePrice;//活动商品价格
	
	private BigDecimal minimumPrice;//历史商品最低价格
	
	private BigDecimal tagPrice;//商品价格(原价：sku最高的吊牌价)

	private Integer joinNum;//商品参加活动次数
	
	private Integer productStock;//商品库存
	
	private String productTypeName;//类目名称
	
	private String productBrandName;//品牌名称
	
	private String shortName;//商家名称
	
	private String mchtCode;//商家编号
	
	private String shopName;//店铺名称
	
	private Date mchtOoperateBeginDate;//商家入驻时间(商家合作开始时间)
	
	private String activityName;//活动名称
	
	private String activityAreaTypeDesc;//活动类型（专区列表里面的type）

	private String activityAreaName;//活动名称（专区列表里面的type）
	
	private Date submitTime;//提报时间（活动列表）
	
	private Date activityUpdateDate;//最新审核时间（活动列表）
	
	private String contactName;//商家对接人
	
	private String qq;//商家对接人(联系qq)
	
	private Date enrollBeginTime;//报名开始时间
	
	private Date enrollEndTime;//报名结束时间
	
	private Date activityBeginTime;//活动开始时间
	
	private Date activityEndTime;//活动结束

	private String activityAreaPic;//活动专区图标
	
	private Integer signleTypeTwoNum;//商品参加单品活动次数
	
	private Integer thisSalesNum;//本次单品活动销量

	private Integer thisProductQuantityNum;//本次活动商品销量
	
	private Integer totalSalesNum;//历史总销量
	
	private Integer dfgNum;//30天内销售量
	
	private BigDecimal dfgPrice;//30天内销售额
	
	private Integer joinSaleNum;//加入品牌特卖活动总数

	private Integer joinDfgSaleNum;//30天内加入品牌特卖活动总数
	
	private Integer signUpProductNum;//本次活动报名商品数量(一个商家)
	
	private Integer mchtSignNum;//商家参加单品次数
	
	private Integer thisProductAppealNum;//投诉次数（商品）
	
	private Integer thisMchtViolateNum;//违规次数（商家）
	
	private BigDecimal salePriceMin;
	
	private BigDecimal salePriceMax;
	
	private BigDecimal popCommissionRate;
	
	private BigDecimal discountMin;
	
	private BigDecimal discountMax;
	
	private String eachDay;
	private int shopdata;
	private int examinedata;
	private int saledata;
	private int singleproductdata;
		
	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getMchtCode() {
		return mchtCode;
	}

	public void setMchtCode(String mchtCode) {
		this.mchtCode = mchtCode;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getActivityAreaTypeDesc() {
		return activityAreaTypeDesc;
	}

	public void setActivityAreaTypeDesc(String activityAreaTypeDesc) {
		this.activityAreaTypeDesc = activityAreaTypeDesc;
	}

	public String getProductTypeName() {
		return productTypeName;
	}

	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}

	public String getProductBrandName() {
		return productBrandName;
	}

	public void setProductBrandName(String productBrandName) {
		this.productBrandName = productBrandName;
	}

	public BigDecimal getActivitySalePrice() {
		return activitySalePrice;
	}

	public void setActivitySalePrice(BigDecimal activitySalePrice) {
		this.activitySalePrice = activitySalePrice;
	}

	public BigDecimal getMinimumPrice() {
		return minimumPrice;
	}

	public void setMinimumPrice(BigDecimal minimumPrice) {
		this.minimumPrice = minimumPrice;
	}
	
	public BigDecimal getTagPrice() {
		return tagPrice;
	}

	public void setTagPrice(BigDecimal tagPrice) {
		this.tagPrice = tagPrice;
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

	public String getProductArtNo() {
		return productArtNo;
	}

	public void setProductArtNo(String productArtNo) {
		this.productArtNo = productArtNo;
	}
	
	public String getProductAuditStatus() {
		return productAuditStatus;
	}

	public void setProductAuditStatus(String productAuditStatus) {
		this.productAuditStatus = productAuditStatus;
	}

	public String getProductPic() {
		return productPic;
	}

	public void setProductPic(String productPic) {
		this.productPic = productPic;
	}

	public String getCooAuditStatusDesc() {
		return cooAuditStatusDesc;
	}

	public void setCooAuditStatusDesc(String cooAuditStatusDesc) {
		this.cooAuditStatusDesc = cooAuditStatusDesc;
	}

	public String getDesignAuditStatusDesc() {
		return designAuditStatusDesc;
	}

	public void setDesignAuditStatusDesc(String designAuditStatusDesc) {
		this.designAuditStatusDesc = designAuditStatusDesc;
	}

	public String getLawAuditStatusDesc() {
		return lawAuditStatusDesc;
	}

	public void setLawAuditStatusDesc(String lawAuditStatusDesc) {
		this.lawAuditStatusDesc = lawAuditStatusDesc;
	}

	public String getOperateAuditStatusDesc() {
		return operateAuditStatusDesc;
	}

	public void setOperateAuditStatusDesc(String operateAuditStatusDesc) {
		this.operateAuditStatusDesc = operateAuditStatusDesc;
	}

	public String getRefuseFlagDesc() {
		return refuseFlagDesc;
	}

	public void setRefuseFlagDesc(String refuseFlagDesc) {
		this.refuseFlagDesc = refuseFlagDesc;
	}

	public Date getProductUpdateDate() {
		return productUpdateDate;
	}

	public void setProductUpdateDate(Date productUpdateDate) {
		this.productUpdateDate = productUpdateDate;
	}

	public Integer getJoinNum() {
		return joinNum;
	}

	public void setJoinNum(Integer joinNum) {
		this.joinNum = joinNum;
	}

	public Integer getProductStock() {
		return productStock;
	}

	public void setProductStock(Integer productStock) {
		this.productStock = productStock;
	}

	public String getActivityAreaName() {
		return activityAreaName;
	}

	public void setActivityAreaName(String activityAreaName) {
		this.activityAreaName = activityAreaName;
	}

	public Date getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}

	public Date getActivityUpdateDate() {
		return activityUpdateDate;
	}

	public void setActivityUpdateDate(Date activityUpdateDate) {
		this.activityUpdateDate = activityUpdateDate;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public Date getEnrollBeginTime() {
		return enrollBeginTime;
	}

	public void setEnrollBeginTime(Date enrollBeginTime) {
		this.enrollBeginTime = enrollBeginTime;
	}

	public Date getEnrollEndTime() {
		return enrollEndTime;
	}

	public void setEnrollEndTime(Date enrollEndTime) {
		this.enrollEndTime = enrollEndTime;
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

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public Date getMchtOoperateBeginDate() {
		return mchtOoperateBeginDate;
	}

	public void setMchtOoperateBeginDate(Date mchtOoperateBeginDate) {
		this.mchtOoperateBeginDate = mchtOoperateBeginDate;
	}

	public String getActivityAreaPic() {
		return activityAreaPic;
	}

	public void setActivityAreaPic(String activityAreaPic) {
		this.activityAreaPic = activityAreaPic;
	}

	public Integer getSignleTypeTwoNum() {
		return signleTypeTwoNum;
	}

	public void setSignleTypeTwoNum(Integer signleTypeTwoNum) {
		this.signleTypeTwoNum = signleTypeTwoNum;
	}

	public Integer getThisSalesNum() {
		return thisSalesNum;
	}

	public void setThisSalesNum(Integer thisSalesNum) {
		this.thisSalesNum = thisSalesNum;
	}

	public Integer getTotalSalesNum() {
		return totalSalesNum;
	}

	public void setTotalSalesNum(Integer totalSalesNum) {
		this.totalSalesNum = totalSalesNum;
	}

	public Integer getDfgNum() {
		return dfgNum;
	}

	public void setDfgNum(Integer dfgNum) {
		this.dfgNum = dfgNum;
	}

	public BigDecimal getDfgPrice() {
		return dfgPrice;
	}

	public void setDfgPrice(BigDecimal dfgPrice) {
		this.dfgPrice = dfgPrice;
	}

	public Integer getJoinSaleNum() {
		return joinSaleNum;
	}

	public void setJoinSaleNum(Integer joinSaleNum) {
		this.joinSaleNum = joinSaleNum;
	}

	public Integer getJoinDfgSaleNum() {
		return joinDfgSaleNum;
	}

	public void setJoinDfgSaleNum(Integer joinDfgSaleNum) {
		this.joinDfgSaleNum = joinDfgSaleNum;
	}

	public Integer getSignUpProductNum() {
		return signUpProductNum;
	}

	public void setSignUpProductNum(Integer signUpProductNum) {
		this.signUpProductNum = signUpProductNum;
	}

	public Integer getMchtSignNum() {
		return mchtSignNum;
	}

	public void setMchtSignNum(Integer mchtSignNum) {
		this.mchtSignNum = mchtSignNum;
	}

	public Integer getThisProductQuantityNum() {
		return thisProductQuantityNum;
	}

	public void setThisProductQuantityNum(Integer thisProductQuantityNum) {
		this.thisProductQuantityNum = thisProductQuantityNum;
	}
	
	public Integer getThisProductAppealNum() {
		return thisProductAppealNum;
	}

	public void setThisProductAppealNum(Integer thisProductAppealNum) {
		this.thisProductAppealNum = thisProductAppealNum;
	}
	
	public Integer getThisMchtViolateNum() {
		return thisMchtViolateNum;
	}

	public void setThisMchtViolateNum(Integer thisMchtViolateNum) {
		this.thisMchtViolateNum = thisMchtViolateNum;
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
	
	public BigDecimal getPopCommissionRate() {
		return popCommissionRate;
	}

	public void setPopCommissionRate(BigDecimal popCommissionRate) {
		this.popCommissionRate = popCommissionRate;
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

	public String getEachDay() {
		return eachDay;
	}

	public void setEachDay(String eachDay) {
		this.eachDay = eachDay;
	}

	public int getShopdata() {
		return shopdata;
	}

	public void setShopdata(int shopdata) {
		this.shopdata = shopdata;
	}

	public int getExaminedata() {
		return examinedata;
	}

	public void setExaminedata(int examinedata) {
		this.examinedata = examinedata;
	}

	public int getSaledata() {
		return saledata;
	}

	public void setSaledata(int saledata) {
		this.saledata = saledata;
	}

	public int getSingleproductdata() {
		return singleproductdata;
	}

	public void setSingleproductdata(int singleproductdata) {
		this.singleproductdata = singleproductdata;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	
	
}
