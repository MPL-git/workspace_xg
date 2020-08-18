package com.jf.entity;

import java.util.Date;

public class MemberPlatformCoupon {

	private Integer id;
	private String rang;
	private String name;
	private Date expiryBeginDate;
	private Date expiryEndDate;
	private String money;
	private String minimum;
	private String couponType;
	private String typeIds;
	private String canSuperpose;
	private String productTypeName;
	private String content;
	private Integer couponId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRang() {
		return rang;
	}

	public void setRang(String rang) {
		this.rang = rang;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Integer getCouponId() {
		return couponId;
	}
	

	public void setCouponId(Integer couponId) {
		this.couponId = couponId;
	}
	

}
