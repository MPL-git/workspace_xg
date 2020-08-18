package com.jf.entity;

public class MallCategoryItemCustom extends MallCategoryItem {

	private String statusDesc;
	private String productTypeName;
	private Integer productTypeCount;
	private Integer productBrandCount;

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public String getProductTypeName() {
		return productTypeName;
	}

	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}

	public Integer getProductTypeCount() {
		return productTypeCount;
	}

	public void setProductTypeCount(Integer productTypeCount) {
		this.productTypeCount = productTypeCount;
	}

	public Integer getProductBrandCount() {
		return productBrandCount;
	}

	public void setProductBrandCount(Integer productBrandCount) {
		this.productBrandCount = productBrandCount;
	}

}
