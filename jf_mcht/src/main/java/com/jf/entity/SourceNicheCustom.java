package com.jf.entity;

import java.math.BigDecimal;

public class SourceNicheCustom extends SourceNiche{
	private String pic;
	private String name;
	private String artNo;
	private String code;
	private int minSalePriceItemId;
	private BigDecimal svipDiscount;
	private int productItemStock;
	private int productCount;
	private BigDecimal salePriceMin;
	private BigDecimal salePriceMax;
	
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
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getArtNo() {
		return artNo;
	}
	public void setArtNo(String artNo) {
		this.artNo = artNo;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getMinSalePriceItemId() {
		return minSalePriceItemId;
	}
	public void setMinSalePriceItemId(int minSalePriceItemId) {
		this.minSalePriceItemId = minSalePriceItemId;
	}
	public BigDecimal getSvipDiscount() {
		return svipDiscount;
	}
	public void setSvipDiscount(BigDecimal svipDiscount) {
		this.svipDiscount = svipDiscount;
	}

	public int getProductItemStock() {
		return productItemStock;
	}

	public void setProductItemStock(int productItemStock) {
		this.productItemStock = productItemStock;
	}

	public int getProductCount() {
		return productCount;
	}
	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}
}
