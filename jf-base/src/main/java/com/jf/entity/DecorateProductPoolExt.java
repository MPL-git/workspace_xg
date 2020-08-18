package com.jf.entity;

import com.jf.common.constant.Constant;

public class DecorateProductPoolExt extends DecorateProductPool {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
