package com.jf.entity;

public class MemberShopFootprintCustomExample extends MemberShopFootprintExample{
	
	@Override
    public MemberShopFootprintCustomCriteria createCriteria() {
		MemberShopFootprintCustomCriteria criteria = new MemberShopFootprintCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class MemberShopFootprintCustomCriteria extends MemberShopFootprintExample.Criteria{
		public Criteria andNickLike(String nick) {
			addCriterion(" member_id in(select mi.id from bu_member_info mi where mi.id=member_id and mi.del_flag='0' and mi.nick like CONCAT('%', '"+nick+"', '%'))");
			return this;
		}
		
		public Criteria andMobileEqualTo(String mobile) {
			addCriterion(" member_id in(select mi.id from bu_member_info mi where mi.id=member_id and mi.del_flag='0' and mi.mobile='"+mobile+"')");
			return this;
		}
		
		public Criteria andMchtCodeEqualTo(String mchtCode) {
			addCriterion(" mcht_id in(select m.id from bu_mcht_info m where m.id =mcht_id and m.mcht_code = '"+ mchtCode +"' )");
			return this;
		}
		
	}
}