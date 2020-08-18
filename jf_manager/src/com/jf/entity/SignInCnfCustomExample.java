package com.jf.entity;


public class SignInCnfCustomExample extends SignInCnfExample {

	@Override
    public SignInCnfCustomCriteria createCriteria() {
		SignInCnfCustomCriteria criteria = new SignInCnfCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class SignInCnfCustomCriteria extends SignInCnfCustomExample.Criteria {
		
	}
	
}
