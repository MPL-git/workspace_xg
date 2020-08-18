package com.jf.entity;

import java.util.Date;

public class MemberRemindCustom extends MemberRemind{
	/**
	 * 活动入口图
	 */
    private String entryPic;
	 /**
	 * 会场入口图
	 */
    private String activityAreaEntryPic;
    /**
	 * 会场类型
	 */
    private String activityAreaType;
    private String logo;

    private String pic;

    private String productName;
    
    private String activityName;
    /**
     * 会场名称
     */
    private String activityAreaName;

    private Integer remindId;

    private Integer activityAreaId;

    private Integer activityId;

    private Date activityBeginTime;
    
    /**
     * 活动结束时间
     */
    private Date activityEndTime;

    private double tagPrice;

    private double salePrice;
    /**
     * 利益点
     */
    private String benefitPoint;
    
    /**
     * 会场形式(1会场 2品牌特卖)
     */
    private String source;
    /**
     * h5模板编号
     */
    private String templetSuffix;
    /**
     * 店铺名称
     */
    private String shopName;
    /**
     * 店铺logo
     */
    private String shopLogo;
    /**
     * 屏蔽店铺动态0.否1.是
     */
    private String shieldingDynamics;
    
	/**  
	 * 活动入口图  
	 * @return entryPic 活动入口图  
	 */
	public String getEntryPic() {
		return entryPic;
	}
	

	/**  
	 * 活动入口图  
	 * @param entryPic 活动入口图  
	 */
	public void setEntryPic(String entryPic) {
		this.entryPic = entryPic;
	}
	

	/**  
	 * 会场入口图  
	 * @return activityAreaEntryPic 会场入口图  
	 */
	public String getActivityAreaEntryPic() {
		return activityAreaEntryPic;
	}
	

	/**  
	 * 会场入口图  
	 * @param activityAreaEntryPic 会场入口图  
	 */
	public void setActivityAreaEntryPic(String activityAreaEntryPic) {
		this.activityAreaEntryPic = activityAreaEntryPic;
	}
	

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getRemindId() {
		return remindId;
	}

	public void setRemindId(Integer remindId) {
		this.remindId = remindId;
	}

	public Integer getActivityAreaId() {
		return activityAreaId;
	}

	public void setActivityAreaId(Integer activityAreaId) {
		this.activityAreaId = activityAreaId;
	}

	public Integer getActivityId() {
		return activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	public Date getActivityBeginTime() {
		return activityBeginTime;
	}

	public void setActivityBeginTime(Date activityBeginTime) {
		this.activityBeginTime = activityBeginTime;
	}

	public double getTagPrice() {
		return tagPrice;
	}

	public void setTagPrice(double tagPrice) {
		this.tagPrice = tagPrice;
	}

	public double getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(double salePrice) {
		this.salePrice = salePrice;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
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
	 * h5模板编号  
	 * @return templetSuffix h5模板编号  
	 */
	public String getTempletSuffix() {
		return templetSuffix;
	}
	

	/**  
	 * h5模板编号  
	 * @param templetSuffix h5模板编号  
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
	 * 会场类型  
	 * @return activityAreaType 会场类型  
	 */
	public String getActivityAreaType() {
		return activityAreaType;
	}
	


	/**  
	 * 会场类型  
	 * @param activityAreaType 会场类型  
	 */
	public void setActivityAreaType(String activityAreaType) {
		this.activityAreaType = activityAreaType;
	}


	/**  
	 * 利益点  
	 * @return benefitPoint 利益点  
	 */
	public String getBenefitPoint() {
		return benefitPoint;
	}
	


	/**  
	 * 利益点  
	 * @param benefitPoint 利益点  
	 */
	public void setBenefitPoint(String benefitPoint) {
		this.benefitPoint = benefitPoint;
	}

	/**  
	 * 活动结束时间  
	 * @return activityEndTime 活动结束时间  
	 */
	public Date getActivityEndTime() {
		return activityEndTime;
	}
	
	/**  
	 * 活动结束时间  
	 * @param activityEndTime 活动结束时间  
	 */
	public void setActivityEndTime(Date activityEndTime) {
		this.activityEndTime = activityEndTime;
	}


	/**  
	 * 店铺名称  
	 * @return shopName 店铺名称  
	 */
	public String getShopName() {
		return shopName;
	}
	


	/**  
	 * 店铺名称  
	 * @param shopName 店铺名称  
	 */
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	


	/**  
	 * 店铺logo  
	 * @return shopLogo 店铺logo  
	 */
	public String getShopLogo() {
		return shopLogo;
	}
	


	/**  
	 * 店铺logo  
	 * @param shopLogo 店铺logo  
	 */
	public void setShopLogo(String shopLogo) {
		this.shopLogo = shopLogo;
	}


	public String getShieldingDynamics() {
		return shieldingDynamics;
	}


	public void setShieldingDynamics(String shieldingDynamics) {
		this.shieldingDynamics = shieldingDynamics;
	}
	
	
}
