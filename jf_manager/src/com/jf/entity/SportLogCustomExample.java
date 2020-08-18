package com.jf.entity;

public class SportLogCustomExample extends SportLogExample {

	@Override
	public SportLogCustomCriteria createCriteria() {
		SportLogCustomCriteria criteria = new SportLogCustomCriteria();
		if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
	}
	
	
	public class SportLogCustomCriteria extends SportLogExample.Criteria {
		
	}
	
}
