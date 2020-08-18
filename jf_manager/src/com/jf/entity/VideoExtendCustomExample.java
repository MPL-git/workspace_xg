package com.jf.entity;



public class VideoExtendCustomExample extends VideoExtendExample {
	@Override
    public VideoExtendCustomCriteria createCriteria() {
		VideoExtendCustomCriteria criteria = new VideoExtendCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class VideoExtendCustomCriteria extends VideoExtendCustomExample.Criteria{
		
	}
}