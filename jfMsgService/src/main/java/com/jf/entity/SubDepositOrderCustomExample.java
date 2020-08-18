package com.jf.entity;


public class SubDepositOrderCustomExample extends SubDepositOrderExample {

	@Override
    public SubDepositOrderCustomCriteria createCriteria() {
		SubDepositOrderCustomCriteria criteria = new SubDepositOrderCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class SubDepositOrderCustomCriteria extends SubDepositOrderExample.Criteria{
		
	}
	
}
