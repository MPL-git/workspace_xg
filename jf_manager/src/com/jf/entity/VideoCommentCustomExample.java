package com.jf.entity;


public class VideoCommentCustomExample extends VideoCommentExample {
	@Override
    public VideoCommentCustomCriteria createCriteria() {
		VideoCommentCustomCriteria criteria = new VideoCommentCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class VideoCommentCustomCriteria extends VideoCommentCustomExample.Criteria{
		
		public Criteria andMchtCodeEqualTo(String mchtCode) {//商家序号
			addCriterion(" EXISTS(select mi.id from bu_mcht_info mi where mi.id=vcm.mcht_id and mi.del_flag='0' and mi.mcht_code='"+mchtCode+"')");
	        return this;
	    }
		
		
		public Criteria andVideoTitleEqualTo(String title) {//视频标题
			addCriterion(" vcm.video_id in(select v.id from bu_video v where vcm.video_id=v.id and v.del_flag='0' and v.title='"+title+"')");
	        return this;
	    }
		
	}
}