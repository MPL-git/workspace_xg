package com.jf.entity;



public class SpUserCustomExample extends SpUserExample {

	@Override
	public SpUserCustomCriteria createCriteria() {
		SpUserCustomCriteria criteria = new SpUserCustomCriteria();
		if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
	}
	
	
	public class SpUserCustomCriteria extends SpUserExample.Criteria {

		public Criteria andMchtIdFindInSet(int mchtId) {
			addCriterion(" FIND_IN_SET("+mchtId+",t.mcht_ids)");
	        return this;
		}
		
	}
	
}
