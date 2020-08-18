package com.jf.entity;


public class ShopownerOrderCustomExample extends ShopownerOrderExample {

	@Override
    public ShopownerOrderCustomCriteria createCriteria() {
		ShopownerOrderCustomCriteria criteria = new ShopownerOrderCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class ShopownerOrderCustomCriteria extends ShopownerOrderCustomExample.Criteria {
		
	}
	
}
