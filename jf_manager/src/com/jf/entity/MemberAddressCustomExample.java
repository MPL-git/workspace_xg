package com.jf.entity;

public class MemberAddressCustomExample extends MemberAddressExample{
	
	@Override
    public MemberAddressCustomCriteria createCriteria() {
		MemberAddressCustomCriteria criteria = new MemberAddressCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class MemberAddressCustomCriteria extends MemberAddressExample.Criteria{
		
		public Criteria andNickLike(String nick) {
			addCriterion(" EXISTS(select mi.id from bu_member_info mi where mi.id=member_id and mi.del_flag='0' and mi.nick like CONCAT('%', '"+nick+"', '%'))");
			return this;
		}
	}
}