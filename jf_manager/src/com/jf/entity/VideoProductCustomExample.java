package com.jf.entity;



public class VideoProductCustomExample extends VideoProductExample {
	@Override
    public VideoProductCustomCriteria createCriteria() {
		VideoProductCustomCriteria criteria = new VideoProductCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class VideoProductCustomCriteria extends VideoProductCustomExample.Criteria{
		
	}
}