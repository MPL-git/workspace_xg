package com.jf.entity;



public class FullDiscountCustomExample extends FullDiscountExample {
	@Override
    public FullDiscountCustomCriteria createCriteria() {
		FullDiscountCustomCriteria criteria = new FullDiscountCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class FullDiscountCustomCriteria extends FullDiscountCustomExample.Criteria{
		
	}
}