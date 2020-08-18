package com.jf.entity;


public class HelpItemCustomExample extends HelpItemExample {

	@Override
    public HelpItemCustomCriteria createCriteria() {
		HelpItemCustomCriteria criteria = new HelpItemCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class HelpItemCustomCriteria extends HelpItemCustomExample.Criteria{
		
	}
	
}
