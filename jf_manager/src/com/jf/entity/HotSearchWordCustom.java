package com.jf.entity;

public class HotSearchWordCustom extends HotSearchWord {
	private String sourceDesc;
	
	private String statusDesc;
	
	private Integer searchLogCount;

	public String getSourceDesc() {
		return sourceDesc;
	}

	public void setSourceDesc(String sourceDesc) {
		this.sourceDesc = sourceDesc;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public Integer getSearchLogCount() {
		return searchLogCount;
	}

	public void setSearchLogCount(Integer searchLogCount) {
		this.searchLogCount = searchLogCount;
	}

}