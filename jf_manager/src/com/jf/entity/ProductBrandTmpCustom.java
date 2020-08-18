package com.jf.entity;

import java.util.List;

public class ProductBrandTmpCustom {
	private ProductBrandTmp productBrandTmp;
	private String statusName;
	//品牌所属品类
	private List<ProductType> productTypes;
	
	private List<BrandTmkPicTmp> brandTmkPicTmps;

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

	public ProductBrandTmp getProductBrandTmp() {
		return productBrandTmp;
	}

	public void setProductBrandTmp(ProductBrandTmp productBrandTmp) {
		this.productBrandTmp = productBrandTmp;
	}

	public List<BrandTmkPicTmp> getBrandTmkPicTmps() {
		return brandTmkPicTmps;
	}

	public void setBrandTmkPicTmps(List<BrandTmkPicTmp> brandTmkPicTmps) {
		this.brandTmkPicTmps = brandTmkPicTmps;
	}

}