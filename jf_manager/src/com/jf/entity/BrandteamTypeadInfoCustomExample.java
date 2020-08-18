package com.jf.entity;

public class BrandteamTypeadInfoCustomExample extends BrandteamTypeadInfoExample {

	@Override
	public BrandteamTypeadInfoCustomCriteria createCriteria() {
		BrandteamTypeadInfoCustomCriteria criteria = new BrandteamTypeadInfoCustomCriteria();
		if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
	}
	
    public class BrandteamTypeadInfoCustomCriteria extends BrandteamTypeadInfoExample.Criteria {
		
    	
	}
	
}
