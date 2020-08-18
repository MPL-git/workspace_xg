package com.jf.entity;

import java.util.List;


public class ProductBrandCustom extends ProductBrand{
    
	//品牌所属品类
	private List<ProductType> productTypes;
	//状态名称
	private String statusName;
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