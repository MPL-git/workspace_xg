package com.jf.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductItemCustom extends ProductItem{
 private List<ProductPropValue> productPropValues;	
 private Map<Integer, String> propValuesMap;
 private String mchtType;

	public String getMchtType() {
		return mchtType;
	}

	public void setMchtType(String mchtType) {
		this.mchtType = mchtType;
	}

	public Map<Integer, String> getPropValuesMap() {
	if(propValuesMap==null){
		propValuesMap=new HashMap<Integer, String>();
		for(ProductPropValue productPropValue:productPropValues){
			propValuesMap.put(productPropValue.getPropId(), productPropValue.getPropValue());
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
 


}