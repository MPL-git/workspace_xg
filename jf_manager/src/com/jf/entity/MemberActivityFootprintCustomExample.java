package com.jf.entity;

public class MemberActivityFootprintCustomExample extends MemberActivityFootprintExample{
	
	@Override
    public MemberActivityFootprintCustomCriteria createCriteria() {
		MemberActivityFootprintCustomCriteria criteria = new MemberActivityFootprintCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class MemberActivityFootprintCustomCriteria extends MemberActivityFootprintExample.Criteria{
		public Criteria andNickLike(String nick) {
			addCriterion(" member_id in(select mi.id from bu_member_info mi where mi.id=member_id and mi.del_flag='0' and mi.nick like CONCAT('%', '"+nick+"', '%'))");
			return this;
		}
		
		public Criteria andMobileEqualTo(String mobile) {
			addCriterion(" member_id in(select mi.id from bu_member_info mi where mi.id=member_id and mi.del_flag='0' and mi.mobile='"+mobile+"')");
			return this;
		}
		
		public Criteria andMchtCodeEqualTo(String mchtCode) {
			addCriterion(" activity_id in(select ac.id from bu_mcht_info m, bu_activity ac where ac.mcht_id=m.id and activity_id=ac.id and m.mcht_code = '"+ mchtCode +"' )");
			return this;
		}
		
	}
}