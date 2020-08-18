package com.jf.entity;



public class VideoPlayRecordCustomExample extends VideoPlayRecordExample {
	@Override
    public VideoPlayRecordCustomCriteria createCriteria() {
		VideoPlayRecordCustomCriteria criteria = new VideoPlayRecordCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class VideoPlayRecordCustomCriteria extends VideoPlayRecordCustomExample.Criteria{
		
	}
}