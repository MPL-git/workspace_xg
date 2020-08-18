package com.jf.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @ClassName ActivityNewCustom
 * @Description TODO(这里用一句话描述这个方法的作用)
 * @author pengl
 * @date 2018年1月11日 上午10:47:13
 */
public class ActivityNewCustom extends Activity {

	private String benefitPoint; // 专区利益点
	private String preferentialTypeDesc; // 特惠类型名称
	private String shopName; // 店铺名称
	private String mchtCode; // 商家编码
	private String operateAuditName; // 运营专员名称
	private String operateAuditStatusDesc; // 运营专员审核状态
	private String cooAuditName; // 运营总监名称
	private String cooAuditStatusDesc; // 运营总监审核状态
	private String activityStatusDesc; // 活动状态
	private Date activityBeginTime; // 活动开始时间
	private Date activityEndTime; // 活动结束时间
	private Date preheatTime; // 活动预热时间
	private BigDecimal popCommissionRate; // pop佣金比例
	private String preferentialType; // 特惠类型
	private String activityAreaName; // 专区的会场名称
	private String designAuditName; // 设计部审核名称
	private String designAuditStatusDesc; // 设计部审核状态
	private Integer sumOperateAudit; // 专员待审商品
	private Integer sumOperatePass; // 专员通过商品
	private Integer sumOperateProductItem; // 专员库存
	private Integer sumCooAudit; // 排期待审商品
	private Integer sumCooPass; // 排期通过商品
	private Integer sumCooProductItem; // 排期库存
	private Integer sumAllCooAudit; // 全部待审
	private Integer activityAreaActivityGroupId; // 活动会场特卖分组ID
	private String activityAreaIsSign; // 活动会场是否标志(0 否 1 是)
	private String groupName; // 分组名称
	private String productBrandName; // 品牌
	private String productTypeName; // 类目
	private Integer adInfoId; // 广告信息ID
	private String source; // 会场来源
	private String preferentialIdGroup; // 特惠相关ID集合
	private String payAmountSum; // 销售额
	private Integer activityBrandgroupactiviyId;// 特卖品牌团-特卖关联表ID
	private String activityBrandGroupName; // 特卖品牌团
	private String mchtGradeDesc; // 商家等级
	private String mchtType;//商家类型
	private String isManageSelf;//是否自营spop

	public String getIsManageSelf() {
		return isManageSelf;
	}

	public void setIsManageSelf(String isManageSelf) {
		this.isManageSelf = isManageSelf;
	}

	public String getBenefitPoint() {
		return benefitPoint;
	}

	public void setBenefitPoint(String benefitPoint) {
		this.benefitPoint = benefitPoint;
	}

	public String getPreferentialTypeDesc() {
		return preferentialTypeDesc;
	}

