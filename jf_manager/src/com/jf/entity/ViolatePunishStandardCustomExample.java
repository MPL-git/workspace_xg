package com.jf.entity;




public class ViolatePunishStandardCustomExample extends ViolatePunishStandardExample{
	
	@Override
    public ViolatePunishStandardCustomCriteria createCriteria() {
		ViolatePunishStandardCustomCriteria criteria = new ViolatePunishStandardCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class ViolatePunishStandardCustomCriteria extends ViolatePunishStandardExample.Criteria{
		
	}
}