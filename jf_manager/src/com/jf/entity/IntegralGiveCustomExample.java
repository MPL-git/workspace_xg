package com.jf.entity;

public class IntegralGiveCustomExample extends IntegralGiveExample{
	
	@Override
    public IntegralGiveCustomCriteria createCriteria() {
		IntegralGiveCustomCriteria criteria = new IntegralGiveCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class IntegralGiveCustomCriteria extends IntegralGiveExample.Criteria{
		public Criteria andStaffNameEqualTo(String staffName) {
			addCriterion(" EXISTS(select id from sys_staff_info mi where mi.staff_id=create_by and mi.staff_name='"+staffName+"')");
	        return this;
	    }
		
	}
}