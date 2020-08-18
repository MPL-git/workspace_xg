package com.jf.entity;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class ProductItemCustom extends ProductItem {
	private List<ProductPropValue> productPropValues;
	private Map<Integer, String> propValuesMap;
	private String productName;
	private String productBrandName;
	private String artNo;
	private String propDesc;
	
	private String productCode;
	private String productStatus;
	private String productNamesku;
	private String productbrandName;
	private String productArtNo;
	private String propValueSku;

	//尺码
	private String sizeValue;
	//颜色
	private String colorValue;

	public String getSizeValue() {
		return sizeValue;
	}

	public void setSizeValue(String sizeValue) {
		this.sizeValue = sizeValue;
	}

	public String getColorValue() {
		return colorValue;
	}

	public void setColorValue(String colorValue) {
		this.colorValue = colorValue;
	}

	public Map<Integer, String> getPropValuesMap() {
		if (propValuesMap == null) {
			propValuesMap = new HashMap<Integer, String>();
			for (ProductPropValue productPropValue : productPropValues) {
				propValuesMap.put(productPropValue.getPropId(),
						productPropValue.getPropValue());
			}
		}
		return propValuesMap;
	}

	public void setPropValuesMap(Map<Integer, String> propValuesMap) {
		this.propValuesMap = propValuesMap;
	}

	public List<ProductPropValue> getProductPropValues() {
		return productPropValues;
	}

	public void setProductPropValues(List<ProductPropValue> productPropValues) {
		this.productPropValues = productPropValues;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductBrandName() {
		return productBrandName;
	}

	public void setProductBrandName(String productBrandName) {
		this.productBrandName = productBrandName;
	}

	public String getArtNo() {
		return artNo;
	}

	public void setArtNo(String artNo) {
		this.artNo = artNo;
	}

	public String getPropDesc() {
		return propDesc;
	}

	public void setPropDesc(String propDesc) {
		this.propDesc = propDesc;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductStatus() {
		return productStatus;
	}

	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}

	public String getProductNamesku() {
		return productNamesku;
	}

	public void setProductNamesku(String productNamesku) {
		this.productNamesku = productNamesku;
	}

	public String getProductbrandName() {
		return productbrandName;
	}

	public void setProductbrandName(String productbrandName) {
		this.productbrandName = productbrandName;
	}

	public String getProductArtNo() {
		return productArtNo;
	}

	public void setProductArtNo(String productArtNo) {
		this.productArtNo = productArtNo;
	}

	public String getPropValueSku() {
		return propValueSku;
	}

	public void setPropValueSku(String propValueSku) {
		this.propValueSku = propValueSku;
	}
	
}