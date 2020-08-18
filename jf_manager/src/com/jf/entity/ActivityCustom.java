package com.jf.entity;

import java.util.Date;

public class ActivityCustom extends Activity {
	
	private String shortName;//商家名称
	
	private String shopName;//店铺名称
	
	private String mchtCode;//商家序号
	
	private Date enrollBeginTime;//首次提报时间
	
	private String operateName;//运营审核人员名称
	
	private String designName;//设计部审核人员名称
	
	private String lawName;//法务审核人员名称
	
	private String cooName;//运营总监审核人员名称
	
	private Integer productNum;//商品数量
	
	private Double serviceCharge;//服务费用
	
	private String operateAuditStatusDesc;//运营专员审核状态
	
	private String designAuditStatusDesc;//设计审核状态
	
	private String lawAuditStatusDesc;//法务审核状态
	
	private String cooAuditStatusDesc;//运营总监审核状态
	
	private String qq;//商家联系人QQ（运营专员）
	
	private String productTypeName;//商品品类名称
	
	private String productBrandName;//商品品牌名称
	
	private String benefitPoint;//专区利益点
	
	private Date activityBeginTime;//活动开始时间
	
	private Date activityEndTime;//活动结束时间
	
	private Date enrollEndTime;//报名截止时间
	
	private Date mchtCooperateBeginDate;//商家入驻时间(合作开始日期)
	
	private Integer activityStock;//活动商品总库存量
	
	private String activityStatusDesc;//活动状态
	
	private String source;//活动专区类型：1/2
	
	private String sourceDesc;//活动专区来源：品牌特卖/会场
	
	private String type;//活动专区类型：1：品牌活动 2：单品活动6：单品爆款7：新用户专享
	
	private String fieldName;//活动专区里名称
	
	private Integer activityDays;//距离活动时间还剩下几天
	
	private Date preheatTime;//活动预热时间
	
	private String activityAreaTypeDesc;//专区类型
	
	private String activityAreaName;//专区的会场名称
	
	private String contactName;//商家对接人
	
	private String preferentialType;//特惠类型（专区表）
	
/*	private Integer brandSaleNum;//参加品牌特卖活动总数（商家）
	
	private Integer thirtyBrandSaleNum;//30天参加品牌特卖活动总数（商家）
	
	private Integer thirtyQuantity;//30天销量
	
	private BigDecimal thirtySalePrice;//30天销售额

	private Integer thisQuantity;//本次活动销量
	
	private BigDecimal thisSalePrice;//本次活动销售额
*/	
	private String activityAreaPic;//专区活动图标
	
	private Integer productAudit1Num;//本次活动商品待审核数量
	
	private Integer productAudit3Num;//本次活动商品待驳回数量
	
	private Integer thisProductAppealNum;//投诉次数（商品）
	
	private Integer thisMchtViolateNum;//违规次数（商家）
	
	public String getPreferentialType() {
		return preferentialType;
	}

