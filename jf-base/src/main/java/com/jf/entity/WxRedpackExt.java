package com.jf.entity;

import com.jf.common.constant.Constant;

public class WxRedpackExt extends WxRedpack {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
