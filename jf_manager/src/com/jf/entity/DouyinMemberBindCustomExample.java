package com.jf.entity;


public class DouyinMemberBindCustomExample extends DouyinMemberBindExample {

	@Override
    public DouyinMemberBindCustomCriteria createCriteria() {
		DouyinMemberBindCustomCriteria criteria = new DouyinMemberBindCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class DouyinMemberBindCustomCriteria extends DouyinMemberBindExample.Criteria {
		
		
		
	}
	
}
