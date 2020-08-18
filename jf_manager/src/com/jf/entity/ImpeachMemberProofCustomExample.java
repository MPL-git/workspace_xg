package com.jf.entity;



public class ImpeachMemberProofCustomExample extends ImpeachMemberProofExample {

	@Override
    public ImpeachMemberProofCustomCriteria createCriteria() {
		ImpeachMemberProofCustomCriteria criteria = new ImpeachMemberProofCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class ImpeachMemberProofCustomCriteria extends ImpeachMemberProofExample.Criteria{
		
		
		
	}
	
}
