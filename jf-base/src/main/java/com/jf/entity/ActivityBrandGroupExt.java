package com.jf.entity;

import com.jf.common.constant.Constant;

public class ActivityBrandGroupExt extends ActivityBrandGroup {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
