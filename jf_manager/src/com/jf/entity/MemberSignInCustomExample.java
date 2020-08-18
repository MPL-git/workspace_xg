package com.jf.entity;

public class MemberSignInCustomExample extends MemberSignInExample {

	@Override
    public MemberSignInCustomCriteria createCriteria() {
		MemberSignInCustomCriteria criteria = new MemberSignInCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class MemberSignInCustomCriteria extends MemberSignInExample.Criteria{
		
		public Criteria andMemberNickLike(String memberNick) {
			addCriterion(" EXISTS(select mi.id from bu_member_info mi where mi.del_flag = '0' and mi.id = t.member_id and mi.nick like '"+ memberNick +"')");
			return this;
		}
		
		public Criteria andLastSignInDayGreaterThanOrEqualTo(Integer lastSignInDay) {
			addCriterion(" t.last_sign_in_date <= DATE_SUB(CURDATE(), INTERVAL "+lastSignInDay+" DAY)");
			return this;
		}
		
		public Criteria andBlackListNull() {
			addCriterion(" NOT EXISTS(select bl.id from bu_black_list bl where bl.del_flag = '0' and bl.black_type = '1' and bl.member_id = t.member_id and bl.black_to_date >= now() )");
			return this;
		}
		
		public Criteria andMemberMobileLike(String memberMobile) {
			addCriterion(" EXISTS(select mi.id from bu_member_info mi where mi.del_flag = '0' and mi.id = t.member_id and mi.mobile like '"+ memberMobile +"')");
			return this;
		}
		
		public Criteria andMemberId(Integer memberId) {
			addCriterion(" t.member_id in(select mi.id from bu_member_info mi where mi.del_flag = '0' and mi.id = t.member_id and mi.id="+memberId +")");
			return this;
		}
		
	}
	
}
