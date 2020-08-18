package com.jf.entity;

import com.jf.common.constant.Constant;

public class MchtSettledApplyExt extends MchtSettledApply {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
