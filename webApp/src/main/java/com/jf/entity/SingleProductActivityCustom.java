package com.jf.entity;

import java.math.BigDecimal;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年9月29日 下午3:19:59 
  * @version 1.0 
  * @parameter  
  * @return  
*/
public class SingleProductActivityCustom extends SingleProductActivity{
	/**
	 * 商品主图
	 */
	private String productPic;
	
	/**
	 * 商品sku总数量
	 */
	private Integer stockSum;
	/**
	 * 商品sku库存
	 */
	private Integer stock;
	
	/**
	 * 商品名称
	 */
	private String productName;
	
	/**
	 * 醒购价
	 */
	private BigDecimal salePrice;
	
	/**
	 * 吊牌价
	 */
	private BigDecimal tagPrice;
	
	/**
	 * 销售量
	 */
	private Integer quantity;
	/**
	 * 冻结数量
	 */
	private Integer lockedAmount;
	
	/**
	 * 商品品类id
	 */
	private Integer productTypeId;
	
	/**
	 * 商品折扣
	 */
	private BigDecimal discount;
	
	/**
	 * 商品2级品类id
	 */
	private Integer productTypeIdTwo;
	
	/**
	 * 商品1级品类id
	 */
	private Integer productTypeIdOne;
	
	/**
	 * 商品品类名称
	 */
	private String productTypeName;
	
	/**
	 * 商品sku
	 */
	private String propValId;
	/**
	 * 商品sku别名
	 */
	private String alias;
	/**
	 * 品牌馆id
	 */
	private Integer brandGroupId;
	/**
	 * 品牌馆名称
	 */
	private String brandGroupName;
	/**
	 * 品牌馆入口图
	 */
	private String entryPic;
	/**
	 * 品牌馆头部海报图
	 */
	private String posterPic;
	/**
	 * 商品sku图片
	 */
	private String skuPic;
	/**
	 * 商品品牌名称
	 */
	private String brandName;
	/**
	 * 商品sku描述
	 */
	private String productPropDesc;
	/**
	 * 商品货号
	 */
	private String artNo;
	/**
	 * 是否单规格 \r\n0 否\r\n1 是
	 */
	private String isSingleProp;
	/**
	 * 砍价享免单需邀请人数
	 */
	private Integer inviteTimes;
	/**
	 * 每个人助力的固定金额
	 */
	private BigDecimal fixedAmount;
	/**
	 * 最多邀请人数（用于助力减价）
	 */
	private Integer maxInviteTimes;
	
	/**  
	 * 商品主图  
	 * @return productPic 商品主图  
	 */
	public String getProductPic() {
		return productPic;
	}
	

	/**  
	 * 商品主图  
	 * @param productPic 商品主图  
	 */
	public void setProductPic(String productPic) {
		this.productPic = productPic;
	}
	

	/**  
	 * 商品sku总数量  
	 * @return stockSum 商品sku总数量  
	 */
	public Integer getStockSum() {
		return stockSum;
	}
	

	/**  
	 * 商品sku总数量  
	 * @param stockSum 商品sku总数量  
	 */
	public void setStockSum(Integer stockSum) {
		this.stockSum = stockSum;
	}
	

	/**  
	 * 商品名称  
	 * @return productName 商品名称  
	 */
	public String getProductName() {
		return productName;
	}
	

	/**  
	 * 商品名称  
	 * @param productName 商品名称  
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}


	/**  
	 * 醒购价  
	 * @return salePrice 醒购价  
	 */
	public BigDecimal getSalePrice() {
		return salePrice;
	}
	
	/**  
	 * 醒购价  
	 * @param salePrice 醒购价  
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
	 * 销售量  
	 * @return quantity 销售量  
	 */
	public Integer getQuantity() {
		return quantity;
	}
	


