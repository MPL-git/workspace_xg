package com.jf.entity;

import java.math.BigDecimal;

public class BannerRecommendProductCustom  extends BannerRecommendProduct{

	private BigDecimal tagPriceMin;//吊牌价
	private BigDecimal tagPriceMax;
	private BigDecimal salePriceMin;//活动价格
	private BigDecimal salePriceMax; 
	private String pic;//主图
	private Integer productId;//商品ID 
	
	
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
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
	
}
