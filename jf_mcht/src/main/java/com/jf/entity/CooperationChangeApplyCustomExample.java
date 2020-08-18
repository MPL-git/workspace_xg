package com.jf.entity;


public class CooperationChangeApplyCustomExample extends CooperationChangeApplyExample{
	
	@Override
    public CooperationChangeApplyCustomCriteria createCriteria() {
		CooperationChangeApplyCustomCriteria criteria = new CooperationChangeApplyCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class CooperationChangeApplyCustomCriteria extends CooperationChangeApplyExample.Criteria{

		
		public Criteria andZsAuditStatusOrFwAuditStatusEquals(String string) {
			addCriterion(" (t.zs_audit_status = '2' or t.fw_audit_status = '2')");
			return this;
		}

		public Criteria andChangeApplyTypeEqualTo(String changeApplyType) {
			addCriterion(" EXISTS(select mi.id from bu_mcht_info mi where mi.del_flag = '0' and mi.id = t.mcht_id and FIND_IN_SET("+changeApplyType+",mi.change_apply_type))");
			return this;
		}

		public Criteria andArchiveStatusNotEqualOrNull() {
			addCriterion(" (t.archive_status is null or t.archive_status !=1)");
			return this;
		}
		
		
	}
}