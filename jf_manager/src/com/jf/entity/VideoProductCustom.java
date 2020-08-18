package com.jf.entity;


public class VideoProductCustom extends VideoProduct{
	private String pic;//主图
	private String productCode;//商品CODE
	private String productName;//商品名称
	
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
}