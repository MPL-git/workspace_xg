package com.jf.entity;

import com.jf.common.constant.Constant;

public class InterventionProcessExt extends InterventionProcess {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
