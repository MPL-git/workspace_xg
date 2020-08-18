package com.jf.entity;


public class PlatformPvStatisticalCustomExample extends PlatformPvStatisticalExample {

	@Override
    public PlatformPvStatisticalCustomCriteria createCriteria() {
		PlatformPvStatisticalCustomCriteria criteria = new PlatformPvStatisticalCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class PlatformPvStatisticalCustomCriteria extends PlatformPvStatisticalExample.Criteria {
		
	}
	
}
