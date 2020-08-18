package com.jf.entity;

public class SportTeamCustomExample extends SportTeamExample {

	@Override
	public SportTeamCustomCriteria createCriteria() {
		SportTeamCustomCriteria criteria = new SportTeamCustomCriteria();
		if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
	}
	
	
	public class SportTeamCustomCriteria extends SportTeamExample.Criteria {
		
	}

	
}
