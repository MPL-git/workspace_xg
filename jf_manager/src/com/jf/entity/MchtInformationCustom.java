package com.jf.entity;


public class MchtInformationCustom extends MchtInformation {
    private  String mchtCode;
    private  String companyName;

    public String getMchtCode() {
        return mchtCode;
    }

    public void setMchtCode(String mchtCode) {
        this.mchtCode = mchtCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
