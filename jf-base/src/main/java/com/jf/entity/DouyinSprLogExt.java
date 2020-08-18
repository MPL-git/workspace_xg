package com.jf.entity;

import com.jf.common.constant.Constant;

public class DouyinSprLogExt extends DouyinSprLog {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
