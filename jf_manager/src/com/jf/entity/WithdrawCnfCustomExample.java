package com.jf.entity;

public class WithdrawCnfCustomExample extends WithdrawCnfExample {

	@Override
	public WithdrawCnfCustomCriteria createCriteria() {
		WithdrawCnfCustomCriteria criteria = new WithdrawCnfCustomCriteria();
		if(oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}
	
	public class WithdrawCnfCustomCriteria extends WithdrawCnfExample.Criteria {
		
	}
	
}
