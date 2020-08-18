package com.jf.entity;


public class MchtShopDynamicCustomExample extends MchtShopDynamicExample {

	@Override
    public MchtShopDynamicCustomCriteria createCriteria() {
		MchtShopDynamicCustomCriteria criteria = new MchtShopDynamicCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class MchtShopDynamicCustomCriteria extends MchtShopDynamicExample.Criteria{
		
	}
	
}
