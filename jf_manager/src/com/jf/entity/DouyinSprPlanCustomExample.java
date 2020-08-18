package com.jf.entity;


public class DouyinSprPlanCustomExample extends DouyinSprPlanExample {

	@Override
    public DouyinSprPlanCustomCriteria createCriteria() {
		DouyinSprPlanCustomCriteria criteria = new DouyinSprPlanCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class DouyinSprPlanCustomCriteria extends DouyinSprPlanExample.Criteria {
		
		
		
	}
	
}
