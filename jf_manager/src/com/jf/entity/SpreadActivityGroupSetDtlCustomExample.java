package com.jf.entity;



public class SpreadActivityGroupSetDtlCustomExample extends SpreadActivityGroupSetDtlExample {

	@Override
	public SpreadActivityGroupSetDtlCustomCriteria createCriteria() {
		SpreadActivityGroupSetDtlCustomCriteria criteria = new SpreadActivityGroupSetDtlCustomCriteria();
		if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
	}
	
	public class SpreadActivityGroupSetDtlCustomCriteria extends SpreadActivityGroupSetDtlExample.Criteria {

		public Criteria andSpreadActivityGroupSetStatusEqualTo() {
			addCriterion(" EXISTS(SELECT a.id from bu_spread_activity_group_set a where a.del_flag = '0' and a.status = '1' and a.id = t.spread_activity_group_set_id )");
			return this;
		}


		
	}
	
}
