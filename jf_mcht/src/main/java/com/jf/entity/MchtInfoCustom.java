package com.jf.entity;

import java.util.Date;



public class MchtInfoCustom extends MchtInfo {
	
    private String mchtTypeDesc;
    
    private String isManageSelfDesc;
    
    private String statusDesc;
    
    private String isCompanyInfPerfectDesc;
    
    private String isFinancialPerfectDesc;
    
    private String mchtProductType;
    
    private String mchtProductBrand;
    
    private String contactProvinceName;
    
    private String contactCityName;
    
    private String contactCountyName;
    
    private String regFeeTypeDesc;
    
    private Integer mchtblpic;
    
    private Date yearlyInspectionDate;
    
	public Date getYearlyInspectionDate() {
		return yearlyInspectionDate;
	}

	public void setYearlyInspectionDate(Date yearlyInspectionDate) {
		this.yearlyInspectionDate = yearlyInspectionDate;
	}

	public Integer getMchtblpic() {
		return mchtblpic;
	}

	public void setMchtblpic(Integer mchtblpic) {
		this.mchtblpic = mchtblpic;
	}

	public String getMchtTypeDesc() {
        return mchtTypeDesc;
    }

    public void setMchtTypeDesc(String mchtTypeDesc) {
        this.mchtTypeDesc = mchtTypeDesc == null ? null : mchtTypeDesc.trim();
    }
    
    public String getIsManageSelfDesc() {
        return isManageSelfDesc;
    }

    public void setIsManageSelfDesc(String isManageSelfDesc) {
        this.isManageSelfDesc = isManageSelfDesc == null ? null : isManageSelfDesc.trim();
    }
    
    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc == null ? null : statusDesc.trim();
    }
    
    public String getIsCompanyInfPerfectDesc() {
        return isCompanyInfPerfectDesc;
    }

    public void setIsCompanyInfPerfectDesc(String isCompanyInfPerfectDesc) {
        this.isCompanyInfPerfectDesc = isCompanyInfPerfectDesc == null ? null : isCompanyInfPerfectDesc.trim();
    }
  
    public String getIsFinancialPerfectDesc() {
        return isFinancialPerfectDesc;
    }

    public void setIsFinancialPerfectDesc(String isFinancialPerfectDesc) {
        this.isFinancialPerfectDesc = isFinancialPerfectDesc == null ? null : isFinancialPerfectDesc.trim();
    }
    
    public String getMchtProductType() {
        return mchtProductType;
    }

    public void setMchtProductType(String mchtProductType) {
        this.mchtProductType = mchtProductType == null ? null : mchtProductType.trim();
    }
    
    public String getMchtProductBrand() {
        return mchtProductBrand;
    }

    public void setMchtProductBrand(String mchtProductBrand) {
        this.mchtProductBrand = mchtProductBrand == null ? null : mchtProductBrand.trim();
    }

	public String getContactProvinceName() {
		return contactProvinceName;
	}

	public void setContactProvinceName(String contactProvinceName) {
		this.contactProvinceName = contactProvinceName;
	}

	public String getContactCityName() {
		return contactCityName;
	}

	public void setContactCityName(String contactCityName) {
		this.contactCityName = contactCityName;
	}


	public String getContactCountyName() {
		return contactCountyName;
	}

	public void setContactCountyName(String contactCountyName) {
		this.contactCountyName = contactCountyName;
	}

	public String getRegFeeTypeDesc() {
		return regFeeTypeDesc;
	}

	public void setRegFeeTypeDesc(String regFeeTypeDesc) {
		this.regFeeTypeDesc = regFeeTypeDesc;
	}
    
    
}