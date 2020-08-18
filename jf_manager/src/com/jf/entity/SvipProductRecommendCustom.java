package com.jf.entity;

import java.math.BigDecimal;

public class SvipProductRecommendCustom extends SvipProductRecommend {

	private String code; // 商品编号
	private String productTypeName; // 分类
	private String productBrandName; // 品牌
	private String name; // 名称
	private String artNo; // 货号
	private String pic; // 商品图
	private BigDecimal tagPriceMin; 
	private BigDecimal tagPriceMax; 
	private BigDecimal mallPriceMin; 
	private BigDecimal mallPriceMax; 
	private BigDecimal salePriceMin; 
	private BigDecimal salePriceMax; 
	private BigDecimal svipDiscount; //SVIP折扣
	private String status; 
	private String mchtName;
	
	
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
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
	public String getProductTypeName() {
		return productTypeName;
	}
	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}
	public String getProductBrandName() {
		return productBrandName;
	}
	public void setProductBrandName(String productBrandName) {
		this.productBrandName = productBrandName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getTagPriceMin() {
		return tagPriceMin;
	}
	public void setTagPriceMin(BigDecimal tagPriceMin) {
		this.tagPriceMin = tagPriceMin;
	}
	public BigDecimal getTagPriceMax() {
		return tagPriceMax;
	}
	public void setTagPriceMax(BigDecimal tagPriceMax) {
		this.tagPriceMax = tagPriceMax;
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
	public BigDecimal getSvipDiscount() {
		return svipDiscount;
	}
	public void setSvipDiscount(BigDecimal svipDiscount) {
		this.svipDiscount = svipDiscount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMchtName() {
		return mchtName;
	}
	public void setMchtName(String mchtName) {
		this.mchtName = mchtName;
	} 

	
}
