package com.jf.entity;

import com.jf.common.constant.Constant;

public class VideoTipOffExt extends VideoTipOff {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
