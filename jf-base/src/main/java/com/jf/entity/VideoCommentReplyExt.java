package com.jf.entity;

import com.jf.common.constant.Constant;

public class VideoCommentReplyExt extends VideoCommentReply {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
