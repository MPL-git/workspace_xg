package com.jf.entity;

public class PlatformMsgEditCustomExample extends PlatformMsgEditExample{
	
	@Override
    public PlatformMsgEditExampleCustomCriteria createCriteria() {
		PlatformMsgEditExampleCustomCriteria criteria = new PlatformMsgEditExampleCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class PlatformMsgEditExampleCustomCriteria extends PlatformMsgEditExample.Criteria{
		
	}
}
