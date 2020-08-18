package com.jf.entity;



public class CustomerServiceLogCustomExample extends CustomerServiceLogExample{
	
	@Override
    public CustomerServiceLogCustomCriteria createCriteria() {
		CustomerServiceLogCustomCriteria criteria = new CustomerServiceLogCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class CustomerServiceLogCustomCriteria extends CustomerServiceLogExample.Criteria{
		
		
	}
}