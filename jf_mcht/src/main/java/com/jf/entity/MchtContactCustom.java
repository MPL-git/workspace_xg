package com.jf.entity;


public class MchtContactCustom extends MchtContact{
	private String contactTypeDesc;
	private String provinceName;
	private String cityName;
	private String countyName;
	public String getContactTypeDesc() {
		return contactTypeDesc;
	}
	public void setContactTypeDesc(String contactTypeDesc) {
		this.contactTypeDesc = contactTypeDesc;
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
	
	
}