package com.jf.entity;

public class CouponRainSetupCustomExample extends CouponRainSetupExample {

	@Override
    public CouponRainSetupCustomCriteria createCriteria() {
		CouponRainSetupCustomCriteria criteria = new CouponRainSetupCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class CouponRainSetupCustomCriteria extends CouponRainSetupCustomExample.Criteria{
		
	}
	
	
}
