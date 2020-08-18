package com.jf.entity;

public class IntegralDtlCustomExample extends IntegralDtlExample{
	
	@Override
    public IntegralDtlCustomCriteria createCriteria() {
		IntegralDtlCustomCriteria criteria = new IntegralDtlCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class IntegralDtlCustomCriteria extends IntegralDtlExample.Criteria{
		public Criteria andMemberIdEqualTo(Integer memberId) {
			addCriterion(" EXISTS(select id from bu_member_account mi where mi.id=acc_id and mi.del_flag='0' and mi.member_id='"+memberId+"')");
	        return this;
	    }
		
		public Criteria andNickLike(String nick) {
			addCriterion(" EXISTS(select mi.id from bu_member_account mi,bu_member_info mc where mi.id=acc_id and mi.member_id=mc.id and mi.del_flag='0' and mc.nick like CONCAT('%', '"+nick+"', '%'))");
			return this;
		}
		
		public Criteria andMobileEqualTo(String mobile) {
			addCriterion(" EXISTS(select mi.id from bu_member_account mi,bu_member_info mc where mi.id=acc_id and mi.member_id=mc.id and mi.del_flag='0' and mc.mobile='"+mobile+"')");
			return this;
		}
	}
}