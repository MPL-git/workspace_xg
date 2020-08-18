package com.jf.entity;

import com.jf.common.constant.Constant;

public class VideoCommentLikeExt extends VideoCommentLike {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
