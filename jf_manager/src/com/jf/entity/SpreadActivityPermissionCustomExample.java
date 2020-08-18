package com.jf.entity;


public class SpreadActivityPermissionCustomExample extends SpreadActivityPermissionExample {

	@Override
	public SpreadActivityPermissionCustomCriteria createCriteria() {
		SpreadActivityPermissionCustomCriteria criteria = new SpreadActivityPermissionCustomCriteria();
		if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
	}
	
	public class SpreadActivityPermissionCustomCriteria extends SpreadActivityPermissionExample.Criteria {

		
	}
	
}
