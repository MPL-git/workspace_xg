package com.jf.entity;


public class CloudProductAfterTempletCustomExample extends CloudProductAfterTempletExample{
	
	@Override
    public CloudProductAfterTempletCustomCriteria createCriteria() {
		CloudProductAfterTempletCustomCriteria criteria = new CloudProductAfterTempletCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

	
	public class CloudProductAfterTempletCustomCriteria extends CloudProductAfterTempletExample.Criteria{
		
	}
}