package com.jf.entity;

public class ShopDecorateInfoDraftCustom extends ShopDecorateInfoDraft{
    /**
     * 店铺头部背景图
     */
    private String banner;
    /**
     * 店铺名称
     */
    private String shopName;
    /**
     * 店铺logo
     */
    private String shopLogo;
    
	public String getBanner() {
		return banner;
	}
	
	public void setBanner(String banner) {
		this.banner = banner;
	}
	
	public String getShopName() {
		return shopName;
	}
	
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	
	public String getShopLogo() {
		return shopLogo;
	}
	
	public void setShopLogo(String shopLogo) {
		this.shopLogo = shopLogo;
	}
	

    
}