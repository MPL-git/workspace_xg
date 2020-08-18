package com.jf.entity;

import java.util.Date;


public class CouponCustom extends Coupon{
	private String statusDesc;
	private String useQuantity;//优惠券使用数
	private String expiryTypeDesc;
	private String minMemberGroupName;
	private String recTypeDesc;
	private String staffName;
	private Integer secondNum;
	private Integer sumCouponExchange; //生成游离码数
	private Integer sumCouponIsExchange; //兑换优惠券数
	private String activityAreaName; //活动会场名称
	private String activityAreaBenefitPoint; //活动会场利益点
	private Date activityAreaABeginTime; //活动会场开始时间
	private Date activityAreaEndTime; //活动会场结束时间
	private String productTypeName; //类目
	private String mchtName;
	private String mchtCode;
	private String pic;
	private String productCode;
	private String productName;
	private String productArtNo;
	private String productBrandName;//商品的品牌
	
	
	

	public String getStatusDesc() {
		return statusDesc;
	}
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	public String getUseQuantity() {
		return useQuantity;
	}
	public void setUseQuantity(String useQuantity) {
		this.useQuantity = useQuantity;
	}
	public String getExpiryTypeDesc() {
		return expiryTypeDesc;
	}
	public void setExpiryTypeDesc(String expiryTypeDesc) {
		this.expiryTypeDesc = expiryTypeDesc;
	}
	public String getMinMemberGroupName() {
		return minMemberGroupName;
	}
	public void setMinMemberGroupName(String minMemberGroupName) {
		this.minMemberGroupName = minMemberGroupName;
	}
	public String getRecTypeDesc() {
		return recTypeDesc;
	}
	public void setRecTypeDesc(String recTypeDesc) {
		this.recTypeDesc = recTypeDesc;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
    public Integer getSecondNum() {
        return secondNum;
    }
    public void setSecondNum(Integer secondNum) {
        this.secondNum = secondNum;
    }
	public Integer getSumCouponExchange() {
		return sumCouponExchange;
	}
	public void setSumCouponExchange(Integer sumCouponExchange) {
		this.sumCouponExchange = sumCouponExchange;
	}
	public Integer getSumCouponIsExchange() {
		return sumCouponIsExchange;
	}
	public void setSumCouponIsExchange(Integer sumCouponIsExchange) {
		this.sumCouponIsExchange = sumCouponIsExchange;
	}
	public String getActivityAreaName() {
		return activityAreaName;
	}
	public void setActivityAreaName(String activityAreaName) {
		this.activityAreaName = activityAreaName;
	}
	public String getActivityAreaBenefitPoint() {
		return activityAreaBenefitPoint;
	}
	public void setActivityAreaBenefitPoint(String activityAreaBenefitPoint) {
		this.activityAreaBenefitPoint = activityAreaBenefitPoint;
	}
	public Date getActivityAreaABeginTime() {
		return activityAreaABeginTime;
	}
	public void setActivityAreaABeginTime(Date activityAreaABeginTime) {
		this.activityAreaABeginTime = activityAreaABeginTime;
	}
	public Date getActivityAreaEndTime() {
		return activityAreaEndTime;
	}
	public void setActivityAreaEndTime(Date activityAreaEndTime) {
		this.activityAreaEndTime = activityAreaEndTime;
	}
	public String getProductTypeName() {
		return productTypeName;
	}
	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}
	public String getMchtName() {
		return mchtName;
	}
	public void setMchtName(String mchtName) {
		this.mchtName = mchtName;
	}
	public String getMchtCode() {
		return mchtCode;
	}
	public void setMchtCode(String mchtCode) {
		this.mchtCode = mchtCode;
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
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductArtNo() {
		return productArtNo;
	}
	public void setProductArtNo(String productArtNo) {
		this.productArtNo = productArtNo;
	}
	public String getProductBrandName() {
		return productBrandName;
	}
	public void setProductBrandName(String productBrandName) {
		this.productBrandName = productBrandName;
	}
	
    
}
