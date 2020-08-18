package com.jf.entity;

import com.jf.common.constant.Constant;

public class GrowthDtlExt extends GrowthDtl {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
