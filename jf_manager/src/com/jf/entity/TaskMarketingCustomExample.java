package com.jf.entity;



public class TaskMarketingCustomExample extends TaskMarketingExample {

	@Override
	public TaskMarketingCustomCriteria createCriteria() {
		TaskMarketingCustomCriteria criteria = new TaskMarketingCustomCriteria();
		if(oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}
	
	public class TaskMarketingCustomCriteria extends TaskMarketingExample.Criteria {
		
		public Criteria andCustomSQL(String sql) {
			addCriterion(sql);
			return this;
		}

	}
	
}
