package com.jf.entity;



public class CouponCombineRecLimitCustomExample extends CouponCombineRecLimitExample{
	
	@Override
    public CouponCombineRecLimitCriteria createCriteria() {
		CouponCombineRecLimitCriteria criteria = new CouponCombineRecLimitCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class CouponCombineRecLimitCriteria extends CouponCombineRecLimitExample.Criteria{
		
		 public Criteria andcouponIdEqualTo(Integer couponId) {
	  			addCriterion("EXISTS(select crt.coupon_combine_rec_limit_id from bu_coupon_combine_rec_limit_dtl crt where crt.del_flag = '0' and crt.coupon_combine_rec_limit_id=ccr.id and crt.coupon_id='"+couponId+"')");
	  			return this;
	  		}
	}
}