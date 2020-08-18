package com.jf.entity;

import java.math.BigDecimal;


public class OrderPreferentialInfoCustom extends OrderPreferentialInfo{
	private String preferentialTypeDesc;
	private String belongDesc;
	private String rangDesc;
	private BigDecimal totalAmout;
	private String contentProduct;

	public String getPreferentialTypeDesc() {
		return preferentialTypeDesc;
	}
	public void setPreferentialTypeDesc(String preferentialTypeDesc) {
		this.preferentialTypeDesc = preferentialTypeDesc;
	}
	public String getBelongDesc() {
		return belongDesc;
	}
	public void setBelongDesc(String belongDesc) {
		this.belongDesc = belongDesc;
	}
	public String getRangDesc() {
		return rangDesc;
	}
	public void setRangDesc(String rangDesc) {
		this.rangDesc = rangDesc;
	}
    public BigDecimal getTotalAmout() {
        return totalAmout;
    }
    public void setTotalAmout(BigDecimal totalAmout) {
        this.totalAmout = totalAmout;
    }
	public String getContentProduct() {
		return contentProduct;
	}
	public void setContentProduct(String contentProduct) {
		this.contentProduct = contentProduct;
	}
}
