package com.jf.entity;



public class SvipOrderCustomExample extends SvipOrderExample {

	@Override
    public SvipOrderCustomCriteria createCriteria() {
		SvipOrderCustomCriteria criteria = new SvipOrderCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class SvipOrderCustomCriteria extends SvipOrderExample.Criteria{
		
		
		
	}
	
}
