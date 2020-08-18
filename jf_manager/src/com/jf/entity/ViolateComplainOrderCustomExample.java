package com.jf.entity;



public class ViolateComplainOrderCustomExample extends ViolateComplainOrderExample{
	
	@Override
    public ViolateComplainOrderCustomCriteria createCriteria() {
		ViolateComplainOrderCustomCriteria criteria = new ViolateComplainOrderCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class ViolateComplainOrderCustomCriteria extends ViolateComplainOrderExample.Criteria{
		
		
	}
}