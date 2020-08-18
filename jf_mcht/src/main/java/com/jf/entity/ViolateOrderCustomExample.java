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
		public Criteria andActivityIdEqualTo(Integer activityId) {
			addCriterion("EXISTS(select b.id FROM bu_order_dtl a,bu_sub_order b WHERE a.sub_order_id = b.id and a.sub_order_id = t.sub_order_id and a.activity_id = "+activityId+")");
			return this;
		}
		
	}
}