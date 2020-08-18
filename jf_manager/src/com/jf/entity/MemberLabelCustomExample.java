package com.jf.entity;

public class MemberLabelCustomExample extends MemberLabelExample{
	
	@Override
    public MemberLabelCustomCriteria createCriteria() {
		MemberLabelCustomCriteria criteria = new MemberLabelCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class MemberLabelCustomCriteria extends MemberLabelExample.Criteria{
		
		
	}
}