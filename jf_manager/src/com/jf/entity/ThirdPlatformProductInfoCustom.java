package com.jf.entity;

public class ThirdPlatformProductInfoCustom extends ThirdPlatformProductInfo{
	
	private String pic;
	private String productTypeName;
	private String fproductTypeName;
	private String gproductTypeName;
	private String status;
	private String productCode;
	
	private String channelName;
	private Integer channelId;
	
	
	
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	public Integer getChannelId() {
		return channelId;
	}
	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}
	
	
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getProductTypeName() {
		return productTypeName;
	}
	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getFproductTypeName() {
		return fproductTypeName;
	}
	public void setFproductTypeName(String fproductTypeName) {
		this.fproductTypeName = fproductTypeName;
	}
	public String getGproductTypeName() {
		return gproductTypeName;
	}
	public void setGproductTypeName(String gproductTypeName) {
		this.gproductTypeName = gproductTypeName;
	}
}