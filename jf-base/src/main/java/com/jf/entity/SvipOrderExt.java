package com.jf.entity;

import com.jf.common.constant.Constant;

public class SvipOrderExt extends SvipOrder {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
