package com.jf.entity;


public class DouyinSprDtlCustomExample extends DouyinSprDtlExample {

	@Override
    public DouyinSprDtlCustomCriteria createCriteria() {
		DouyinSprDtlCustomCriteria criteria = new DouyinSprDtlCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class DouyinSprDtlCustomCriteria extends DouyinSprDtlExample.Criteria {
		
		
		
	}
	
}
