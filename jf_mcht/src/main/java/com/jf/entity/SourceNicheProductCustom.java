package com.jf.entity;

import java.math.BigDecimal;

public class SourceNicheProductCustom extends SourceNicheProduct {

	
	private String pName; //商品名称
	private String pArtNo;//商品货号
	private String pic;//商品图片
	private String pCode;//商品ID
	private String pRecommendRemarks;//推荐文案
	private BigDecimal salePriceMin;
	private BigDecimal salePriceMax;;//活动价
	private BigDecimal mallPriceMin;
	private BigDecimal mallPriceMax;//商城价
	private BigDecimal svipDiscount;//SVP折扣
	private int pStock;//库存
	private String pStatus;//上架状态
	
	
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}

	public String getpArtNo() {
		return pArtNo;
	}
	public void setpArtNo(String pArtNo) {
		this.pArtNo = pArtNo;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getpCode() {
		return pCode;
	}
	public void setpCode(String pCode) {
		this.pCode = pCode;
	}
	public String getpRecommendRemarks() {
		return pRecommendRemarks;
	}
	public void setpRecommendRemarks(String pRecommendRemarks) {
		this.pRecommendRemarks = pRecommendRemarks;
	}
	public BigDecimal getSalePriceMin() {
		return salePriceMin;
	}
	public void setSalePriceMin(BigDecimal salePriceMin) {
		this.salePriceMin = salePriceMin;
	}
	public BigDecimal getSalePriceMax() {
		return salePriceMax;
	}
	public void setSalePriceMax(BigDecimal salePriceMax) {
		this.salePriceMax = salePriceMax;
	}
	

	public BigDecimal getMallPriceMin() {
		return mallPriceMin;
	}
	public void setMallPriceMin(BigDecimal mallPriceMin) {
		this.mallPriceMin = mallPriceMin;
	}
	public BigDecimal getMallPriceMax() {
		return mallPriceMax;
	}
	public void setMallPriceMax(BigDecimal mallPriceMax) {
		this.mallPriceMax = mallPriceMax;
	}
	public BigDecimal getSvipDiscount() {
		return svipDiscount;
	}
	public void setSvipDiscount(BigDecimal svipDiscount) {
		this.svipDiscount = svipDiscount;
	}
	public int getpStock() {
		return pStock;
	}
	public void setpStock(int pStock) {
		this.pStock = pStock;
	}
	public String getpStatus() {
		return pStatus;
	}
	public void setpStatus(String pStatus) {
		this.pStatus = pStatus;
	}
	
	

}
