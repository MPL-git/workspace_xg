package com.jf.entity;


public class CouponRainCustomExample extends CouponRainExample {

	@Override
    public CouponRainCustomCriteria createCriteria() {
		CouponRainCustomCriteria criteria = new CouponRainCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class CouponRainCustomCriteria extends CouponRainCustomExample.Criteria{
	
		public Criteria andtitleLike(String title) {
			addCriterion(" EXISTS(select crs.id from bu_coupon_rain_setup crs where crs.del_flag = '0' and crs.id = cr.coupon_rain_setup_id and crs.title like '%" + title + "%')");
	    	return this;
		}
		
	}
	
	
}
