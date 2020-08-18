package com.jf.entity;

import java.util.Date;



public class PropertyRightProsecutionCustomExample extends PropertyRightProsecutionExample{
	
    private Integer staffId;
    private String mobile;
    private Date prosecutionEndDate;
    private Integer listFlag;//是否列表展示（0：否、1：是）
    

	public Integer getStaffId() {
		return staffId;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Date getProsecutionEndDate() {
		return prosecutionEndDate;
	}

	public void setProsecutionEndDate(Date prosecutionEndDate) {
		this.prosecutionEndDate = prosecutionEndDate;
	}

	public Integer getListFlag() {
		return listFlag;
	}

	public void setListFlag(Integer listFlag) {
		this.listFlag = listFlag;
	}

	@Override
	public PropertyRightProsecutionCustomCriteria createCriteria() {
		PropertyRightProsecutionCustomCriteria criteria = new PropertyRightProsecutionCustomCriteria();
		if(oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}
	
	public class PropertyRightProsecutionCustomCriteria extends PropertyRightProsecutionExample.Criteria {
	}
}