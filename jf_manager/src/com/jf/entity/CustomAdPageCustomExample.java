package com.jf.entity;

public class CustomAdPageCustomExample extends CustomAdPageExample {

	@Override
    public CustomAdPageCustomCriteria createCriteria() {
		CustomAdPageCustomCriteria criteria = new CustomAdPageCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class CustomAdPageCustomCriteria extends CustomAdPageCustomExample.Criteria{
		
		
	}
	
	
}
