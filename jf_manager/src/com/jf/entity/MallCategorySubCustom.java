package com.jf.entity;



public class MallCategorySubCustom extends MallCategorySub{
	private String productTypeName;
	
	private String brandName;
	
	private String secondProductTypeName;
	
	private String statusDesc;

	public String getProductTypeName() {
		return productTypeName;
	}

	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getSecondProductTypeName() {
		return secondProductTypeName;
	}

	public void setSecondProductTypeName(String secondProductTypeName) {
		this.secondProductTypeName = secondProductTypeName;
	}
	
	
}
