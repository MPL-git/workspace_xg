package com.jf.entity;

import com.jf.common.constant.Constant;

public class VideoExt extends Video {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