	public void setPreferentialType(String preferentialType) {
		this.preferentialType = preferentialType;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

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

	public Date getEnrollBeginTime() {
		return enrollBeginTime;
	}

	public void setEnrollBeginTime(Date enrollBeginTime) {
		this.enrollBeginTime = enrollBeginTime;
	}

	public Integer getProductNum() {
		return productNum;
	}

	public void setProductNum(Integer productNum) {
		this.productNum = productNum;
	}

	public Double getServiceCharge() {
		return serviceCharge;
	}

	public void setServiceCharge(Double serviceCharge) {
		this.serviceCharge = serviceCharge;
	}

	public String getOperateAuditStatusDesc() {
		return operateAuditStatusDesc;
	}

	public void setOperateAuditStatusDesc(String operateAuditStatusDesc) {
		this.operateAuditStatusDesc = operateAuditStatusDesc;
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

	public String getCooAuditStatusDesc() {
		return cooAuditStatusDesc;
	}

	public void setCooAuditStatusDesc(String cooAuditStatusDesc) {
		this.cooAuditStatusDesc = cooAuditStatusDesc;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
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

	public String getBenefitPoint() {
		return benefitPoint;
	}

	public void setBenefitPoint(String benefitPoint) {
		this.benefitPoint = benefitPoint;
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

	public String getOperateName() {
		return operateName;
	}

	public void setOperateName(String operateName) {
		this.operateName = operateName;
	}

	public String getDesignName() {
		return designName;
	}

	public void setDesignName(String designName) {
		this.designName = designName;
	}

	public String getLawName() {
		return lawName;
	}

	public void setLawName(String lawName) {
		this.lawName = lawName;
	}

	public String getCooName() {
		return cooName;
	}

	public void setCooName(String cooName) {
		this.cooName = cooName;
	}

	public Integer getActivityStock() {
		return activityStock;
	}

	public void setActivityStock(Integer activityStock) {
		this.activityStock = activityStock;
	}

	public String getActivityStatusDesc() {
		return activityStatusDesc;
	}

	public void setActivityStatusDesc(String activityStatusDesc) {
		this.activityStatusDesc = activityStatusDesc;
	}
    
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
	
	public String getSourceDesc() {
		return sourceDesc;
	}

	public void setSourceDesc(String sourceDesc) {
		this.sourceDesc = sourceDesc;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public Date getEnrollEndTime() {
		return enrollEndTime;
	}

	public void setEnrollEndTime(Date enrollEndTime) {
		this.enrollEndTime = enrollEndTime;
	}

	public Integer getActivityDays() {
		return activityDays;
	}

	public void setActivityDays(Integer activityDays) {
		this.activityDays = activityDays;
	}

	public Date getPreheatTime() {
		return preheatTime;
	}

	public void setPreheatTime(Date preheatTime) {
		this.preheatTime = preheatTime;
	}

	public String getActivityAreaTypeDesc() {
		return activityAreaTypeDesc;
	}

	public void setActivityAreaTypeDesc(String activityAreaTypeDesc) {
		this.activityAreaTypeDesc = activityAreaTypeDesc;
	}

	public String getActivityAreaName() {
		return activityAreaName;
	}

	public void setActivityAreaName(String activityAreaName) {
		this.activityAreaName = activityAreaName;
	}

	public Date getMchtCooperateBeginDate() {
		return mchtCooperateBeginDate;
	}

	public void setMchtCooperateBeginDate(Date mchtCooperateBeginDate) {
		this.mchtCooperateBeginDate = mchtCooperateBeginDate;
	}

/*	public Integer getBrandSaleNum() {
		return brandSaleNum;
	}

	public void setBrandSaleNum(Integer brandSaleNum) {
		this.brandSaleNum = brandSaleNum;
	}

	public Integer getThirtyBrandSaleNum() {
		return thirtyBrandSaleNum;
	}

	public void setThirtyBrandSaleNum(Integer thirtyBrandSaleNum) {
		this.thirtyBrandSaleNum = thirtyBrandSaleNum;
	}

	public Integer getThirtyQuantity() {
		return thirtyQuantity;
	}

	public void setThirtyQuantity(Integer thirtyQuantity) {
		this.thirtyQuantity = thirtyQuantity;
	}

	public BigDecimal getThirtySalePrice() {
		return thirtySalePrice;
	}

	public void setThirtySalePrice(BigDecimal thirtySalePrice) {
		this.thirtySalePrice = thirtySalePrice;
	}

	public Integer getThisQuantity() {
		return thisQuantity;
	}

	public void setThisQuantity(Integer thisQuantity) {
		this.thisQuantity = thisQuantity;
	}

	public BigDecimal getThisSalePrice() {
		return thisSalePrice;
	}

	public void setThisSalePrice(BigDecimal thisSalePrice) {
		this.thisSalePrice = thisSalePrice;
	}*/

	public String getActivityAreaPic() {
		return activityAreaPic;
	}

	public void setActivityAreaPic(String activityAreaPic) {
		this.activityAreaPic = activityAreaPic;
	}
	
	public Integer getProductAudit1Num() {
		return productAudit1Num;
	}

	public void setProductAudit1Num(Integer productAudit1Num) {
		this.productAudit1Num = productAudit1Num;
	}
	
	public Integer getProductAudit3Num() {
		return productAudit3Num;
	}

	public void setProductAudit3Num(Integer productAudit3Num) {
		this.productAudit3Num = productAudit3Num;
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

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	
}