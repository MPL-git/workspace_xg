package com.jf.entity;


public class MchtDepositCustom extends MchtDeposit {
	
    private String mchtCode;
    private String companyName;
    private String shortName;
    private String shopName;
    private String mchtStatusDesc;

    public String getMchtCode() {
        return mchtCode;
    }

    public void setMchtCode(String mchtCode) {
        this.mchtCode = mchtCode == null ? null : mchtCode.trim();
    }
    
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getMchtStatusDesc() {
		return mchtStatusDesc;
	}

	public void setMchtStatusDesc(String mchtStatusDesc) {
		this.mchtStatusDesc = mchtStatusDesc;
	}
    
}