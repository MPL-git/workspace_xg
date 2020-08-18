package com.jf.entity;



public class VideoPvStatisticalCustomExample extends VideoPvStatisticalExample {
	@Override
    public VideoPvStatisticalCustomCriteria createCriteria() {
		VideoPvStatisticalCustomCriteria criteria = new VideoPvStatisticalCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class VideoPvStatisticalCustomCriteria extends VideoPvStatisticalCustomExample.Criteria{
		
	}
}