package com.jf.entity;

import com.jf.common.constant.Constant;

public class SubDepositOrderExt extends SubDepositOrder {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
