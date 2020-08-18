package com.jf.entity;

import java.math.BigDecimal;

public class ProductCustom extends Product{
	
    private String propValId;
    
    private Integer lockedAmount;
    
    private Integer stock;
    /**
     * 销售价
     */
    private BigDecimal salePrice;
    /**
     * 吊牌价
     */
    private BigDecimal tagPrice;
    
    private String propName;
    
    private String propValue;
    
    private Integer propValueId;
    
    private String pic;
    
    private Integer propId;
    
    /**
     * 品牌名
     */
    private String brandName;
    /**
     * 商品购买数量
     */
    private Integer productBuyNum;

	public String getPropValId() {
		return propValId;
	}

	public void setPropValId(String propValId) {
		this.propValId = propValId;
	}

	public Integer getLockedAmount() {
		return lockedAmount;
	}

	public void setLockedAmount(Integer lockedAmount) {
		this.lockedAmount = lockedAmount;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public String getPropName() {
		return propName;
	}

	public void setPropName(String propName) {
		this.propName = propName;
	}

	public String getPropValue() {
		return propValue;
	}

	public void setPropValue(String propValue) {
		this.propValue = propValue;
	}

	public Integer getPropValueId() {
		return propValueId;
	}

	public void setPropValueId(Integer propValueId) {
		this.propValueId = propValueId;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public Integer getPropId() {
		return propId;
	}

	public void setPropId(Integer propId) {
		this.propId = propId;
	}

	/**  
	 * 品牌名  
	 * @return brandName 品牌名  
	 */
	public String getBrandName() {
		return brandName;
	}
	

	/**  
	 * 品牌名  
	 * @param brandName 品牌名  
	 */
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	/**  
	 * 销售价  
	 * @return salePrice 销售价  
	 */
	public BigDecimal getSalePrice() {
		return salePrice;
	}
	

	/**  
	 * 销售价  
	 * @param salePrice 销售价  
	 */
	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}
	

	/**  
	 * 吊牌价  
	 * @return tagPrice 吊牌价  
	 */
	public BigDecimal getTagPrice() {
		return tagPrice;
	}
	

	/**  
	 * 吊牌价  
	 * @param tagPrice 吊牌价  
	 */
	public void setTagPrice(BigDecimal tagPrice) {
		this.tagPrice = tagPrice;
	}

	/**  
	 * 商品购买数量  
	 * @return productBuyNum 商品购买数量  
	 */
	public Integer getProductBuyNum() {
		return productBuyNum;
	}
	

	/**  
	 * 商品购买数量  
	 * @param productBuyNum 商品购买数量  
	 */
	public void setProductBuyNum(Integer productBuyNum) {
		this.productBuyNum = productBuyNum;
	}
    
}