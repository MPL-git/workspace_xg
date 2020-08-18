package com.jf.entity;





public class ViolateOrderCustomExample extends ViolateOrderExample{
	
	@Override
    public ViolateOrderCustomCriteria createCriteria() {
		ViolateOrderCustomCriteria criteria = new ViolateOrderCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class ViolateOrderCustomCriteria extends ViolateOrderExample.Criteria{
		
		public Criteria andIsShamDeliveryOrder() {
			addCriterion(" EXISTS(select id from bu_order_abnormal_log oal where oal.sub_order_id=t.sub_order_id and oal.del_flag='0' and oal.abnormal_type='4')");
	        return this;
	    }
		
	}
}