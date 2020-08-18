package com.jf.entity;



public class PropertyRightComplainCustomExample extends PropertyRightComplainExample{
	
	private Integer rightAppealId;
    private String appealType;
    private String relevantValue;
    private Integer staffId;
    private String companyName;
    
	public Integer getRightAppealId() {
		return rightAppealId;
	}

	public void setRightAppealId(Integer rightAppealId) {
		this.rightAppealId = rightAppealId;
	}

	public String getAppealType() {
		return appealType;
	}

	public void setAppealType(String appealType) {
		this.appealType = appealType;
	}

	public String getRelevantValue() {
		return relevantValue;
	}

	public void setRelevantValue(String relevantValue) {
		this.relevantValue = relevantValue;
	}

	public Integer getStaffId() {
		return staffId;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Override
	public PropertyRightComplainCustomCriteria createCriteria() {
		PropertyRightComplainCustomCriteria criteria = new PropertyRightComplainCustomCriteria();
		if(oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}
	
	public class PropertyRightComplainCustomCriteria extends PropertyRightComplainExample.Criteria {
	}
}