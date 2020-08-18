package com.jf.entity;


public class WoRkCustomExample extends WoRkExample {

	@Override
	public WoRkCustomCriteria createCriteria() {
		WoRkCustomCriteria criteria = new WoRkCustomCriteria();
		if(oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}
	
	public class WoRkCustomCriteria extends WoRkExample.Criteria {//当前指派给我的或者曾经指派给我的
		
		public Criteria andstaffIdEqualTo(Integer staffId){
			addCriterion("(w.staff_id="+staffId+" or EXISTS(select wr.work_id from bu_work_record wr where wr.work_id=w.id and wr.del_flag='0' and wr.staff_id ="+staffId+"))");
			return this;
		}
		
	}
	
}