	/**  
	 * 销售量  
	 * @param quantity 销售量  
	 */
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}


	/**  
	 * 商品品类id  
	 * @return productTypeId 商品品类id  
	 */
	public Integer getProductTypeId() {
		return productTypeId;
	}
	


	/**  
	 * 商品品类id  
	 * @param productTypeId 商品品类id  
	 */
	public void setProductTypeId(Integer productTypeId) {
		this.productTypeId = productTypeId;
	}


	/**  
	 * 商品折扣  
	 * @return discount 商品折扣  
	 */
	public BigDecimal getDiscount() {
		return discount;
	}
	


	/**  
	 * 商品折扣  
	 * @param discount 商品折扣  
	 */
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}


	/**  
	 * 销售量  
	 * @return unpaidQuantity 销售量  
	 */
	public Integer getLockedAmount() {
		return lockedAmount;
	}
	


	/**  
	 * 销售量  
	 * @param unpaidQuantity 销售量  
	 */
	public void setLockedAmount(Integer lockedAmount) {
		this.lockedAmount = lockedAmount;
	}


	/**  
	 * 商品2级品类id  
	 * @return productTypeIdTwo 商品2级品类id  
	 */
	public Integer getProductTypeIdTwo() {
		return productTypeIdTwo;
	}
	


	/**  
	 * 商品2级品类id  
	 * @param productTypeIdTwo 商品2级品类id  
	 */
	public void setProductTypeIdTwo(Integer productTypeIdTwo) {
		this.productTypeIdTwo = productTypeIdTwo;
	}
	


	/**  
	 * 商品1级品类id  
	 * @return productTypeIdOne 商品1级品类id  
	 */
	public Integer getProductTypeIdOne() {
		return productTypeIdOne;
	}
	


	/**  
	 * 商品1级品类id  
	 * @param productTypeIdOne 商品1级品类id  
	 */
	public void setProductTypeIdOne(Integer productTypeIdOne) {
		this.productTypeIdOne = productTypeIdOne;
	}
	


	/**  
	 * 商品品类名称  
	 * @return productTypeName 商品品类名称  
	 */
	public String getProductTypeName() {
		return productTypeName;
	}
	


	/**  
	 * 商品品类名称  
	 * @param productTypeName 商品品类名称  
	 */
	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}


	/**  
	 * 商品sku  
	 * @return propValId 商品sku  
	 */
	public String getPropValId() {
		return propValId;
	}
	


	/**  
	 * 商品sku  
	 * @param propValId 商品sku  
	 */
	public void setPropValId(String propValId) {
		this.propValId = propValId;
	}


	/**  
	 * 商品sku别名  
	 * @return alias 商品sku别名  
	 */
	public String getAlias() {
		return alias;
	}
	


	/**  
	 * 商品sku别名  
	 * @param alias 商品sku别名  
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}


	/**  
	 * 品牌馆id  
	 * @return brandGroupId 品牌馆id  
	 */
	public Integer getBrandGroupId() {
		return brandGroupId;
	}
	


	/**  
	 * 品牌馆id  
	 * @param brandGroupId 品牌馆id  
	 */
	public void setBrandGroupId(Integer brandGroupId) {
		this.brandGroupId = brandGroupId;
	}
	


	/**  
	 * 品牌馆入口图  
	 * @return entryPic 品牌馆入口图  
	 */
	public String getEntryPic() {
		return entryPic;
	}
	


	/**  
	 * 品牌馆入口图  
	 * @param entryPic 品牌馆入口图  
	 */
	public void setEntryPic(String entryPic) {
		this.entryPic = entryPic;
	}
	


	/**  
	 * 品牌馆头部海报图  
	 * @return posterPic 品牌馆头部海报图  
	 */
	public String getPosterPic() {
		return posterPic;
	}
	


	/**  
	 * 品牌馆头部海报图  
	 * @param posterPic 品牌馆头部海报图  
	 */
	public void setPosterPic(String posterPic) {
		this.posterPic = posterPic;
	}


	/**  
	 * 品牌馆名称  
	 * @return brandGroupName 品牌馆名称  
	 */
	public String getBrandGroupName() {
		return brandGroupName;
	}
	


	/**  
	 * 品牌馆名称  
	 * @param brandGroupName 品牌馆名称  
	 */
	public void setBrandGroupName(String brandGroupName) {
		this.brandGroupName = brandGroupName;
	}


	/**
	 * 是否自营（SPOP）
	 */
	private String isManageSelf;

	public String getIsManageSelf() {
		return isManageSelf;
	}

	public void setIsManageSelf(String isManageSelf) {
		this.isManageSelf = isManageSelf;
	}


	public String getSkuPic() {
		return skuPic;
	}
	


	public void setSkuPic(String skuPic) {
		this.skuPic = skuPic;
	}
	


	public String getBrandName() {
		return brandName;
	}
	


	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	


	public String getProductPropDesc() {
		return productPropDesc;
	}
	


	public void setProductPropDesc(String productPropDesc) {
		this.productPropDesc = productPropDesc;
	}
	


	public String getArtNo() {
		return artNo;
	}
	


	public void setArtNo(String artNo) {
		this.artNo = artNo;
	}


	public String getIsSingleProp() {
		return isSingleProp;
	}
	


	public void setIsSingleProp(String isSingleProp) {
		this.isSingleProp = isSingleProp;
	}


	public Integer getInviteTimes() {
		return inviteTimes;
	}
	


	public void setInviteTimes(Integer inviteTimes) {
		this.inviteTimes = inviteTimes;
	}


	/**  
	 * 商品sku库存  
	 * @return stock 商品sku库存  
	 */
	public Integer getStock() {
		return stock;
	}
	


	/**  
	 * 商品sku库存  
	 * @param stock 商品sku库存  
	 */
	public void setStock(Integer stock) {
		this.stock = stock;
	}


	public BigDecimal getFixedAmount() {
		return fixedAmount;
	}
	


	public void setFixedAmount(BigDecimal fixedAmount) {
		this.fixedAmount = fixedAmount;
	}


	public Integer getMaxInviteTimes() {
		return maxInviteTimes;
	}
	


	public void setMaxInviteTimes(Integer maxInviteTimes) {
		this.maxInviteTimes = maxInviteTimes;
	}
	
	
	
	
	
}
