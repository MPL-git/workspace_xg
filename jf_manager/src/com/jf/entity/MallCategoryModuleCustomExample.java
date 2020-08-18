package com.jf.entity;


public class MallCategoryModuleCustomExample extends MallCategoryModuleExample {

	@Override
    public MallCategoryModuleCustomCriteria createCriteria() {
		MallCategoryModuleCustomCriteria criteria = new MallCategoryModuleCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class MallCategoryModuleCustomCriteria extends MallCategoryModuleExample.Criteria{
		
	}
	
}
