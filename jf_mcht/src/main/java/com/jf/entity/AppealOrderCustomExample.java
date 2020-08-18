package com.jf.entity;




public class AppealOrderCustomExample extends AppealOrderExample{
	
	@Override
    public AppealOrderCustomCriteria createCriteria() {
		AppealOrderCustomCriteria criteria = new AppealOrderCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class AppealOrderCustomCriteria extends AppealOrderExample.Criteria{
		public Criteria andSubOrderCodeEqualTo(String subOrderCode) {
			addCriterion("EXISTS(select id FROM bu_sub_order a WHERE a.id = t.sub_order_id and a.sub_order_code = '"+subOrderCode+"')");
			return this;
		}
		
	}
}