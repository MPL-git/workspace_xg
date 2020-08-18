package com.jf.entity;


public class AndroidChannelGroupCustomExample extends AndroidChannelGroupExample {
	@Override
    public ActivityCustomCriteria createCriteria() {
		ActivityCustomCriteria criteria = new ActivityCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class ActivityCustomCriteria extends AndroidChannelGroupCustomExample.Criteria{
		
	}
}