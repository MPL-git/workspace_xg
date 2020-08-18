package com.jf.entity;


public class ActivityCustom extends Activity{
	private String productBrandName;
	private String statusDesc;
	private int acceptProductCount;
	private int refuseProductCount;
	private int saleQuantity;
	
	public String getProductBrandName() {
		return productBrandName;
	}
	public void setProductBrandName(String productBrandName) {
		this.productBrandName = productBrandName;
	}
	public String getStatusDesc() {
		return statusDesc;
	}
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	public int getAcceptProductCount() {
		return acceptProductCount;
	}
	public void setAcceptProductCount(int acceptProductCount) {
		this.acceptProductCount = acceptProductCount;
	}
	public int getRefuseProductCount() {
		return refuseProductCount;
	}
	public void setRefuseProductCount(int refuseProductCount) {
		this.refuseProductCount = refuseProductCount;
	}
	public int getSaleQuantity() {
		return saleQuantity;
	}
	public void setSaleQuantity(int saleQuantity) {
		this.saleQuantity = saleQuantity;
	}
}
