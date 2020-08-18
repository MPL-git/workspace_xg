package com.jf.entity;

import com.jf.common.constant.Constant;

public class SpreadActivityGroupSetExt extends SpreadActivityGroupSet {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
