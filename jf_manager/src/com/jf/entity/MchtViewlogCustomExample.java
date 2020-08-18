package com.jf.entity;


public class MchtViewlogCustomExample extends MchtViewlogExample {

	@Override
	public MchtViewlogCustomCriteria createCriteria() {
		MchtViewlogCustomCriteria criteria = new MchtViewlogCustomCriteria();
		if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
	}
	
    public class MchtViewlogCustomCriteria extends MchtViewlogExample.Criteria {
		
    	public Criteria andstaffNameLikeTo(String staffName) {
			addCriterion(" mv.create_by in (select ss.STAFF_ID from sys_staff_info ss where mv.create_by=ss.STAFF_ID  and ss.STATUS='A' and ss.STAFF_NAME like '%"+staffName+"%')");
	        return this;
	    }
    	
    	public Criteria andMchtCodeEqualTo(String mchtCode) {
			addCriterion(" mv.mcht_id in(select mi.id from bu_mcht_info mi where mi.del_flag='0' and mv.mcht_id=mi.id and mi.mcht_code='"+mchtCode+"')");
	        return this;
	    }
    	
    	public Criteria andMchtNameLikeTo(String mchtName) {
			addCriterion(" mv.mcht_id in (select mi.id from bu_mcht_info mi where mi.del_flag='0' and mi.id=mv.mcht_id and ( mi.short_name like '%"+mchtName+"%' or mi.company_name like '%"+mchtName+"%' or mi.shop_name like '%"+mchtName+"%' ))");
	        return this;
	    }
	}
	
}
