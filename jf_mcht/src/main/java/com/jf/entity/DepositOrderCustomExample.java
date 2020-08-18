package com.jf.entity;



public class DepositOrderCustomExample extends DepositOrderExample{
	
	@Override
    public DepositOrderCustomCriteria createCriteria() {
		DepositOrderCustomCriteria criteria = new DepositOrderCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class DepositOrderCustomCriteria extends DepositOrderExample.Criteria{
		
		
	}
}