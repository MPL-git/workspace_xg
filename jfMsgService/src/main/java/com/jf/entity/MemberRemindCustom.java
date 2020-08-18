package com.jf.entity;

import java.util.Date;

public class MemberRemindCustom extends MemberRemind{
    private String entryPic;

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

    private double tagPrice;

    private double salePrice;
    /**
     * 商品id
     */
    private Integer productId;
    /**
     * 会场形式(1会场 2品牌特卖)
     */
    private String source;
    /**
     * 是否接受推送(1是 2否)
     */
    private String isAcceptPush;

	public String getEntryPic() {
		return entryPic;
	}

	public void setEntryPic(String entryPic) {
		this.entryPic = entryPic;
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
	 * 商品id  
	 * @return productId 商品id  
	 */
	public Integer getProductId() {
		return productId;
	}
	

	/**  
	 * 商品id  
	 * @param productId 商品id  
	 */
	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	/**  
	 * 是否接受推送(1是2否)  
	 * @return isAcceptPush 是否接受推送(1是2否)  
	 */
	public String getIsAcceptPush() {
		return isAcceptPush;
	}
	

	/**  
	 * 是否接受推送(1是2否)  
	 * @param isAcceptPush 是否接受推送(1是2否)  
	 */
	public void setIsAcceptPush(String isAcceptPush) {
		this.isAcceptPush = isAcceptPush;
	}
	
}
