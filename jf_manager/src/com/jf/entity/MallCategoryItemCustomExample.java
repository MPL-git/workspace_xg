package com.jf.entity;


public class MallCategoryItemCustomExample extends MallCategoryItemExample {

	@Override
    public MallCategoryItemCustomCriteria createCriteria() {
		MallCategoryItemCustomCriteria criteria = new MallCategoryItemCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class MallCategoryItemCustomCriteria extends MallCategoryItemExample.Criteria{
		
	}
	
}
