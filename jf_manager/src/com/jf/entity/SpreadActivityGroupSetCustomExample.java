package com.jf.entity;


public class SpreadActivityGroupSetCustomExample extends SpreadActivityGroupSetExample {

	@Override
	public SpreadActivityGroupSetCustomCriteria createCriteria() {
		SpreadActivityGroupSetCustomCriteria criteria = new SpreadActivityGroupSetCustomCriteria();
		if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
	}
	
	
	public class SpreadActivityGroupSetCustomCriteria extends SpreadActivityGroupSetExample.Criteria {

		
	}
	
}
