package com.jf.entity;
/**
  * @author  chenwf: 
  * @date 创建时间：2018年9月4日 下午3:51:04 
  * @version 1.0 
  * @parameter  
  * @return  
*/
public class CombineOrderCustom extends CombineOrder{
	
	private String mobile;
	private String provinceName;
	private String cityName;
	private String countyName;
	private Integer invitationMemberId;
	private String merchantCode;

	public String getMerchantCode() {
		return merchantCode;
	}

	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}

	/**
	 * mobile  
	 * @return mobile mobile  
	 */
	public String getMobile() {
		return mobile;
	}
	

	/**  
	 * mobile  
	 * @param mobile mobile  
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


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


	public Integer getInvitationMemberId() {
		return invitationMemberId;
	}
	


	public void setInvitationMemberId(Integer invitationMemberId) {
		this.invitationMemberId = invitationMemberId;
	}
	
	
	
	
}
