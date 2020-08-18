package com.jf.entity;

import java.math.BigDecimal;

public class ProductItemCustom extends ProductItem{
	
    private String productName;

    private String artNo;

    private String brandName;
    
    /**
     * 品牌id
     */
    private Integer brandId;
    
    /**
     * pop佣金比例
     */
    private BigDecimal feeRate;
    /**
     *一级类目id
     */
    private Integer productType1Id;
    /**
     *二级类目id
     */
    private Integer productType2Id;
    /**
     *三级类目id
     */
    private Integer productTypeId;
    /**
     *总库存（库存-冻结库存）
     */
    private Integer stockSum;
    /**
     *jb经销价
     */
    private BigDecimal sellingPrice;

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getArtNo() {
		return artNo;
	}

	public void setArtNo(String artNo) {
		this.artNo = artNo;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	/**  
	 * pop佣金比例  
	 * @return feeRate pop佣金比例  
	 */
	public BigDecimal getFeeRate() {
		return feeRate;
	}
	

	/**  
	 * pop佣金比例  
	 * @param feeRate pop佣金比例  
	 */
	public void setFeeRate(BigDecimal feeRate) {
		this.feeRate = feeRate;
	}

	/**  
	 * 品牌id  
	 * @return brandId 品牌id  
	 */
	public Integer getBrandId() {
		return brandId;
	}
	

	/**  
	 * 品牌id  
	 * @param brandId 品牌id  
	 */
	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

	public Integer getProductType1Id() {
		return productType1Id;
	}
	

	public void setProductType1Id(Integer productType1Id) {
		this.productType1Id = productType1Id;
	}

	public Integer getProductType2Id() {
		return productType2Id;
	}
	

	public void setProductType2Id(Integer productType2Id) {
		this.productType2Id = productType2Id;
	}
	

	public Integer getProductTypeId() {
		return productTypeId;
	}
	

	public void setProductTypeId(Integer productTypeId) {
		this.productTypeId = productTypeId;
	}

	public Integer getStockSum() {
		return stockSum;
	}
	

	public void setStockSum(Integer stockSum) {
		this.stockSum = stockSum;
	}
	

	public BigDecimal getSellingPrice() {
		return sellingPrice;
	}
	

	public void setSellingPrice(BigDecimal sellingPrice) {
		this.sellingPrice = sellingPrice;
	}
	
}