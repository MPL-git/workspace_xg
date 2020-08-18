package com.jf.entity;
/**
  * @author  chenwf: 
  * @date 创建时间：2018年4月18日 下午4:05:39 
  * @version 1.0 
  * @parameter  
  * @return  
*/
public class SeckillBrandGroupCustom extends SeckillBrandGroup{
	/**
	 * 商品名称
	 */
	private String productName;
	
	/**
	 * 商品id
	 */
	private Integer productId;

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
	 * 商品id  
	 * @return productId 商品id  
	 */
	public Integer getProductId() {
		return productId;
	}
	


	/**  
	 * 商品id  
	 * @param productId 商品id  
	 */
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	
	
	
}
