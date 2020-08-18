package com.jf.entity;


public class MchtTaxInvoiceInfoCustom extends MchtTaxInvoiceInfo {
	    
    private String companyName;
    
    private String taxTypeDesc;
    
    private String auditStatusDesc;
    
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }
    
    public String getTaxTypeDesc() {
        return taxTypeDesc;
    }

    public void setTaxTypeDesc(String taxTypeDesc) {
        this.taxTypeDesc = taxTypeDesc == null ? null : taxTypeDesc.trim();
    }
    
    public String getAuditStatusDesc() {
        return auditStatusDesc;
    }

    public void setAuditStatusDesc(String auditStatusDesc) {
        this.auditStatusDesc = auditStatusDesc == null ? null : auditStatusDesc.trim();
    }
}