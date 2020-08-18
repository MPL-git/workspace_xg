package com.jf.entity;



public class ImpeachHandleLogCustomExample extends ImpeachHandleLogExample {

	@Override
    public ImpeachHandleLogCustomCriteria createCriteria() {
		ImpeachHandleLogCustomCriteria criteria = new ImpeachHandleLogCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class ImpeachHandleLogCustomCriteria extends ImpeachHandleLogExample.Criteria{
		
		
		
	}
	
}
