package com.jf.entity;



public class StaffOpinionFeedbackCustomExample extends StaffOpinionFeedbackExample {

	@Override
	public StaffOpinionFeedbackCustomCriteria createCriteria() {
		StaffOpinionFeedbackCustomCriteria criteria = new StaffOpinionFeedbackCustomCriteria();
		if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
	}
	
	
	public class StaffOpinionFeedbackCustomCriteria extends StaffOpinionFeedbackExample.Criteria {
		
		public Criteria andCreateDateBegin(String BeginDate, String endDate) {
			addCriterion(" s.create_date >= '"+ BeginDate +"' and  s.create_date <= '"+ endDate +"' ");
			return this;
		}
		
	}
	
}
