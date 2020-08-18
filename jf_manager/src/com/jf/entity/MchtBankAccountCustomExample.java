package com.jf.entity;




public class MchtBankAccountCustomExample extends MchtBankAccountExample{
	
	@Override
    public MchtBankAccountCustomCriteria createCriteria() {
		MchtBankAccountCustomCriteria criteria = new MchtBankAccountCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class MchtBankAccountCustomCriteria extends MchtBankAccountExample.Criteria{
		public Criteria andPlatformContactIdEqualTo(Integer platformContactId) {
			addCriterion(" EXISTS (select mi.mcht_id from bu_mcht_platform_contact mi where mi.mcht_id=t.mcht_id and mi.platform_contact_id="+platformContactId+")");
			return this;
		}
		
		public Criteria andCompanyNameLike(String companyName) {
			addCriterion(" EXISTS (select id from bu_mcht_info mi where mi.id=t.mcht_id  and mi.company_name like '%"+companyName+"%')");
			return this;
		}
		public Criteria andWhereClause(String andWhereClause) {
			addCriterion(andWhereClause);
			return this;
		}

		public Criteria andSettledTypeEqualTo(String settledType) {
			addCriterion(" EXISTS (select id from bu_mcht_info mi where mi.id=t.mcht_id and mi.settled_type ="+settledType+")");
			return this;
		}

		public Criteria andIsManageSelfEqualTo(String isManageSelf) {
			addCriterion(" EXISTS (select id from bu_mcht_info mi where mi.id=t.mcht_id and mi.is_manage_self ="+isManageSelf+")");
			return this;
		}
		
		
	}
}