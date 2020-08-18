package com.jf.entity;

import java.util.List;


public class ViolateComplainOrderCustom extends ViolateComplainOrder{
	private String staffName;
	private List<ComplainOrderPic> mchtComplainOrderPics;
	private List<ComplainOrderPic> platFormComplainOrderPics;
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public List<ComplainOrderPic> getMchtComplainOrderPics() {
		return mchtComplainOrderPics;
	}
	public void setMchtComplainOrderPics(
			List<ComplainOrderPic> mchtComplainOrderPics) {
		this.mchtComplainOrderPics = mchtComplainOrderPics;
	}
	public List<ComplainOrderPic> getPlatFormComplainOrderPics() {
		return platFormComplainOrderPics;
	}
	public void setPlatFormComplainOrderPics(
			List<ComplainOrderPic> platFormComplainOrderPics) {
		this.platFormComplainOrderPics = platFormComplainOrderPics;
	}
	
}
