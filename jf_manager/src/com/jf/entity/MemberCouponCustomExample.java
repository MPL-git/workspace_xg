package com.jf.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MemberCouponCustomExample extends MemberCouponExample{
	
	@Override
    public MemberCouponCustomCriteria createCriteria() {
		MemberCouponCustomCriteria criteria = new MemberCouponCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class MemberCouponCustomCriteria extends MemberCouponExample.Criteria{
		public Criteria andNickLike(String nick) {
			addCriterion(" EXISTS(select mi.id from bu_member_info mi where mi.id=member_id and mi.del_flag='0' and mi.nick like CONCAT('%', '"+nick+"', '%'))");
			return this;
		}
		
		public Criteria andMobileEqualTo(String mobile) {
			addCriterion(" EXISTS(select mi.id from bu_member_info mi where mi.id=member_id and mi.del_flag='0' and mi.mobile='"+mobile+"')");
			return this;
		}
		
		public Criteria andSmallexpiryEndDateEqualTo(Date valueOne){//未过期
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
			String sdfOne=dateFormat.format(valueOne);
			addCriterion("'"+sdfOne+"'<=(DATE_FORMAT(expiry_end_date, '%Y-%m-%d'))");
			return this;
		}
		
		public Criteria andlargeexpiryEndDateEqualTo(Date valuetwo){//已过期
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
			String sdftwo=dateFormat.format(valuetwo);
			addCriterion("'"+sdftwo+"'>(DATE_FORMAT(expiry_end_date, '%Y-%m-%d'))");
			return this;
		}
		
		
		public Criteria andactivityareaIdEqualTo(Integer activityareaId) {//活动会场ID
			addCriterion("coupon_id in (select c.id from bu_coupon c where c.id=coupon_id and c.del_flag='0' and c.activity_area_id='"+activityareaId+"')");
			return this;
		}
	}
}