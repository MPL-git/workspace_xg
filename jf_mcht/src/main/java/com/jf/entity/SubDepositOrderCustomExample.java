package com.jf.entity;




public class SubDepositOrderCustomExample extends SubDepositOrderExample{
	@Override
    public SubDepositOrderCustomCriteria createCriteria() {
		SubDepositOrderCustomCriteria criteria = new SubDepositOrderCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class SubDepositOrderCustomCriteria extends SubDepositOrderExample.Criteria{
		public Criteria andProductCodeEqualTo(String productCode) {
			addCriterion("EXISTS(select id FROM bu_product p WHERE p.id = t.product_id and p.code = '"+productCode+"')");
	        return this;
	    }
	}
}