package com.jf.entity;

public class CooperationChangeApplyCustom extends CooperationChangeApply{
	private String changeApplyType;
	
	private String sendStatusDesc;
	
	private String archiveStatusDesc;

	private String mchtCode;
	
	private String companyName;
	
	private String productTypeName;

	public String getChangeApplyType() {
		return changeApplyType;
	}

	public void setChangeApplyType(String changeApplyType) {
		this.changeApplyType = changeApplyType;
	}

	public String getSendStatusDesc() {
		return sendStatusDesc;
	}

	public void setSendStatusDesc(String sendStatusDesc) {
		this.sendStatusDesc = sendStatusDesc;
	}

	public String getArchiveStatusDesc() {
		return archiveStatusDesc;
	}

	public void setArchiveStatusDesc(String archiveStatusDesc) {
		this.archiveStatusDesc = archiveStatusDesc;
	}

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

	public String getProductTypeName() {
		return productTypeName;
	}

	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}
	
	
}