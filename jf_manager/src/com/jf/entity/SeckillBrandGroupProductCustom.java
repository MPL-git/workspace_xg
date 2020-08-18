package com.jf.entity;

import java.math.BigDecimal;

public class SeckillBrandGroupProductCustom extends SeckillBrandGroupProduct {

	private String productPic;
	private String productArtNo;
	private String productCode;
	private String productBrandName;
	private String shopName;
	private String productName;
	private BigDecimal productActivityPrice;
	private String discount;
	private Integer stockSum;

	public String getProductPic() {
		return productPic;
	}

	public void setProductPic(String productPic) {
		this.productPic = productPic;
	}

	public String getProductArtNo() {
		return productArtNo;
	}

	public void setProductArtNo(String productArtNo) {
		this.productArtNo = productArtNo;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductBrandName() {
		return productBrandName;
	}

	public void setProductBrandName(String productBrandName) {
		this.productBrandName = productBrandName;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public BigDecimal getProductActivityPrice() {
		return productActivityPrice;
	}

	public void setProductActivityPrice(BigDecimal productActivityPrice) {
		this.productActivityPrice = productActivityPrice;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public Integer getStockSum() {
		return stockSum;
	}

	public void setStockSum(Integer stockSum) {
		this.stockSum = stockSum;
	}

}
