package com.jf.entity;

import com.jf.common.constant.Constant;

public class PlatformContactExt extends PlatformContact {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
