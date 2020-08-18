package com.jf.entity;

import com.jf.common.constant.Constant;

public class VideoExtendExt extends VideoExtend {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
