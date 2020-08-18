package com.jf.entity;

import java.util.List;

public class SourceProductTypeBannerCustom extends SourceProductTypeBanner {
	
	private String linkTypeDesc;//banner类型名称
	private String ids;
	private List<BannerRecommendProductCustom> bannerList;
	private String productTypeName;
	private String mchtCode;
	private String brandteamtypeName;
	private String productCode;
	private String productTypeNames;
/*	private BigDecimal tagPriceMin;//吊牌价
	private BigDecimal tagPriceMax;
	private BigDecimal salePriceMin;//活动价格
	private BigDecimal salePriceMax;*/
	
	public String getLinkTypeDesc() {
		return linkTypeDesc;
	}
	public void setLinkTypeDesc(String linkTypeDesc) {
		this.linkTypeDesc = linkTypeDesc;
	}
	
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public List<BannerRecommendProductCustom> getBannerList() {
		return bannerList;
	}
	public void setBannerList(List<BannerRecommendProductCustom> bannerList) {
		this.bannerList = bannerList;
	}
	public String getProductTypeName() {
		return productTypeName;
	}
	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}
	public String getMchtCode() {
		return mchtCode;
	}
	public void setMchtCode(String mchtCode) {
		this.mchtCode = mchtCode;
	}
	public String getBrandteamtypeName() {
		return brandteamtypeName;
	}
	public void setBrandteamtypeName(String brandteamtypeName) {
		this.brandteamtypeName = brandteamtypeName;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductTypeNames() {
		return productTypeNames;
	}
	public void setProductTypeNames(String productTypeNames) {
		this.productTypeNames = productTypeNames;
	}
	
}