	public void setPreferentialTypeDesc(String preferentialTypeDesc) {
		this.preferentialTypeDesc = preferentialTypeDesc;
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

	public String getOperateAuditName() {
		return operateAuditName;
	}

	public void setOperateAuditName(String operateAuditName) {
		this.operateAuditName = operateAuditName;
	}

	public String getOperateAuditStatusDesc() {
		return operateAuditStatusDesc;
	}

	public void setOperateAuditStatusDesc(String operateAuditStatusDesc) {
		this.operateAuditStatusDesc = operateAuditStatusDesc;
	}

	public String getCooAuditName() {
		return cooAuditName;
	}

	public void setCooAuditName(String cooAuditName) {
		this.cooAuditName = cooAuditName;
	}

	public String getCooAuditStatusDesc() {
		return cooAuditStatusDesc;
	}

	public void setCooAuditStatusDesc(String cooAuditStatusDesc) {
		this.cooAuditStatusDesc = cooAuditStatusDesc;
	}

	public String getActivityStatusDesc() {
		return activityStatusDesc;
	}

	public void setActivityStatusDesc(String activityStatusDesc) {
		this.activityStatusDesc = activityStatusDesc;
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

	public Date getPreheatTime() {
		return preheatTime;
	}

	public void setPreheatTime(Date preheatTime) {
		this.preheatTime = preheatTime;
	}

	public BigDecimal getPopCommissionRate() {
		return popCommissionRate;
	}

	public void setPopCommissionRate(BigDecimal popCommissionRate) {
		this.popCommissionRate = popCommissionRate;
	}

	public String getPreferentialType() {
		return preferentialType;
	}

	public void setPreferentialType(String preferentialType) {
		this.preferentialType = preferentialType;
	}

	public String getActivityAreaName() {
		return activityAreaName;
	}

	public void setActivityAreaName(String activityAreaName) {
		this.activityAreaName = activityAreaName;
	}

	public String getDesignAuditName() {
		return designAuditName;
	}

	public void setDesignAuditName(String designAuditName) {
		this.designAuditName = designAuditName;
	}

	public String getDesignAuditStatusDesc() {
		return designAuditStatusDesc;
	}

	public void setDesignAuditStatusDesc(String designAuditStatusDesc) {
		this.designAuditStatusDesc = designAuditStatusDesc;
	}

	public Integer getSumOperateAudit() {
		return sumOperateAudit;
	}

	public void setSumOperateAudit(Integer sumOperateAudit) {
		this.sumOperateAudit = sumOperateAudit;
	}

	public Integer getSumOperatePass() {
		return sumOperatePass;
	}

	public void setSumOperatePass(Integer sumOperatePass) {
		this.sumOperatePass = sumOperatePass;
	}

	public Integer getSumOperateProductItem() {
		return sumOperateProductItem;
	}

	public void setSumOperateProductItem(Integer sumOperateProductItem) {
		this.sumOperateProductItem = sumOperateProductItem;
	}

	public Integer getSumCooAudit() {
		return sumCooAudit;
	}

	public void setSumCooAudit(Integer sumCooAudit) {
		this.sumCooAudit = sumCooAudit;
	}

	public Integer getSumCooPass() {
		return sumCooPass;
	}

	public void setSumCooPass(Integer sumCooPass) {
		this.sumCooPass = sumCooPass;
	}

	public Integer getSumCooProductItem() {
		return sumCooProductItem;
	}

	public void setSumCooProductItem(Integer sumCooProductItem) {
		this.sumCooProductItem = sumCooProductItem;
	}

	public Integer getSumAllCooAudit() {
		return sumAllCooAudit;
	}

	public void setSumAllCooAudit(Integer sumAllCooAudit) {
		this.sumAllCooAudit = sumAllCooAudit;
	}

	public Integer getActivityAreaActivityGroupId() {
		return activityAreaActivityGroupId;
	}

	public void setActivityAreaActivityGroupId(
			Integer activityAreaActivityGroupId) {
		this.activityAreaActivityGroupId = activityAreaActivityGroupId;
	}

	public String getActivityAreaIsSign() {
		return activityAreaIsSign;
	}

	public void setActivityAreaIsSign(String activityAreaIsSign) {
		this.activityAreaIsSign = activityAreaIsSign;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
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

	public Integer getAdInfoId() {
		return adInfoId;
	}

	public void setAdInfoId(Integer adInfoId) {
		this.adInfoId = adInfoId;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getPreferentialIdGroup() {
		return preferentialIdGroup;
	}

	public void setPreferentialIdGroup(String preferentialIdGroup) {
		this.preferentialIdGroup = preferentialIdGroup;
	}

	public String getPayAmountSum() {
		return payAmountSum;
	}

	public void setPayAmountSum(String payAmountSum) {
		this.payAmountSum = payAmountSum;
	}

	public Integer getActivityBrandgroupactiviyId() {
		return activityBrandgroupactiviyId;
	}

	public void setActivityBrandgroupactiviyId(
			Integer activityBrandgroupactiviyId) {
		this.activityBrandgroupactiviyId = activityBrandgroupactiviyId;
	}

	public String getActivityBrandGroupName() {
		return activityBrandGroupName;
	}

	public void setActivityBrandGroupName(String activityBrandGroupName) {
		this.activityBrandGroupName = activityBrandGroupName;
	}

	public String getMchtGradeDesc() {
		return mchtGradeDesc;
	}

	public void setMchtGradeDesc(String mchtGradeDesc) {
		this.mchtGradeDesc = mchtGradeDesc;
	}

	public String getMchtType() {
		return mchtType;
	}

	public void setMchtType(String mchtType) {
		this.mchtType = mchtType;
	}

}
