package com.jf.entity;


public class MallCategoryCustomExample extends MallCategoryExample {

	@Override
    public MallCategoryCustomCriteria createCriteria() {
		MallCategoryCustomCriteria criteria = new MallCategoryCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class MallCategoryCustomCriteria extends MallCategoryExample.Criteria{
		
	}
	
}
