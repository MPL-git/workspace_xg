package com.jf.entity;

public class SportTeamCustom extends SportTeam {

	private String resultDesc;
	private String auditStatusDesc;
	private String createStaffName;
	private Integer sumSport;
	private Integer sumCount;

	public String getResultDesc() {
		return resultDesc;
	}

	public void setResultDesc(String resultDesc) {
		this.resultDesc = resultDesc;
	}

	public String getAuditStatusDesc() {
		return auditStatusDesc;
	}

	public void setAuditStatusDesc(String auditStatusDesc) {
		this.auditStatusDesc = auditStatusDesc;
	}

	public String getCreateStaffName() {
		return createStaffName;
	}

	public void setCreateStaffName(String createStaffName) {
		this.createStaffName = createStaffName;
	}

	public Integer getSumSport() {
		return sumSport;
	}

	public void setSumSport(Integer sumSport) {
		this.sumSport = sumSport;
	}

	public Integer getSumCount() {
		return sumCount;
	}

	public void setSumCount(Integer sumCount) {
		this.sumCount = sumCount;
	}

}
