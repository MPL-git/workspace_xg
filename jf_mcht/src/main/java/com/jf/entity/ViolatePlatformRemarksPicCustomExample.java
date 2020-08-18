package com.jf.entity;



public class ViolatePlatformRemarksPicCustomExample extends ViolatePlatformRemarksPicExample{
	
	@Override
    public ViolatePlatformRemarksPicCustomCriteria createCriteria() {
		ViolatePlatformRemarksPicCustomCriteria criteria = new ViolatePlatformRemarksPicCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class ViolatePlatformRemarksPicCustomCriteria extends ViolatePlatformRemarksPicExample.Criteria{
		
		
	}
}