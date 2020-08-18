package com.jf.entity;



public class ViolatePunishStandardCustom extends ViolatePunishStandard{
	private String violateTypeDesc;
	private String violateActionDesc;
	private String complainFlagDesc;
	
	public String getViolateTypeDesc() {
		return violateTypeDesc;
	}
	public void setViolateTypeDesc(String violateTypeDesc) {
		this.violateTypeDesc = violateTypeDesc;
	}
	public String getViolateActionDesc() {
		return violateActionDesc;
	}
	public void setViolateActionDesc(String violateActionDesc) {
		this.violateActionDesc = violateActionDesc;
	}
	public String getComplainFlagDesc() {
		return complainFlagDesc;
	}
	public void setComplainFlagDesc(String complainFlagDesc) {
		this.complainFlagDesc = complainFlagDesc;
	}
	
}
