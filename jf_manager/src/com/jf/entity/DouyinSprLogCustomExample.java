package com.jf.entity;


public class DouyinSprLogCustomExample extends DouyinSprLogExample {

	@Override
    public DouyinSprLogCustomCriteria createCriteria() {
		DouyinSprLogCustomCriteria criteria = new DouyinSprLogCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class DouyinSprLogCustomCriteria extends DouyinSprLogExample.Criteria {
		
		
		
	}
	
}
