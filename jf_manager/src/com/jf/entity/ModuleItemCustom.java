package com.jf.entity;

import java.math.BigDecimal;


public class ModuleItemCustom extends ModuleItem{
	private String pic;
	private String productName;
	private String productCode;
	private BigDecimal salePriceMin;
	private BigDecimal salePriceMax;
	//折扣
	private BigDecimal discountMax;
	private BigDecimal discountMin;
	private int stock;

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

	public BigDecimal getDiscountMax() {
		return discountMax;
	}

	public void setDiscountMax(BigDecimal discountMax) {
		this.discountMax = discountMax;
	}

	public BigDecimal getDiscountMin() {
		return discountMin;
	}

	public void setDiscountMin(BigDecimal discountMin) {
		this.discountMin = discountMin;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}
	
	
}
