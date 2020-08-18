package com.jf.entity;



public class MchtDepositDtlCustomExample extends MchtDepositDtlExample{
	
	@Override
    public MchtDepositDtlCustomCriteria createCriteria() {
		MchtDepositDtlCustomCriteria criteria = new MchtDepositDtlCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class MchtDepositDtlCustomCriteria extends MchtDepositDtlExample.Criteria{
		
	}
	
}