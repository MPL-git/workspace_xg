package com.jf.entity;



public class VideoLikeCustomExample extends VideoLikeExample {
	@Override
    public VideoLikeCustomCriteria createCriteria() {
		VideoLikeCustomCriteria criteria = new VideoLikeCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class VideoLikeCustomCriteria extends VideoLikeCustomExample.Criteria{
		
	}
}