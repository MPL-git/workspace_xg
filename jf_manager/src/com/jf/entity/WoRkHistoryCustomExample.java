package com.jf.entity;

public class WoRkHistoryCustomExample extends WorkHistoryExample {

	@Override
	public WoRkHistoryCustomCriteria createCriteria() {
		WoRkHistoryCustomCriteria criteria = new WoRkHistoryCustomCriteria();
		if(oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}
	
	public class WoRkHistoryCustomCriteria extends WorkHistoryExample.Criteria {
		
	}
	
}
