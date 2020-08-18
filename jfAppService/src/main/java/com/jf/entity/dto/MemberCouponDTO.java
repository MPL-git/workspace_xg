package com.jf.entity.dto;

import java.util.Date;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年4月20日 下午5:15:59 
  * @version 1.0 
  * @parameter  
  * @return  
*/
public class MemberCouponDTO {
	private String id;
	
	private Date expiryBeginDate;

    private Date expiryEndDate;
    
    private String name;
    
    private String rang;
    
    private String money;
    
    private String minimum;
    
    private String orderByDate;
    
    /**
     * 会场id
     */
    private Integer activityAreaId;
    
    /**
     * 活动id
     */
    private Integer activityId;
    
    /**
	 * 会场形式(1会场 2品牌特卖)
	 */
	private String source;
	/**
	 * h5模板
	 */
	private String templetSuffix;
	/**
	 * 会场名称
	 */
	private String activityAreaName;
	/**
	 * 会场类型(1品牌活动 2单品活动 6单品爆款 7新用户专享)
	 */
	private String activityAreaType;
	/**
	 * 商家id
	 */
	private Integer mchtId;
	/**
     * 优惠券未使用数量
     */
    private Integer unUsedCouponCount;
    /**
     * 优惠券已使用数量
     */
    private Integer usedCouponCount;
    /**
     * 优惠券已过期数量
     */
    private Integer expiredCouponCount;
    /**
	 * 优惠券类型\r\n1 全场通用\r\n2 品类券\r\n3 品牌券
	 */
	private String couponType;
	/**
	 * 类型范围id\r\n以逗号分隔的品类，品牌id等 
	 */
	private String typeIds;
	/**
	 * 不同类型优惠券是否可叠加\r\n0 否\r\n1 是
	 */
	private String canSuperpose;
	/**
	 * 品类名称
	 */
	private String productTypeName;
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 一级类目名称
	 */
	private String productType1Name;
	/**
	 * 优惠券领取途径 1.其他途径2.签到领取 3.累签领取 4.购买 5.SUPER VIP领取6.SUPER VIP赠送
	 */
	private String receiveType;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	

	public Date getExpiryBeginDate() {
		return expiryBeginDate;
	}
	

	public void setExpiryBeginDate(Date expiryBeginDate) {
		this.expiryBeginDate = expiryBeginDate;
	}
	

	public Date getExpiryEndDate() {
		return expiryEndDate;
	}
	

	public void setExpiryEndDate(Date expiryEndDate) {
		this.expiryEndDate = expiryEndDate;
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRang() {
		return rang;
	}

	public void setRang(String rang) {
		this.rang = rang;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getMinimum() {
		return minimum;
	}

	public void setMinimum(String minimum) {
		this.minimum = minimum;
	}

	public String getOrderByDate() {
		return orderByDate;
	}

	public void setOrderByDate(String orderByDate) {
		this.orderByDate = orderByDate;
	}

	/**  
	 * 会场id  
	 * @return activityAreaId 会场id  
	 */
	public Integer getActivityAreaId() {
		return activityAreaId;
	}
	

	/**  
	 * 会场id  
	 * @param activityAreaId 会场id  
	 */
	public void setActivityAreaId(Integer activityAreaId) {
		this.activityAreaId = activityAreaId;
	}

	/**  
	 * 会场形式(1会场2品牌特卖)  
	 * @return source 会场形式(1会场2品牌特卖)  
	 */
	public String getSource() {
		return source;
	}
	

	/**  
	 * 会场形式(1会场2品牌特卖)  
	 * @param source 会场形式(1会场2品牌特卖)  
	 */
	public void setSource(String source) {
		this.source = source;
	}

	/**  
	 * 活动id  
	 * @return activityId 活动id  
	 */
	public Integer getActivityId() {
		return activityId;
	}
	

	/**  
	 * 活动id  
	 * @param activityId 活动id  
	 */
	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	/**  
	 * h5模板  
	 * @return templetSuffix h5模板  
	 */
	public String getTempletSuffix() {
		return templetSuffix;
	}
	

	/**  
	 * h5模板  
	 * @param templetSuffix h5模板  
	 */
	public void setTempletSuffix(String templetSuffix) {
		this.templetSuffix = templetSuffix;
	}

	/**  
	 * 会场名称  
	 * @return activityAreaName 会场名称  
	 */
	public String getActivityAreaName() {
		return activityAreaName;
	}
	

	/**  
	 * 会场名称  
	 * @param activityAreaName 会场名称  
	 */
	public void setActivityAreaName(String activityAreaName) {
		this.activityAreaName = activityAreaName;
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
	 * 商家id  
	 * @return mchtId 商家id  
	 */
	public Integer getMchtId() {
		return mchtId;
	}
	

	/**  
	 * 商家id  
	 * @param mchtId 商家id  
	 */
	public void setMchtId(Integer mchtId) {
		this.mchtId = mchtId;
	}

	public Integer getUnUsedCouponCount() {
		return unUsedCouponCount;
	}
	

	public void setUnUsedCouponCount(Integer unUsedCouponCount) {
		this.unUsedCouponCount = unUsedCouponCount;
	}
	

	public Integer getUsedCouponCount() {
		return usedCouponCount;
	}
	

	public void setUsedCouponCount(Integer usedCouponCount) {
		this.usedCouponCount = usedCouponCount;
	}
	

	public Integer getExpiredCouponCount() {
		return expiredCouponCount;
	}
	

	public void setExpiredCouponCount(Integer expiredCouponCount) {
		this.expiredCouponCount = expiredCouponCount;
	}

	public String getCouponType() {
		return couponType;
	}
	

	public void setCouponType(String couponType) {
		this.couponType = couponType;
	}
	

	public String getTypeIds() {
		return typeIds;
	}
	

	public void setTypeIds(String typeIds) {
		this.typeIds = typeIds;
	}
	

	public String getCanSuperpose() {
		return canSuperpose;
	}
	

	public void setCanSuperpose(String canSuperpose) {
		this.canSuperpose = canSuperpose;
	}
	

	public String getProductTypeName() {
		return productTypeName;
	}
	

	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}
	

	public String getContent() {
		return content;
	}
	

	public void setContent(String content) {
		this.content = content;
	}

	public String getProductType1Name() {
		return productType1Name;
	}
	

	public void setProductType1Name(String productType1Name) {
		this.productType1Name = productType1Name;
	}

	public String getReceiveType() {
		return receiveType;
	}
	

	public void setReceiveType(String receiveType) {
		this.receiveType = receiveType;
	}
	
}
