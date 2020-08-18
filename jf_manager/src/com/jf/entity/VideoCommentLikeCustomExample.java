package com.jf.entity;



public class VideoCommentLikeCustomExample extends VideoCommentLikeExample {
	@Override
    public VideoCommentLikeCustomCriteria createCriteria() {
		VideoCommentLikeCustomCriteria criteria = new VideoCommentLikeCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class VideoCommentLikeCustomCriteria extends VideoCommentLikeCustomExample.Criteria{
		
	}
}