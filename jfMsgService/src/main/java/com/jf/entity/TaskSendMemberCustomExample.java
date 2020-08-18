package com.jf.entity;



public class TaskSendMemberCustomExample extends TaskSendMemberExample {

	@Override
    public TaskSendMemberCustomCriteria createCriteria() {
		TaskSendMemberCustomCriteria criteria = new TaskSendMemberCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class TaskSendMemberCustomCriteria extends TaskSendMemberExample.Criteria{
		
		public Criteria andOrSendDate() {
			addCriterion(" (t.send_date is null or t.send_date <= now())");
	        return this;
	    }
		
	}
	
	
}
