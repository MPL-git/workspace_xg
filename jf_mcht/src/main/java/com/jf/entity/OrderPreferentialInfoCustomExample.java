package com.jf.entity;



public class OrderPreferentialInfoCustomExample extends OrderPreferentialInfoExample{
	
	@Override
    public OrderPreferentialInfoCustomCriteria createCriteria() {
		OrderPreferentialInfoCustomCriteria criteria = new OrderPreferentialInfoCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class OrderPreferentialInfoCustomCriteria extends OrderPreferentialInfoExample.Criteria{
		
		
	}
}