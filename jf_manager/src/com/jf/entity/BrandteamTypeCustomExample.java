package com.jf.entity;

public class BrandteamTypeCustomExample extends BrandteamTypeExample {

	@Override
	public BrandteamTypeCustomCriteria createCriteria() {
		BrandteamTypeCustomCriteria criteria = new BrandteamTypeCustomCriteria();
		if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
	}
	
    public class BrandteamTypeCustomCriteria extends BrandteamTypeExample.Criteria {
		
    	
	}
	
}
