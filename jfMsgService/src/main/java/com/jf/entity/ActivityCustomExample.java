package com.jf.entity;


public class ActivityCustomExample extends ActivityExample {

	@Override
    public ActivityCustomCriteria createCriteria() {
		ActivityCustomCriteria criteria = new ActivityCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class ActivityCustomCriteria extends ActivityExample.Criteria{
		
	}
	
}
