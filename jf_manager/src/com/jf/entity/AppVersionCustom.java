package com.jf.entity;

public class AppVersionCustom extends AppVersion{
	private String appTypeDesc;
	private String sprChnlDesc;
	private String isEffectDesc;
	private String isMustDesc;
	private String staffName;
	private String fileName;

	public String getAppTypeDesc() {
		return appTypeDesc;
	}
	public void setAppTypeDesc(String appTypeDesc) {
		this.appTypeDesc = appTypeDesc;
	}
	public String getSprChnlDesc() {
		return sprChnlDesc;
	}
	public void setSprChnlDesc(String sprChnlDesc) {
		this.sprChnlDesc = sprChnlDesc;
	}
	public String getIsEffectDesc() {
		return isEffectDesc;
	}
	public void setIsEffectDesc(String isEffectDesc) {
		this.isEffectDesc = isEffectDesc;
	}
	public String getIsMustDesc() {
		return isMustDesc;
	}
	public void setIsMustDesc(String isMustDesc) {
		this.isMustDesc = isMustDesc;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
