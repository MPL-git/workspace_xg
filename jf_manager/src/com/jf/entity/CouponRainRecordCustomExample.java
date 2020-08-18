package com.jf.entity;

public class CouponRainRecordCustomExample extends CouponRainRecordExample {

	@Override
    public CouponRainRecordCustomCriteria createCriteria() {
		CouponRainRecordCustomCriteria criteria = new CouponRainRecordCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class CouponRainRecordCustomCriteria extends CouponRainRecordCustomExample.Criteria{
		
	}
	
	
}
