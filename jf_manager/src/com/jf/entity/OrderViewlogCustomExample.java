package com.jf.entity;


public class OrderViewlogCustomExample extends OrderViewlogExample {

	@Override
	public OrderViewlogCustomCriteria createCriteria() {
		OrderViewlogCustomCriteria criteria = new OrderViewlogCustomCriteria();
		if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
	}
	
    public class OrderViewlogCustomCriteria extends OrderViewlogExample.Criteria {
		
    	public Criteria andstaffNameLikeTo(String staffName) {
			addCriterion(" ov.create_by in (select ss.STAFF_ID from sys_staff_info ss where ov.create_by=ss.STAFF_ID  and ss.STATUS='A' and ss.STAFF_NAME like '%"+staffName+"%')");
	        return this;
	    }
    	
    	public Criteria andMchtCodeEqualTo(String mchtCode) {
			addCriterion(" ov.order_id in(select so.id from bu_mcht_info mi,bu_sub_order so where mi.id=so.mcht_id and mi.del_flag='0' and so.del_flag='0' and ov.order_id=so.id and mi.mcht_code='"+mchtCode+"')");
	        return this;
	    }
    	
    	public Criteria andMchtNameLikeTo(String mchtName) {
			addCriterion(" ov.order_id in (select so.id from bu_mcht_info mi,bu_sub_order so where mi.del_flag='0' and so.del_flag='0' and mi.id=so.mcht_id and ov.order_id=so.id and ( mi.short_name like '%"+mchtName+"%' or mi.company_name like '%"+mchtName+"%' or mi.shop_name like '%"+mchtName+"%' ))");
	        return this;
	    }
		
		
	}
	
}
