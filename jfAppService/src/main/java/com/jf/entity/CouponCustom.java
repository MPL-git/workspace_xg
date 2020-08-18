package com.jf.entity;

import java.util.Date;

public class CouponCustom extends Coupon{
    private boolean isHasCoupon;
    private String couponReceiveMsg;
    private String couponIds;
    private String activityName;
    private String productPic;
    private String quantityInfo;
    private Integer activityId;
    private Date recDate;
    private String memberCouponIds;

	/**  
	 * isHasCoupon  
	 * @return isHasCoupon isHasCoupon  
	 */
	public boolean isHasCoupon() {
		return isHasCoupon;
	}
	

	/**  
	 * isHasCoupon  
	 * @param isHasCoupon isHasCoupon  
	 */
	public void setIsHasCoupon(boolean isHasCoupon) {
		this.isHasCoupon = isHasCoupon;
	}


	/**  
	 * couponReceiveMsg  
	 * @return couponReceiveMsg couponReceiveMsg  
	 */
	public String getCouponReceiveMsg() {
		return couponReceiveMsg;
	}
	


	/**  
	 * couponReceiveMsg  
	 * @param couponReceiveMsg couponReceiveMsg  
	 */
	public void setCouponReceiveMsg(String couponReceiveMsg) {
		this.couponReceiveMsg = couponReceiveMsg;
	}
	


	/**  
	 * isHasCoupon  
	 * @param isHasCoupon isHasCoupon  
	 */
	public void setHasCoupon(boolean isHasCoupon) {
		this.isHasCoupon = isHasCoupon;
	}


	public String getCouponIds() {
		return couponIds;
	}


	public void setCouponIds(String couponIds) {
		this.couponIds = couponIds;
	}


	public String getActivityName() {
		return activityName;
	}


	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}


	public String getProductPic() {
		return productPic;
	}


	public void setProductPic(String productPic) {
		this.productPic = productPic;
	}


	public String getQuantityInfo() {
		return quantityInfo;
	}

	public void setQuantityInfo(String quantityInfo) {
		this.quantityInfo = quantityInfo;
	}


	public Integer getActivityId() {
		return activityId;
	}


	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}


	public Date getRecDate() {
		return recDate;
	}


	public void setRecDate(Date recDate) {
		this.recDate = recDate;
	}


	public String getMemberCouponIds() {
		return memberCouponIds;
	}


	public void setMemberCouponIds(String memberCouponIds) {
		this.memberCouponIds = memberCouponIds;
	}
	
}