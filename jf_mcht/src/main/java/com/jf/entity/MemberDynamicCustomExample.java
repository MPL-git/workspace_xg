package com.jf.entity;






public class MemberDynamicCustomExample extends MemberDynamicExample{
	

	@Override
    public MemberDynamicCustomCriteria createCriteria() {
		MemberDynamicCustomCriteria criteria = new MemberDynamicCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	
	public class MemberDynamicCustomCriteria extends MemberDynamicExample.Criteria{
		
	}
}