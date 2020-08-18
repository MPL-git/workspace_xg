package com.jf.entity;



public class AppealLogCustomExample extends AppealLogExample{
	
	@Override
    public AppealLogCustomCriteria createCriteria() {
		AppealLogCustomCriteria criteria = new AppealLogCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class AppealLogCustomCriteria extends AppealLogExample.Criteria{
		
		
	}
}