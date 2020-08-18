package com.jf.entity;

public class MemberLabelGroupRelationCustomExample extends MemberLabelGroupRelationExample{
	
	@Override
    public MemberLabelGroupRelationCustomCriteria createCriteria() {
		MemberLabelGroupRelationCustomCriteria criteria = new MemberLabelGroupRelationCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class MemberLabelGroupRelationCustomCriteria extends MemberLabelGroupRelationExample.Criteria{
		
		
	}
}