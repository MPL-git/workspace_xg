package com.jf.entity;



public class TaskSmsCustomExample extends TaskSmsExample {

	@Override
	public TaskSmsCustomCriteria createCriteria() {
		TaskSmsCustomCriteria criteria = new TaskSmsCustomCriteria();
		if(oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}
	
	public class TaskSmsCustomCriteria extends TaskSmsExample.Criteria {
		
		public Criteria andCustomSQL(String sql) {
			addCriterion(sql);
			return this;
		}
		

	}
	
}
