package com.jf.entity;


public class SportTeamLogCustomExample extends SportTeamLogExample {

	@Override
	public SportTeamLogCustomCriteria createCriteria() {
		SportTeamLogCustomCriteria criteria = new SportTeamLogCustomCriteria();
		if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
	}
	
	
	public class SportTeamLogCustomCriteria extends SportTeamLogExample.Criteria {
		
	}
	
}
