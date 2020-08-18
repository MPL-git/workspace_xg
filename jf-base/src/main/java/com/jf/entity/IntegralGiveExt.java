package com.jf.entity;

import com.jf.common.constant.Constant;

public class IntegralGiveExt extends IntegralGive {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
