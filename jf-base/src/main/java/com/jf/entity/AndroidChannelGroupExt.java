package com.jf.entity;

import com.jf.common.constant.Constant;

public class AndroidChannelGroupExt extends AndroidChannelGroup {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
