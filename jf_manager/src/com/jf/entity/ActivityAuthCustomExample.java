package com.jf.entity;



public class ActivityAuthCustomExample extends ActivityAuthExample {
	@Override
    public ActivityAuthCustomCriteria createCriteria() {
		ActivityAuthCustomCriteria criteria = new ActivityAuthCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class ActivityAuthCustomCriteria extends ActivityAuthCustomExample.Criteria{
		
	}
}