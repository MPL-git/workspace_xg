package com.jf.entity;

import java.util.Date;


public class TaskCustom extends Task {
	private String TaskActivitySelectionId;
	private String coverPic;
	private String createName;
	private Integer decorateInfoId;
	
	private String yyAuditName;
	private String yyAuditRemarks;
	private Date yyAuditDate;
	private String fwAuditName;
	private String fwAuditRemarks;
	private Date fwAuditDate;
	
	public String getTaskActivitySelectionId() {
		return TaskActivitySelectionId;
	}
	public void setTaskActivitySelectionId(String taskActivitySelectionId) {
		TaskActivitySelectionId = taskActivitySelectionId;
	}
	public Integer getDecorateInfoId() {
		return decorateInfoId;
	}
	public void setDecorateInfoId(Integer decorateInfoId) {
		this.decorateInfoId = decorateInfoId;
	}
	public String getCoverPic() {
		return coverPic;
	}
	public void setCoverPic(String coverPic) {
		this.coverPic = coverPic;
	}
	public String getCreateName() {
		return createName;
	}
	public void setCreateName(String createName) {
		this.createName = createName;
	}
	public String getYyAuditName() {
		return yyAuditName;
	}
	public void setYyAuditName(String yyAuditName) {
		this.yyAuditName = yyAuditName;
	}
	public String getYyAuditRemarks() {
		return yyAuditRemarks;
	}
	public void setYyAuditRemarks(String yyAuditRemarks) {
		this.yyAuditRemarks = yyAuditRemarks;
	}
	public Date getYyAuditDate() {
		return yyAuditDate;
	}
	public void setYyAuditDate(Date yyAuditDate) {
		this.yyAuditDate = yyAuditDate;
	}
	public String getFwAuditName() {
		return fwAuditName;
	}
	public void setFwAuditName(String fwAuditName) {
		this.fwAuditName = fwAuditName;
	}
	public String getFwAuditRemarks() {
		return fwAuditRemarks;
	}
	public void setFwAuditRemarks(String fwAuditRemarks) {
		this.fwAuditRemarks = fwAuditRemarks;
	}
	public Date getFwAuditDate() {
		return fwAuditDate;
	}
	public void setFwAuditDate(Date fwAuditDate) {
		this.fwAuditDate = fwAuditDate;
	}
	
	
}
