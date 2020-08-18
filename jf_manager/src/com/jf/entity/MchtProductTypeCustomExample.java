package com.jf.entity;




public class MchtProductTypeCustomExample extends MchtProductTypeExample{
	
	@Override
    public MchtProductTypeCustomCriteria createCriteria() {
		MchtProductTypeCustomCriteria criteria = new MchtProductTypeCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class MchtProductTypeCustomCriteria extends MchtProductTypeExample.Criteria{
		public Criteria andPlatformContactIdEqualTo(Integer platformContactId) {
			addCriterion(" EXISTS (select mi.mcht_id from bu_mcht_platform_contact mi where mi.mcht_id=t.mcht_id and mi.platform_contact_id="+platformContactId+")");
			return this;
		}
		
		public Criteria andCompanyNameLike(String companyName) {
			addCriterion(" EXISTS (select id from bu_mcht_info mi where mi.id=t.mcht_id  and mi.company_name like '%"+companyName+"%')");
			return this;
		}
		
		public Criteria andMchtStatusIn(String status) {
			addCriterion(" EXISTS (select mi.id from bu_mcht_info mi where mi.id = t.mcht_id and mi.del_flag = '0' and mi.status in(" + status + "))");
			return this;
		}
		
	}
}