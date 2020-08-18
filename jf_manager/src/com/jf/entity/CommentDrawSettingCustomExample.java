package com.jf.entity;



public class CommentDrawSettingCustomExample extends CommentDrawSettingExample {

	@Override
    public CommentDrawSettingCustomCriteria createCriteria() {
		CommentDrawSettingCustomCriteria criteria = new CommentDrawSettingCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

	public class CommentDrawSettingCustomCriteria extends Criteria{
		
		public Criteria validateDate(String beginDate, String endDate) {
			addCriterion(" ((t.begin_date > '"+beginDate+"' and t.begin_date < '"+endDate+"')"
							+ " or (t.end_date > '"+beginDate+"' and t.end_date < '"+endDate+"')"
							+ " or (t.begin_date <= '"+beginDate+"' and t.end_date >= '"+endDate+"'))");
			return this;
		}
		
		
	}
	
	
	
}
