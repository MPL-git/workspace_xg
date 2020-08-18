package com.jf.entity;

import com.jf.common.constant.Constant;

public class ThirdPlatformProductInfoExt extends ThirdPlatformProductInfo {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
