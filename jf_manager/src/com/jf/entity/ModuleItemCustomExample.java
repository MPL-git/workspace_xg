package com.jf.entity;


public class ModuleItemCustomExample extends ModuleItemExample{
	
	@Override
    public ModuleItemCustomCriteria createCriteria() {
		ModuleItemCustomCriteria criteria = new ModuleItemCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class ModuleItemCustomCriteria extends ModuleItemExample.Criteria{
		public Criteria andProductActivityStatusEqualTo(String productActivityStatus) {
			addCriterion(" FUN_GET_PRODUCT_ACTIVITY_STATUS(t.item_id)='"+productActivityStatus+"'");
	        return this;
	    }
	}
}