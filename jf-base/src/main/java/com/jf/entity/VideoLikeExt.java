package com.jf.entity;

import com.jf.common.constant.Constant;

public class VideoLikeExt extends VideoLike {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
