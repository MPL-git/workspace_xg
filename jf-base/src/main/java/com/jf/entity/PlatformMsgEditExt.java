package com.jf.entity;

import com.jf.common.constant.Constant;

public class PlatformMsgEditExt extends PlatformMsgEdit {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
