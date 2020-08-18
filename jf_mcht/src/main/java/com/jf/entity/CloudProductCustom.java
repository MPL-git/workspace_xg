package com.jf.entity;

import java.math.BigDecimal;


public class CloudProductCustom extends CloudProduct{
	private String brandName;
	
	private String propValues;
	
	private BigDecimal salePriceMin;
	
	private BigDecimal salePriceMax;
	
	private BigDecimal mallPriceMin;
	
	private BigDecimal mallPriceMax;

	private String productIds;
	
	private int stock;
	
	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getPropValues() {
		return propValues;
	}

	public void setPropValues(String propValues) {
		this.propValues = propValues;
	}

	public BigDecimal getSalePriceMin() {
		return salePriceMin;
	}

	public void setSalePriceMin(BigDecimal salePriceMin) {
		this.salePriceMin = salePriceMin;
	}

	public BigDecimal getSalePriceMax() {
		return salePriceMax;
	}

	public void setSalePriceMax(BigDecimal salePriceMax) {
		this.salePriceMax = salePriceMax;
	}

	public BigDecimal getMallPriceMin() {
		return mallPriceMin;
	}

	public void setMallPriceMin(BigDecimal mallPriceMin) {
		this.mallPriceMin = mallPriceMin;
	}

	public BigDecimal getMallPriceMax() {
		return mallPriceMax;
	}

	public void setMallPriceMax(BigDecimal mallPriceMax) {
		this.mallPriceMax = mallPriceMax;
	}

	public String getProductIds() {
		return productIds;
	}

	public void setProductIds(String productIds) {
		this.productIds = productIds;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
	
	
}
