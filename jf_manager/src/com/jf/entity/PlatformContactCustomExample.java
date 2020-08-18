package com.jf.entity;

public class PlatformContactCustomExample extends PlatformContactExample{
	
	@Override
    public PlatformContactCustomCriteria createCriteria() {
		PlatformContactCustomCriteria criteria = new PlatformContactCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class PlatformContactCustomCriteria extends PlatformContactExample.Criteria{
		public Criteria andMchtHasNoEqualTo(int mchtId) {
			addCriterion(" NOT EXISTS(select mi.platform_contact_id from bu_mcht_platform_contact mi where mi.mcht_id="+mchtId+" and mi.platform_contact_id=a.id and mi.status='1')");
			return this;
		}
		
		public Criteria andContactTypeHasNoEqualTo(int mchtId) {
			addCriterion(" a.contact_type NOT IN (select distinct mp.contact_type from bu_mcht_platform_contact mi, bu_platform_contact mp where mi.mcht_id="+mchtId+" and mi.platform_contact_id=mp.id and mi.status='1')");
			return this;
		}
		
		public Criteria andMchtIdEqualTo(int mchtId) {
			addCriterion(" EXISTS(select mi.platform_contact_id from bu_mcht_platform_contact mi where mi.mcht_id = "+mchtId+" and mi.platform_contact_id = a.id and mi.status = '1')");
			return this;
		}
	
	}
}