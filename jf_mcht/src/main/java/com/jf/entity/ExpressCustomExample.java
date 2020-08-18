package com.jf.entity;



public class ExpressCustomExample extends ExpressExample{
	
	@Override
    public ExpressCustomCriteria createCriteria() {
		ExpressCustomCriteria criteria = new ExpressCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class ExpressCustomCriteria extends ExpressExample.Criteria{
		
		
	}
}