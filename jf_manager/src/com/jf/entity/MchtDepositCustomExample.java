package com.jf.entity;

public class MchtDepositCustomExample extends MchtDepositExample {

	@Override
	public MchtDepositCustomCriteria createCriteria() {
		MchtDepositCustomCriteria criteria = new MchtDepositCustomCriteria();
		if(oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}
	
	public class MchtDepositCustomCriteria extends MchtDepositExample.Criteria {
		
		public Criteria andMchtStatusIn(String statusIn) {
			addCriterion(" EXISTS(select mi.id from bu_mcht_info mi where mi.del_flag = '0' and mi.id = mcht_id and mi.status in(" + statusIn + "))");
			return this;
		}
		
	}
	
}
