package com.jf.entity;
/**
  * @author  chenwf: 
  * @date 创建时间：2017年4月21日 上午10:07:02 
  * @version 1.0 
  * @parameter  
  * @return  
*/
public class MemberFootprintCustom extends MemberFootprint{
	private double tagPrice;

	private double salePrice;
	
	private String pic;
	
	private String name;

	private double saleOrMallPrice;
	private String saleType;

	public double getSaleOrMallPrice() {
		return saleOrMallPrice;
	}

	public void setSaleOrMallPrice(double saleOrMallPrice) {
		this.saleOrMallPrice = saleOrMallPrice;
	}

	public String getSaleType() {
		return saleType;
	}

	public void setSaleType(String saleType) {
		this.saleType = saleType;
	}

	/**
	 * tagPrice  
	 * @return tagPrice tagPrice  
	 */
	public double getTagPrice() {
		return tagPrice;
	}
	

	/**  
	 * tagPrice  
	 * @param tagPrice tagPrice  
	 */
	public void setTagPrice(double tagPrice) {
		this.tagPrice = tagPrice;
	}
	

	/**  
	 * salePrice  
	 * @return salePrice salePrice  
	 */
	public double getSalePrice() {
		return salePrice;
	}
	

	/**  
	 * salePrice  
	 * @param salePrice salePrice  
	 */
	public void setSalePrice(double salePrice) {
		this.salePrice = salePrice;
	}
	

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
