package com.jf.entity;

import com.jf.common.constant.Constant;

public class ActivityAreaExt extends ActivityArea {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
