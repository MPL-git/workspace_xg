package com.jf.entity;


public class StaffReplyCustomExample extends StaffReplyExample {

	@Override
	public StaffReplyCustomCriteria createCriteria() {
		StaffReplyCustomCriteria criteria = new StaffReplyCustomCriteria();
		if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
	}
	
    public class StaffReplyCustomCriteria extends StaffReplyExample.Criteria {
		
		
	}
	
}
