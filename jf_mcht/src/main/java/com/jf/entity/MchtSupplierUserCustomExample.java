package com.jf.entity;



public class MchtSupplierUserCustomExample extends MchtSupplierUserExample {

	@Override
	public MchtSupplierUserCustomCriteria createCriteria() {
		MchtSupplierUserCustomCriteria criteria = new MchtSupplierUserCustomCriteria();
		if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
	}
	
	
	public class MchtSupplierUserCustomCriteria extends MchtSupplierUserExample.Criteria {
		
		public Criteria andSupplierStatusEqualTo(String status) {
			addCriterion(" EXISTS(select a.id from jybao.sp_office a where a.id = t.sp_office_id and a.del_flag = '0' and a.status = '"+status+"')");
			return this;
		}
		
	}
	
}
