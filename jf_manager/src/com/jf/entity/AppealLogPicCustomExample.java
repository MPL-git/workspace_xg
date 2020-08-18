package com.jf.entity;



public class AppealLogPicCustomExample extends AppealLogPicExample{
	
	@Override
    public AppealLogPicCustomCriteria createCriteria() {
		AppealLogPicCustomCriteria criteria = new AppealLogPicCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class AppealLogPicCustomCriteria extends AppealLogPicExample.Criteria{
		
		
	}
}