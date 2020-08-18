package com.jf.entity;

import java.math.BigDecimal;

public class ProvinceFreightCustom extends ProvinceFreight{
	
	private BigDecimal productFreight;
	
	private String freightTemplateName;
	
	private String provinceName;
	/**
	 * 该模板总金额
	 */
	private BigDecimal freightTemplateTotalFreight;
	
	public BigDecimal getProductFreight() {
		return productFreight;
	}
	

	public void setProductFreight(BigDecimal productFreight) {
		this.productFreight = productFreight;
	}


	public String getFreightTemplateName() {
		return freightTemplateName;
	}
	


	public void setFreightTemplateName(String freightTemplateName) {
		this.freightTemplateName = freightTemplateName;
	}


	public BigDecimal getFreightTemplateTotalFreight() {
		return freightTemplateTotalFreight;
	}
	


	public void setFreightTemplateTotalFreight(BigDecimal freightTemplateTotalFreight) {
		this.freightTemplateTotalFreight = freightTemplateTotalFreight;
	}


	public String getProvinceName() {
		return provinceName;
	}
	


	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	
	
}
