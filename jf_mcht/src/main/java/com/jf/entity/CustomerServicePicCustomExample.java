package com.jf.entity;



public class CustomerServicePicCustomExample extends CustomerServicePicExample{
	
	@Override
    public CustomerServicePicCustomCriteria createCriteria() {
		CustomerServicePicCustomCriteria criteria = new CustomerServicePicCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class CustomerServicePicCustomCriteria extends CustomerServicePicExample.Criteria{
		
		
	}
}