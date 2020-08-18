package com.jf.entity;



public class TaskCustomExample extends TaskExample {

	@Override
	public TaskCustomCriteria createCriteria() {
		TaskCustomCriteria criteria = new TaskCustomCriteria();
		if(oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}
	
	public class TaskCustomCriteria extends TaskCustomExample.Criteria {
		

	}
	
}
