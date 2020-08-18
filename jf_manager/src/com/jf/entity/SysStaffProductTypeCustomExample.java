package com.jf.entity;

public class SysStaffProductTypeCustomExample extends SysStaffProductTypeExample {

	@Override
	public SysStaffProductTypeCustomCriteria createCriteria() {
		SysStaffProductTypeCustomCriteria criteria = new SysStaffProductTypeCustomCriteria();
		if(oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}
	
	public class SysStaffProductTypeCustomCriteria extends SysStaffProductTypeExample.Criteria {
		//如需只呈现品类状态为正常的类目
		public  Criteria  andProductTypeStatus(){
			addCriterion("exists(select id FROM bu_product_type pt  WHERE pt.id = s.product_type_id and pt.status = '1')");
			return this;
		}
	}
	
}
