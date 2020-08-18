package com.jf.entity;

public class MchContactCustom extends MchtContact{
	
	private String mchtCode;
	private String settledType;
	private String companyName;
	private String shopName;
	private String platformContactName;
	private String countyName;
	private String provinceName;
	private String cityName;
	private Integer fwStaffId;
	private Integer fwAssistantStaffId;

	public String getCountyName() {
		return countyName;
	}
	public void setCountyName(String countyName) {
		this.countyName = countyName;
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
	public Integer getFwStaffId() {
		return fwStaffId;
	}
	public void setFwStaffId(Integer fwStaffId) {
		this.fwStaffId = fwStaffId;
	}
	public Integer getFwAssistantStaffId() {
		return fwAssistantStaffId;
	}
	public void setFwAssistantStaffId(Integer fwAssistantStaffId) {
		this.fwAssistantStaffId = fwAssistantStaffId;
	}
	public String getMchtCode() {
		return mchtCode;
	}
	public void setMchtCode(String mchtCode) {
		this.mchtCode = mchtCode;
	}
	public String getSettledType() {
		return settledType;
	}
	public void setSettledType(String settledType) {
		this.settledType = settledType;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getPlatformContactName() {
		return platformContactName;
	}
	public void setPlatformContactName(String platformContactName) {
		this.platformContactName = platformContactName;
	}
	
	/*private MchtContact mcContact;*/
	/*private String contactTypes;
	private String data;*/
	/*public MchtContact getMcContact() {
		return mcContact;
	}
	public void setMcContact(MchtContact mcContact) {
		this.mcContact = mcContact;
	}*/
	/*public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getContactTypes() {
		return contactTypes;
	}
	public void setContactTypes(String contactTypes) {
		this.contactTypes = contactTypes;
	}*/
	
}
