package com.jf.entity;

import java.math.BigDecimal;
import java.util.List;

public class ActivityAreaCustom extends ActivityArea {

	private String createStaffName;// 创建人名称

	private String sourceDesc;// 专区来源

	private String typeDesc;// 专区类型

	private String pushMchtTypeDese;// 推送商家类型

	private String preferentialTypeDesc;// 特惠类型

	private String statusDesc;// 专区状态

	private Integer activityDays;// 距离活动时间还剩下几天

	private Integer activityId;// 特卖活动ID

	private String shortName;// 商家简称

	private String mchtCode;// 商家序号

	private String brandName;// 活动品牌名称

	private String minMemberGroupName;// 面对对像名称

	private Integer adoptMchNum;// 通过报名商家数量

	private Integer signUpNum;// 报名商家数量

	private Integer adoptActivityProductNum;// 通过的商品数量

	private Integer signActivityProductNum;// 报名的商品数量

	private Integer thisActivityAreaNum;// 本次活动销量

	private BigDecimal thisActivityAreaSalePrice;// 本次活动销售额

	private Integer trialOperateNum; // 特卖（专员审核状态！=通过 && ！=驳回）

	private Integer passOperateNum; // 特卖（专员审核状态=通过）

	private Integer trialOperateAuditNum; // 商品（专员审核状态！=通过、！=驳回）

	private Integer passOperateAuditNum; // 商品（专员审核状态=通过）

	private Integer trialCooNum; // 特卖（排期审核状态！=通过 && ！=驳回）

	private Integer passCooNum; // 特卖（排期审核状态=通过）

	private Integer trialCooAuditNum; // 商品（排期审核状态！=通过、！=驳回）

	private Integer passCooAuditNum; // 商品（排期审核状态=通过）

	private String templetTypeDesc; // 模板类型(1默认模板 2代码模板 3自定义模板)

	private String mchtGradeDesc; // 商家等级
	
	private List<CouponCustom> couponList; // 当特卖为优惠券时的优惠券集合

	public String getCreateStaffName() {
		return createStaffName;
	}

	public void setCreateStaffName(String createStaffName) {
		this.createStaffName = createStaffName;
	}

	public String getSourceDesc() {
		return sourceDesc;
	}

	public void setSourceDesc(String sourceDesc) {
		this.sourceDesc = sourceDesc;
	}

	public String getTypeDesc() {
		return typeDesc;
	}

	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}

	public String getPushMchtTypeDese() {
		return pushMchtTypeDese;
	}

	public void setPushMchtTypeDese(String pushMchtTypeDese) {
		this.pushMchtTypeDese = pushMchtTypeDese;
	}

	public String getPreferentialTypeDesc() {
		return preferentialTypeDesc;
	}

	public void setPreferentialTypeDesc(String preferentialTypeDesc) {
		this.preferentialTypeDesc = preferentialTypeDesc;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public Integer getActivityDays() {
		return activityDays;
	}

	public void setActivityDays(Integer activityDays) {
		this.activityDays = activityDays;
	}

	public Integer getActivityId() {
		return activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
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

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getMinMemberGroupName() {
		return minMemberGroupName;
	}

	public void setMinMemberGroupName(String minMemberGroupName) {
		this.minMemberGroupName = minMemberGroupName;
	}

	public Integer getAdoptMchNum() {
		return adoptMchNum;
	}

	public void setAdoptMchNum(Integer adoptMchNum) {
		this.adoptMchNum = adoptMchNum;
	}

	public Integer getSignUpNum() {
		return signUpNum;
	}

	public void setSignUpNum(Integer signUpNum) {
		this.signUpNum = signUpNum;
	}

	public Integer getAdoptActivityProductNum() {
		return adoptActivityProductNum;
	}

	public void setAdoptActivityProductNum(Integer adoptActivityProductNum) {
		this.adoptActivityProductNum = adoptActivityProductNum;
	}

	public Integer getSignActivityProductNum() {
		return signActivityProductNum;
	}

	public void setSignActivityProductNum(Integer signActivityProductNum) {
		this.signActivityProductNum = signActivityProductNum;
	}

	public Integer getThisActivityAreaNum() {
		return thisActivityAreaNum;
	}

	public void setThisActivityAreaNum(Integer thisActivityAreaNum) {
		this.thisActivityAreaNum = thisActivityAreaNum;
	}

	public BigDecimal getThisActivityAreaSalePrice() {
		return thisActivityAreaSalePrice;
	}

	public void setThisActivityAreaSalePrice(
			BigDecimal thisActivityAreaSalePrice) {
		this.thisActivityAreaSalePrice = thisActivityAreaSalePrice;
	}

	public Integer getTrialOperateNum() {
		return trialOperateNum;
	}

	public void setTrialOperateNum(Integer trialOperateNum) {
		this.trialOperateNum = trialOperateNum;
	}

	public Integer getPassOperateNum() {
		return passOperateNum;
	}

	public void setPassOperateNum(Integer passOperateNum) {
		this.passOperateNum = passOperateNum;
	}

	public Integer getTrialOperateAuditNum() {
		return trialOperateAuditNum;
	}

	public void setTrialOperateAuditNum(Integer trialOperateAuditNum) {
		this.trialOperateAuditNum = trialOperateAuditNum;
	}

	public Integer getPassOperateAuditNum() {
		return passOperateAuditNum;
	}

	public void setPassOperateAuditNum(Integer passOperateAuditNum) {
		this.passOperateAuditNum = passOperateAuditNum;
	}

	public Integer getTrialCooNum() {
		return trialCooNum;
	}

	public void setTrialCooNum(Integer trialCooNum) {
		this.trialCooNum = trialCooNum;
	}

	public Integer getPassCooNum() {
		return passCooNum;
	}

	public void setPassCooNum(Integer passCooNum) {
		this.passCooNum = passCooNum;
	}

	public Integer getTrialCooAuditNum() {
		return trialCooAuditNum;
	}

	public void setTrialCooAuditNum(Integer trialCooAuditNum) {
		this.trialCooAuditNum = trialCooAuditNum;
	}

	public Integer getPassCooAuditNum() {
		return passCooAuditNum;
	}

	public void setPassCooAuditNum(Integer passCooAuditNum) {
		this.passCooAuditNum = passCooAuditNum;
	}

	public String getTempletTypeDesc() {
		return templetTypeDesc;
	}

	public void setTempletTypeDesc(String templetTypeDesc) {
		this.templetTypeDesc = templetTypeDesc;
	}

	public String getMchtGradeDesc() {
		return mchtGradeDesc;
	}

	public void setMchtGradeDesc(String mchtGradeDesc) {
		this.mchtGradeDesc = mchtGradeDesc;
	}

	public List<CouponCustom> getCouponList() {
		return couponList;
	}

	public void setCouponList(List<CouponCustom> couponList) {
		this.couponList = couponList;
	}

	
}
