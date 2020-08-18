package com.jf.entity;


public class ActivityAuditLogCustomExample extends ActivityAuditLogExample{

	@Override
    public ActivityAuditLogCustomCriteria createCriteria() {
		ActivityAuditLogCustomCriteria criteria = new ActivityAuditLogCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class ActivityAuditLogCustomCriteria extends ActivityAuditLogCustomExample.Criteria{
		
	}
}
