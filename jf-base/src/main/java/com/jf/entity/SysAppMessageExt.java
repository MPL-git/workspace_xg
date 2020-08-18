package com.jf.entity;

import com.jf.common.constant.Constant;

public class SysAppMessageExt extends SysAppMessage {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
