package com.jf.entity;

import com.jf.common.constant.Constant;

public class CombineDepositOrderExt extends CombineDepositOrder {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
