package com.jf.entity;



public class ActivityBrandGroupCustomExample extends ActivityBrandGroupExample {
	@Override
    public ActivityBrandGroupCustomCriteria createCriteria() {
		ActivityBrandGroupCustomCriteria criteria = new ActivityBrandGroupCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class ActivityBrandGroupCustomCriteria extends ActivityBrandGroupCustomExample.Criteria{
		
	}
}