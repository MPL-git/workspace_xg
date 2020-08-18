package com.jf.entity;


public class MemberPvCustomExample extends MemberPvExample {

	@Override
    public MemberPvCustomCriteria createCriteria() {
		MemberPvCustomCriteria criteria = new MemberPvCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

	public class MemberPvCustomCriteria extends MemberPvExample.Criteria{
		
	}
	
}
