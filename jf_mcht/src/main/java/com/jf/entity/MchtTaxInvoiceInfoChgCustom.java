package com.jf.entity;


public class MchtTaxInvoiceInfoChgCustom extends MchtTaxInvoiceInfoChg {
	    
    
    private String taxTypeDesc;
    
    private String auditStatusDesc;
    
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