package com.jf.entity;

import com.jf.common.constant.Constant;

public class WeixinXcxSprLogExt extends WeixinXcxSprLog {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
