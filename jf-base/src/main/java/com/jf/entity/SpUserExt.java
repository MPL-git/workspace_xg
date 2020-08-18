package com.jf.entity;

import com.jf.common.constant.Constant;

public class SpUserExt extends SpUser {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
