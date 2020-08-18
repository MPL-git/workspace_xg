package com.jf.vo;

import java.math.BigDecimal;

public class SvipProductAutomaticGrab {

	/**
	 * 商品ID
	 */
	private Integer productId;

	/**
	 *一级类目ID
	 */
	private Integer productType1Id;

	/**
	 *权重
	 */
	private Integer saleWeight;

	/**
	 *销量
	 */
	private Integer salesVolume;

	/**
	 *优惠金额
	 */
	private BigDecimal discountAmount;

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getProductType1Id() {
		return productType1Id;
	}

	public void setProductType1Id(Integer productType1Id) {
		this.productType1Id = productType1Id;
	}

	public Integer getSaleWeight() {
		return saleWeight;
	}

	public void setSaleWeight(Integer saleWeight) {
		this.saleWeight = saleWeight;
	}

	public Integer getSalesVolume() {
		return salesVolume;
	}

	public void setSalesVolume(Integer salesVolume) {
		this.salesVolume = salesVolume;
	}

	public BigDecimal getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(BigDecimal discountAmount) {
		this.discountAmount = discountAmount;
	}
}
