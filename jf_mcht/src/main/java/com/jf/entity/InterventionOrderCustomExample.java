package com.jf.entity;




public class InterventionOrderCustomExample extends InterventionOrderExample{
	
	@Override
    public InterventionOrderCustomCriteria createCriteria() {
		InterventionOrderCustomCriteria criteria = new InterventionOrderCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class InterventionOrderCustomCriteria extends InterventionOrderExample.Criteria{
		public Criteria andSubOrderCodeEqualTo(String subOrderCode) {
			addCriterion("EXISTS(select id FROM bu_sub_order a WHERE a.id = t.sub_order_id and a.sub_order_code = '"+subOrderCode+"')");
			return this;
		}

		public Criteria andCustomerServiceOrderCodeEqualTo(String customerServiceOrderCode) {
			addCriterion("EXISTS(select id FROM bu_customer_service_order cso WHERE cso.id = t.service_order_id and cso.order_code = '"+customerServiceOrderCode+"')");
			return this;
		}
		
	}
}