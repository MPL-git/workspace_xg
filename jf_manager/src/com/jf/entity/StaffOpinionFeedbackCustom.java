package com.jf.entity;


public class StaffOpinionFeedbackCustom extends StaffOpinionFeedback{
	
	private String staffFeedbackContentPic;

	private Integer sridNum;
	
	private String staffName;

	public String getStaffFeedbackContentPic() {
		return staffFeedbackContentPic;
	}

	public void setStaffFeedbackContentPic(String staffFeedbackContentPic) {
		this.staffFeedbackContentPic = staffFeedbackContentPic;
	}

	public Integer getSridNum() {
		return sridNum;
	}

	public void setSridNum(Integer sridNum) {
		this.sridNum = sridNum;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
		
	
}