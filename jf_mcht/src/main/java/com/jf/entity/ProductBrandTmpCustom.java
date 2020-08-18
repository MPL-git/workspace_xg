package com.jf.entity;

import java.util.List;

public class ProductBrandTmpCustom extends ProductBrandTmp{
	private String statusName;
	//品牌所属品类
	private List<ProductType> productTypes;
	

	public List<ProductType> getProductTypes() {
		return productTypes;
	}

	public void setProductTypes(List<ProductType> productTypes) {
		this.productTypes = productTypes;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

}