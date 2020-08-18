package com.jf.entity;

import com.jf.common.constant.Constant;

public class WetaoChannelExt extends WetaoChannel {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
