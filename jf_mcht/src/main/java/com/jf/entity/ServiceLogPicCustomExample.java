package com.jf.entity;



public class ServiceLogPicCustomExample extends ServiceLogPicExample{
	
	@Override
    public ServiceLogPicCustomCriteria createCriteria() {
		ServiceLogPicCustomCriteria criteria = new ServiceLogPicCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class ServiceLogPicCustomCriteria extends ServiceLogPicExample.Criteria{
		
		
	}
}