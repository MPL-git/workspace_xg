package com.jf.entity;


public class ToutiaoTokenAdvertiserInfoCustomExample extends ToutiaoTokenAdvertiserInfoExample {

	@Override
    public ToutiaoTokenAdvertiserInfoCustomCriteria createCriteria() {
		ToutiaoTokenAdvertiserInfoCustomCriteria criteria = new ToutiaoTokenAdvertiserInfoCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class ToutiaoTokenAdvertiserInfoCustomCriteria extends ToutiaoTokenAdvertiserInfoExample.Criteria {
		
		
	}
	
}
