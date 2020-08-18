package com.jf.entity;


public class SourceProductTypeCustomExample extends SourceProductTypeExample {

	@Override
	public SourceProductTypeCustomCriteria createCriteria() {
		SourceProductTypeCustomCriteria criteria = new SourceProductTypeCustomCriteria();
		if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
	}
	
    public class SourceProductTypeCustomCriteria extends SourceProductTypeExample.Criteria {
		
    	
		
	}
	
}
