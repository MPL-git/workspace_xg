package com.jf.entity;

public class CouponExchangeCodeCustomExample extends CouponExchangeCodeExample {

	@Override
    public CouponExchangeCodeCustomCriteria createCriteria() {
		CouponExchangeCodeCustomCriteria criteria = new CouponExchangeCodeCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class CouponExchangeCodeCustomCriteria extends CouponExchangeCodeCustomExample.Criteria{
		
		/**
		 * 
		 * @Title andMemberCouponStatusByEqualTo   
		 * @Description TODO(游离码是否使用)   
		 * @author pengl
		 * @date 2018年2月9日 下午5:05:30
		 */
		public Criteria andMemberCouponStatusByEqualTo(String memberCouponStatus){
			if("1".equals(memberCouponStatus)) { //已使用
				addCriterion("EXISTS(select mc.id from bu_member_coupon mc where mc.del_flag = '0' and mc.id = member_coupon_id and mc.status = '"+memberCouponStatus+"' )");
			}else if("0".equals(memberCouponStatus)) { //未使用
				addCriterion("(member_coupon_id IS NULL or"
						+ " (SELECT mc.id FROM bu_member_coupon mc WHERE mc.del_flag = '0' AND mc.id = member_coupon_id AND mc. STATUS = '"+memberCouponStatus+"') is NOT NULL )");
			}
			return this;
		}
		
	}
	
	
}
