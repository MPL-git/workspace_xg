package com.jf.entity;

public class MchtSingleActivityCnfCustom extends MchtSingleActivityCnf {

	private String mchtCode; //商家编码
	private String companyName; //公司名称
	private String shopName; //店铺名称
	private Integer limitMchtQuality; //商家每天默认可报数量
	private String activityTypeDesc; //活动类型
	private String producttypeName;//类目
	public MchtSingleActivityCnfCustom() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MchtSingleActivityCnfCustom(String mchtCode, String companyName,
			String shopName) {
		super();
		this.mchtCode = mchtCode;
		this.companyName = companyName;
		this.shopName = shopName;
	}
	
	public String getMchtCode() {
		return mchtCode;
	}
	public void setMchtCode(String mchtCode) {
		this.mchtCode = mchtCode;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public Integer getLimitMchtQuality() {
		return limitMchtQuality;
	}
	public void setLimitMchtQuality(Integer limitMchtQuality) {
		this.limitMchtQuality = limitMchtQuality;
	}
	public String getActivityTypeDesc() {
		return activityTypeDesc;
	}
	public void setActivityTypeDesc(String activityTypeDesc) {
		this.activityTypeDesc = activityTypeDesc;
	}

	public String getProducttypeName() {
		return producttypeName;
	}

	public void setProducttypeName(String producttypeName) {
		this.producttypeName = producttypeName;
	}

	
	
}
