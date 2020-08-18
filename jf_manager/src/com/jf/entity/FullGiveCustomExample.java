package com.jf.entity;



public class FullGiveCustomExample extends FullGiveExample {
	@Override
    public FullGiveCustomCriteria createCriteria() {
		FullGiveCustomCriteria criteria = new FullGiveCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class FullGiveCustomCriteria extends FullGiveCustomExample.Criteria{
		
	}
}