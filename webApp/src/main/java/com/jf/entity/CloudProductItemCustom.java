package com.jf.entity;

import java.math.BigDecimal;

public class CloudProductItemCustom extends CloudProductItem{
	private Integer spOfficeId;
	private BigDecimal sellingPrice;

	public Integer getSpOfficeId() {
		return spOfficeId;
	}
	

	public void setSpOfficeId(Integer spOfficeId) {
		this.spOfficeId = spOfficeId;
	}


	public BigDecimal getSellingPrice() {
		return sellingPrice;
	}
	


	public void setSellingPrice(BigDecimal sellingPrice) {
		this.sellingPrice = sellingPrice;
	}
	
	
	
}
