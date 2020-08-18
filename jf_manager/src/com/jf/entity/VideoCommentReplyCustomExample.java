package com.jf.entity;



public class VideoCommentReplyCustomExample extends VideoCommentReplyExample {
	@Override
    public VideoCommentReplyCustomCriteria createCriteria() {
		VideoCommentReplyCustomCriteria criteria = new VideoCommentReplyCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class VideoCommentReplyCustomCriteria extends VideoCommentReplyCustomExample.Criteria{
		
	}
}