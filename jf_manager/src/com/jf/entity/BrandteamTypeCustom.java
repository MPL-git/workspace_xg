package com.jf.entity;


public class BrandteamTypeCustom extends BrandteamType{
	
    private String productTypeName;
	
	private String previewUrl;

	public String getProductTypeName() {
		return productTypeName;
	}

	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}

	public String getPreviewUrl() {
		return previewUrl;
	}

	public void setPreviewUrl(String previewUrl) {
		this.previewUrl = previewUrl;
	}
}