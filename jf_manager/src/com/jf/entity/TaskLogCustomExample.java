package com.jf.entity;


public class TaskLogCustomExample extends TaskLogExample {

	@Override
	public TaskLogCustomCriteria createCriteria() {
		TaskLogCustomCriteria criteria = new TaskLogCustomCriteria();
		if(oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}
	
	public class TaskLogCustomCriteria extends TaskLogExample.Criteria {
		

	}
	
}
