package com.jf.entity;


public class MchtTaxInvoiceInfoChgCustom extends MchtTaxInvoiceInfoChg {
	    
    private String companyName;
    
    private String taxTypeDesc;
    
    private String auditStatusDesc;
    
    private String fwContactName;
    
    private Integer fwStaffId;
    
    private String cwContactName;
    
    private Integer cwStaffId;
    
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

	public String getFwContactName() {
		return fwContactName;
	}

	public void setFwContactName(String fwContactName) {
		this.fwContactName = fwContactName;
	}

	public Integer getFwStaffId() {
		return fwStaffId;
	}

	public void setFwStaffId(Integer fwStaffId) {
		this.fwStaffId = fwStaffId;
	}

	public String getCwContactName() {
		return cwContactName;
	}

	public void setCwContactName(String cwContactName) {
		this.cwContactName = cwContactName;
	}

	public Integer getCwStaffId() {
		return cwStaffId;
	}

	public void setCwStaffId(Integer cwStaffId) {
		this.cwStaffId = cwStaffId;
	}
    
    
}