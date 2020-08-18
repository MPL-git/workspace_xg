package com.jf.entity;

import com.jf.common.constant.Constant;

public class MchtCloseApplicationExt extends MchtCloseApplication {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
