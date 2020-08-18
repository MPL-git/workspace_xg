package com.jf.entity;



public class OrderLogCustomExample extends OrderLogExample{
	
	@Override
    public OrderLogCustomCriteria createCriteria() {
		OrderLogCustomCriteria criteria = new OrderLogCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class OrderLogCustomCriteria extends OrderLogExample.Criteria{
		
		
	}
}