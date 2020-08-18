package com.jf.entity;

import com.jf.common.constant.Constant;

public class VideoAttentionExt extends VideoAttention {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
