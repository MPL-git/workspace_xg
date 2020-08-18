package com.jf.entity;


public class CouponCustomExample extends CouponExample {

	@Override
    public CouponCustomCriteria createCriteria() {
		CouponCustomCriteria criteria = new CouponCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class CouponCustomCriteria extends CouponExample.Criteria{
		
	}
	
}
