package com.jf.entity;




public class MchtInfoChgCustomExample extends MchtInfoChgExample{
	
	@Override
    public MchtInfoChgCustomCriteria createCriteria() {
		MchtInfoChgCustomCriteria criteria = new MchtInfoChgCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class MchtInfoChgCustomCriteria extends MchtInfoChgExample.Criteria{
		public Criteria andMchtTypeEqualTo(String mchtType) {
			addCriterion(" EXISTS(select id from bu_mcht_info mi where mi.id=mcht_id and mi.del_flag='0' and mi.mcht_type='"+mchtType+"')");
	        return this;
	    }
		
		public Criteria andMchtStatusEqualTo(String mchtStatus) {
			addCriterion(" EXISTS(select id from bu_mcht_info mi where mi.id=mcht_id and mi.del_flag='0' and mi.status='"+mchtStatus+"')");
			return this;
		}
		
		public Criteria andMchtIsManageSelfEqualTo(String mchtIsManageSelf) {
			addCriterion(" EXISTS(select id from bu_mcht_info mi where mi.id=mcht_id and mi.del_flag='0' and mi.is_manage_self='"+mchtIsManageSelf+"')");
			return this;
		}
	}
}