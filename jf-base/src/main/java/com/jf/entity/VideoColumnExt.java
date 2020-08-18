package com.jf.entity;

import com.jf.common.constant.Constant;

public class VideoColumnExt extends VideoColumn {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
