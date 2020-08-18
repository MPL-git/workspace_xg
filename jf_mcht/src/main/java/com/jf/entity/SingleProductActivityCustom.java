package com.jf.entity;


public class SingleProductActivityCustom extends SingleProductActivity{
	private String typeDesc;
	private String pic;
	private String productName;
	private String productCode;
	private String brandName;
	private String artNo;
	private Integer activityStock;
	private String auditStatusDesc;
	private String rejectReason;
	private Integer saleCount;
	public String getTypeDesc() {
		return typeDesc;
	}
	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getArtNo() {
		return artNo;
	}
	public void setArtNo(String artNo) {
		this.artNo = artNo;
	}
	public Integer getActivityStock() {
		return activityStock;
	}
	public void setActivityStock(Integer activityStock) {
		this.activityStock = activityStock;
	}
	public String getAuditStatusDesc() {
		return auditStatusDesc;
	}
	public void setAuditStatusDesc(String auditStatusDesc) {
		this.auditStatusDesc = auditStatusDesc;
	}
	public String getRejectReason() {
		return rejectReason;
	}
	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public Integer getSaleCount() {
		return saleCount;
	}
	public void setSaleCount(Integer saleCount) {
		this.saleCount = saleCount;
	}
	
}
