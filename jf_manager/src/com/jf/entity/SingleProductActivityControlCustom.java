package com.jf.entity;

public class SingleProductActivityControlCustom extends SingleProductActivityControl{
	private String typeDesc;
	
	private String productTypeNames;
	
	private String showToMchtCodes;
	
	private String hideToMchtCodes;

	public String getProductTypeNames() {
		return productTypeNames;
	}

	public void setProductTypeNames(String productTypeNames) {
		this.productTypeNames = productTypeNames;
	}

	public String getShowToMchtCodes() {
		return showToMchtCodes;
	}

	public void setShowToMchtCodes(String showToMchtCodes) {
		this.showToMchtCodes = showToMchtCodes;
	}

	public String getHideToMchtCodes() {
		return hideToMchtCodes;
	}

	public void setHideToMchtCodes(String hideToMchtCodes) {
		this.hideToMchtCodes = hideToMchtCodes;
	}

	public String getTypeDesc() {
		return typeDesc;
	}

	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}
	
}