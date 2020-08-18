package com.jf.entity;

import com.jf.common.constant.Constant;

public class InterventionOrderLogExt extends InterventionOrderLog {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
