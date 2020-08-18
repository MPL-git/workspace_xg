package com.jf.entity;



public class SpreadNameCustomExample extends SpreadNameExample{
	


	@Override
	public SpreadNameCustomCriteria createCriteria() {
		SpreadNameCustomCriteria criteria = new SpreadNameCustomCriteria();
		if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
	}
	
	
	public class SpreadNameCustomCriteria extends SpreadNameExample.Criteria {
		
		public Criteria andChannelIdEqualTo(String channel) {
			addCriterion(" EXISTS(SELECT sag.id from bu_spread_activity_group sag where sag.id=t.activity_group_id and sag.channel='"+channel+"')");
			return this;
		}
	}
	

	
}