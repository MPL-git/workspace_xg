package com.jf.entity;


public class MemberAccountCustomExample extends MemberAccountExample {

	@Override
	public MemberAccountCustomCriteria createCriteria() {
		MemberAccountCustomCriteria criteria = new MemberAccountCustomCriteria();
		if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
	}
	
	
	public class MemberAccountCustomCriteria extends MemberAccountExample.Criteria {
		
	}
	
}
