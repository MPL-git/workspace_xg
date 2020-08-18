package com.jf.entity;

public class MchtProductbrandInfoEntity{

	
	private MchtProductBrandCustom mchtP;
	//给平台授权期限
	private String platformAuthExpDate;
	//资质到期日期
	private String aptitudeExpDate;
	//创建日期
	private String createDate;

	public String getPlatformAuthExpDate() {
		return platformAuthExpDate;
	}

	public void setPlatformAuthExpDate(String platformAuthExpDate) {
		this.platformAuthExpDate = platformAuthExpDate;
	}

	public String getAptitudeExpDate() {
		return aptitudeExpDate;
	}

	public void setAptitudeExpDate(String aptitudeExpDate) {
		this.aptitudeExpDate = aptitudeExpDate;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public MchtProductBrandCustom getMchtP() {
		return mchtP;
	}

	public void setMchtP(MchtProductBrandCustom mchtP) {
		this.mchtP = mchtP;
	}
	
}
