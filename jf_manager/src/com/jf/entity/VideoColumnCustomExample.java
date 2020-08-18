package com.jf.entity;



public class VideoColumnCustomExample extends VideoColumnExample {
	@Override
    public VideoColumnCustomCriteria createCriteria() {
		VideoColumnCustomCriteria criteria = new VideoColumnCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class VideoColumnCustomCriteria extends VideoColumnCustomExample.Criteria{
		
	}
}