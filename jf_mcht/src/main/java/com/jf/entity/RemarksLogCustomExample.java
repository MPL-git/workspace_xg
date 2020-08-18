package com.jf.entity;



public class RemarksLogCustomExample extends RemarksLogExample{
	
	@Override
    public RemarksLogCustomCriteria createCriteria() {
		RemarksLogCustomCriteria criteria = new RemarksLogCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class RemarksLogCustomCriteria extends RemarksLogExample.Criteria{
		
		
	}
}