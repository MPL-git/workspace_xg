package com.jf.entity;


public class MemberExtendCustomExample extends MemberExtendExample {
	
	
	@Override
    public MemberExtendCustomCriteria createCriteria() {
		MemberExtendCustomCriteria criteria = new MemberExtendCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class  MemberExtendCustomCriteria extends  MemberExtendExample.Criteria{
		
		public Criteria andMemberMobileEqualTo(String memberMobile) {
			addCriterion(" EXISTS(SELECT mi.id FROM bu_member_info mi WHERE mi.del_flag = '0' AND mi.id = me.member_id  AND mi.mobile = '" + memberMobile + "')");			
			return this;
		}
		
		
		public Criteria andMemberNickEqualTo(String memberNick) {
			addCriterion(" EXISTS(SELECT mi.id FROM bu_member_info mi WHERE mi.del_flag = '0' AND mi.id = me.member_id  AND mi.nick = '" + memberNick + "')");			
			return this;
		}
		
		
		public Criteria andMemberCreateDateGreaterThanOrEqualTo(String memberCreateDate) {
			addCriterion("EXISTS( SELECT mi.id FROM bu_member_info mi	WHERE mi.del_flag = '0' AND mi.id = me.member_id	AND mi.create_date >= '" + memberCreateDate + "')");			
			return this;
		}
		
		
		public Criteria andMemberEndLessThanOrEqualTo(String memberEndDate) {
			addCriterion("EXISTS( SELECT mi.id FROM bu_member_info mi	WHERE mi.del_flag = '0' AND mi.id = me.member_id	AND mi.create_date <= '" + memberEndDate + "')");			
			return this;
		}
		
	}

}
