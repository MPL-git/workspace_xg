package com.jf.entity;


public class ActivityProductAuditLogCustomExample extends ActivityProductAuditLogExample{

	@Override
    public ActivityProductAuditLogCustomCriteria createCriteria() {
		ActivityProductAuditLogCustomCriteria criteria = new ActivityProductAuditLogCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class ActivityProductAuditLogCustomCriteria extends ActivityProductAuditLogCustomExample.Criteria{
		
	}
}
