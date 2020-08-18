package com.jf.entity;

import com.jf.common.constant.Constant;

public class SpreadActivityGroupExt extends SpreadActivityGroup {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
