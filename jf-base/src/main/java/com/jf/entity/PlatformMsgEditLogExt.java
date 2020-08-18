package com.jf.entity;

import com.jf.common.constant.Constant;

public class PlatformMsgEditLogExt extends PlatformMsgEditLog {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
