package com.jf.entity;
/**
  * @author  chenwf: 
  * @date 创建时间：2017年4月20日 下午4:40:38 
  * @version 1.0 
  * @parameter  
  * @return  
*/
public class MemberAddressCustom extends MemberAddress{
	
	private String provinceName;
	
	private String cityName;

	private String countyName;
	/**
	 * 收货地址全称
	 */
	private String fullAddressName;

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCountyName() {
		return countyName;
	}

	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}

	/**  
	 * 收货地址全称  
	 * @return fullAddressName 收货地址全称  
	 */
	public String getFullAddressName() {
		return fullAddressName;
	}
	

	/**  
	 * 收货地址全称  
	 * @param fullAddressName 收货地址全称  
	 */
	public void setFullAddressName(String fullAddressName) {
		this.fullAddressName = fullAddressName;
	}
	
}
