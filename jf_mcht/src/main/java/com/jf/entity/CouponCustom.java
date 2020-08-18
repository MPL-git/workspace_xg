package com.jf.entity;

public class CouponCustom extends Coupon {
	
	private Integer useNum;
	private String pic;
	private String productName;
	private String productCode;
	private String productArtNo;
	private String isIntegralTurntableDesc;

	public String getIsIntegralTurntableDesc() {
		return isIntegralTurntableDesc;
	}

	public void setIsIntegralTurntableDesc(String isIntegralTurntableDesc) {
		this.isIntegralTurntableDesc = isIntegralTurntableDesc;
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

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}



	public String getProductArtNo() {
		return productArtNo;
	}

	public void setProductArtNo(String productArtNo) {
		this.productArtNo = productArtNo;
	}

	public Integer getUseNum() {
		return useNum;
	}

	public void setUseNum(Integer useNum) {
		this.useNum = useNum;
	}
	
}
