package com.jf.entity;

import com.jf.common.constant.Constant;

public class MchtPlatformAuthPicExt extends MchtPlatformAuthPic {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
