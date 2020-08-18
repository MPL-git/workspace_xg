package com.jf.entity;



public class VideoAttentionCustomExample extends VideoAttentionExample {
	@Override
    public VideoAttentionCustomCriteria createCriteria() {
		VideoAttentionCustomCriteria criteria = new VideoAttentionCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class VideoAttentionCustomCriteria extends VideoAttentionCustomExample.Criteria{
		
	}
}