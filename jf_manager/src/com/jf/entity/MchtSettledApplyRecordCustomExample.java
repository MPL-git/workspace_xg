package com.jf.entity;

public class MchtSettledApplyRecordCustomExample extends MchtSettledApplyRecordExample{
	
	@Override
    public MchtSettledApplyRecordCustomCriteria createCriteria() {
		MchtSettledApplyRecordCustomCriteria criteria = new MchtSettledApplyRecordCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class MchtSettledApplyRecordCustomCriteria extends MchtSettledApplyRecordExample.Criteria{

	}
}