package com.jf.entity;


public class MchtInfoCustomExample extends MchtInfoExample {

	@Override
	public MchtInfoCustomCriteria createCriteria() {
		MchtInfoCustomCriteria criteria = new MchtInfoCustomCriteria();
		if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
	}
	
	
	public class MchtInfoCustomCriteria extends MchtInfoExample.Criteria {

		
	}
	
}
