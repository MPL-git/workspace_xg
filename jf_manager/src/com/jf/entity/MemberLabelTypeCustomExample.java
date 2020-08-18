package com.jf.entity;


public class MemberLabelTypeCustomExample extends MemberLabelTypeExample{
	
	@Override
    public MemberLabelTypeCustomCriteria createCriteria() {
		MemberLabelTypeCustomCriteria criteria = new MemberLabelTypeCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class MemberLabelTypeCustomCriteria extends MemberLabelTypeExample.Criteria{
		
		
	}
}