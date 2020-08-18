package com.jf.entity;

import com.jf.common.constant.Constant;

public class InterventionOrderStatusLogExt extends InterventionOrderStatusLog {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
