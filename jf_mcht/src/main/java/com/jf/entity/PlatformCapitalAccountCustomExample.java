package com.jf.entity;



public class PlatformCapitalAccountCustomExample extends PlatformCapitalAccountExample{
	
	@Override
    public PlatformCapitalAccountCustomCriteria createCriteria() {
		PlatformCapitalAccountCustomCriteria criteria = new PlatformCapitalAccountCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class PlatformCapitalAccountCustomCriteria extends PlatformCapitalAccountExample.Criteria{
		
		
	}
}