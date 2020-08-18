package com.jf.entity;



public class MchtDepositCustomExample extends MchtDepositExample{
	
	@Override
    public MchtDepositCustomCriteria createCriteria() {
		MchtDepositCustomCriteria criteria = new MchtDepositCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class MchtDepositCustomCriteria extends MchtDepositExample.Criteria{
		
	}
	
}