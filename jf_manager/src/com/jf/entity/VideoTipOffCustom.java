package com.jf.entity;


import java.util.List;

public class VideoTipOffCustom extends VideoTipOff{
	
    private String mchtName;
	private String mchtCode;
	private String videoStatus;
	private String videoDescription;
	private String videoTitle;
	private String videoStatusDesc;
	private List<VideoAuthorizedAccessories> videoAuthorizedAccessoriesList;

	public List<VideoAuthorizedAccessories> getVideoAuthorizedAccessoriesList() {
		return videoAuthorizedAccessoriesList;
	}

	public String getVideoStatusDesc() {
		return videoStatusDesc;
	}

	public void setVideoStatusDesc(String videoStatusDesc) {
		this.videoStatusDesc = videoStatusDesc;
	}

	public void setVideoAuthorizedAccessoriesList(List<VideoAuthorizedAccessories> videoAuthorizedAccessoriesList) {
		this.videoAuthorizedAccessoriesList = videoAuthorizedAccessoriesList;
	}
	public String getMchtName() {
		return mchtName;
	}
	public void setMchtName(String mchtName) {
		this.mchtName = mchtName;
	}
	public String getMchtCode() {
		return mchtCode;
	}
	public void setMchtCode(String mchtCode) {
		this.mchtCode = mchtCode;
	}
	public String getVideoStatus() {
		return videoStatus;
	}
	public void setVideoStatus(String videoStatus) {
		this.videoStatus = videoStatus;
	}
	public String getVideoDescription() {
		return videoDescription;
	}
	public void setVideoDescription(String videoDescription) {
		this.videoDescription = videoDescription;
	}
	public String getVideoTitle() {
		return videoTitle;
	}
	public void setVideoTitle(String videoTitle) {
		this.videoTitle = videoTitle;
	}
		
}