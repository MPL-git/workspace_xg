package com.jf.entity;



public class RefundOrderCustomExample extends RefundOrderExample{
	
	@Override
    public RefundOrderCustomCriteria createCriteria() {
		RefundOrderCustomCriteria criteria = new RefundOrderCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class RefundOrderCustomCriteria extends RefundOrderExample.Criteria{
		
		
	}
}