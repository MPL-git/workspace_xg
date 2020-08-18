package com.jf.entity;

import com.jf.common.constant.Constant;

public class InterventionOrderExt extends InterventionOrder {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
