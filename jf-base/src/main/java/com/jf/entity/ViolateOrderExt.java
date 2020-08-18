package com.jf.entity;

import com.jf.common.constant.Constant;

public class ViolateOrderExt extends ViolateOrder {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
