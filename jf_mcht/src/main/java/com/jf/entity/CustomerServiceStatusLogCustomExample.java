package com.jf.entity;



public class CustomerServiceStatusLogCustomExample extends CustomerServiceStatusLogExample{
	
	@Override
    public CustomerServiceStatusLogCustomCriteria createCriteria() {
		CustomerServiceStatusLogCustomCriteria criteria = new CustomerServiceStatusLogCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class CustomerServiceStatusLogCustomCriteria extends CustomerServiceStatusLogExample.Criteria{
		
		
	}
}