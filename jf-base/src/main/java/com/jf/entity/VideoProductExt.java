package com.jf.entity;

import com.jf.common.constant.Constant;

public class VideoProductExt extends VideoProduct {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
