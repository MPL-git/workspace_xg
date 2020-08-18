package com.jf.entity;

import java.util.List;


public class ViolateComplainOrderCustom extends ViolateComplainOrder{
	private List<ComplainOrderPic> complainOrderPics;
	private List<ComplainOrderPic> platformPics;

	public List<ComplainOrderPic> getComplainOrderPics() {
		return complainOrderPics;
	}

	public void setComplainOrderPics(List<ComplainOrderPic> complainOrderPics) {
		this.complainOrderPics = complainOrderPics;
	}

	public List<ComplainOrderPic> getPlatformPics() {
		return platformPics;
	}

	public void setPlatformPics(List<ComplainOrderPic> platformPics) {
		this.platformPics = platformPics;
	}
	
}
