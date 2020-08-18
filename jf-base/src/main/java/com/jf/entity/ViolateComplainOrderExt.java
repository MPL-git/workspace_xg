package com.jf.entity;

import com.jf.common.constant.Constant;

public class ViolateComplainOrderExt extends ViolateComplainOrder {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
