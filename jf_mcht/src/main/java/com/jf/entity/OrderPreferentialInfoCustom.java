package com.jf.entity;

import java.math.BigDecimal;


public class OrderPreferentialInfoCustom extends OrderPreferentialInfo{
	/**
	 * 优惠类型：1.优惠券
	 */
	public static String PREFERENTIALTYPE_COUPON = "1";
	/**
	 * 优惠类型：2.满减
	 */
	public static String PREFERENTIALTYPE_FULLCUT = "2";
	/**
	 * 优惠类型：3.满赠
	 */
	public static String PREFERENTIALTYPE_FULLGIVE = "3";
	/**
	 * 优惠类型：4.多买多折
	 */
	public static String PREFERENTIALTYPE_FULLDISCOUNT = "4";
	/**
	 * 优惠类型：5.积分优惠
	 */
	public static String PREFERENTIALTYPE_INTEGRALGIVE = "5";
	
	private String belongDesc;
	
	private String preferentialTypeDesc;
	
	private BigDecimal totalPreferentialAmount;
	
	private String productPreferentialDetail;

	public String getBelongDesc() {
		return belongDesc;
	}

	public void setBelongDesc(String belongDesc) {
		this.belongDesc = belongDesc;
	}

	public String getPreferentialTypeDesc() {
		return preferentialTypeDesc;
	}

	public void setPreferentialTypeDesc(String preferentialTypeDesc) {
		this.preferentialTypeDesc = preferentialTypeDesc;
	}

	public BigDecimal getTotalPreferentialAmount() {
		return totalPreferentialAmount;
	}

	public void setTotalPreferentialAmount(BigDecimal totalPreferentialAmount) {
		this.totalPreferentialAmount = totalPreferentialAmount;
	}

	public String getProductPreferentialDetail() {
		return productPreferentialDetail;
	}

	public void setProductPreferentialDetail(String productPreferentialDetail) {
		this.productPreferentialDetail = productPreferentialDetail;
	}
	
}
