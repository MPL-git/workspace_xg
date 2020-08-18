package com.jf.entity;

import java.math.BigDecimal;
import java.util.List;

public class MchtInfoCustom extends MchtInfo{
	private Integer sourceNicheId;
	private String storyIntroduction;
	private String productIds;
	private String oldProductIds;
	private List<ProductCustom> productCustomList; 
	private String couponIds;
	private List<Coupon> couponList; 
	private Integer totalSaleQuantity; 
	private Integer goodCommentCount; 
	private Integer totalCommentCount;
	private String shopSalesAndCommentRate;
	private Integer productSaleCount90Day;
	private BigDecimal productApplauseRate;
	public Integer getSourceNicheId() {
		return sourceNicheId;
	}

	public void setSourceNicheId(Integer sourceNicheId) {
		this.sourceNicheId = sourceNicheId;
	}

	public String getStoryIntroduction() {
		return storyIntroduction;
	}

	public void setStoryIntroduction(String storyIntroduction) {
		this.storyIntroduction = storyIntroduction;
	}

	public List<ProductCustom> getProductCustomList() {
		return productCustomList;
	}

	public void setProductCustomList(List<ProductCustom> productCustomList) {
		this.productCustomList = productCustomList;
	}

	public String getProductIds() {
		return productIds;
	}

	public void setProductIds(String productIds) {
		this.productIds = productIds;
	}

	public String getCouponIds() {
		return couponIds;
	}

	public void setCouponIds(String couponIds) {
		this.couponIds = couponIds;
	}

	public List<Coupon> getCouponList() {
		return couponList;
	}

	public void setCouponList(List<Coupon> couponList) {
		this.couponList = couponList;
	}

	public String getOldProductIds() {
		return oldProductIds;
	}

	public void setOldProductIds(String oldProductIds) {
		this.oldProductIds = oldProductIds;
	}

	public Integer getTotalSaleQuantity() {
		return totalSaleQuantity;
	}

	public void setTotalSaleQuantity(Integer totalSaleQuantity) {
		this.totalSaleQuantity = totalSaleQuantity;
	}

	public Integer getGoodCommentCount() {
		return goodCommentCount;
	}

	public void setGoodCommentCount(Integer goodCommentCount) {
		this.goodCommentCount = goodCommentCount;
	}

	public Integer getTotalCommentCount() {
		return totalCommentCount;
	}

	public void setTotalCommentCount(Integer totalCommentCount) {
		this.totalCommentCount = totalCommentCount;
	}

	public String getShopSalesAndCommentRate() {
		return shopSalesAndCommentRate;
	}

	public void setShopSalesAndCommentRate(String shopSalesAndCommentRate) {
		this.shopSalesAndCommentRate = shopSalesAndCommentRate;
	}

	public Integer getProductSaleCount90Day() {
		return productSaleCount90Day;
	}

	public void setProductSaleCount90Day(Integer productSaleCount90Day) {
		this.productSaleCount90Day = productSaleCount90Day;
	}

	public BigDecimal getProductApplauseRate() {
		return productApplauseRate;
	}

	public void setProductApplauseRate(BigDecimal productApplauseRate) {
		this.productApplauseRate = productApplauseRate;
	}
	
	
}
