package com.jf.entity;

import com.jf.common.constant.Constant;

public class IntegralDtlExt extends IntegralDtl {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
