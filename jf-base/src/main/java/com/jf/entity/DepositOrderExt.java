package com.jf.entity;

import com.jf.common.constant.Constant;

public class DepositOrderExt extends DepositOrder {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
