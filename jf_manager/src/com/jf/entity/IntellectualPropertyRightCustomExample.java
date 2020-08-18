package com.jf.entity;


public class IntellectualPropertyRightCustomExample extends IntellectualPropertyRightExample{
	
	@Override
	public IntellectualPropertyRightCustomCriteria createCriteria() {
		IntellectualPropertyRightCustomCriteria criteria = new IntellectualPropertyRightCustomCriteria();
		if(oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}
	
	public class IntellectualPropertyRightCustomCriteria extends IntellectualPropertyRightExample.Criteria {
		
	}
}