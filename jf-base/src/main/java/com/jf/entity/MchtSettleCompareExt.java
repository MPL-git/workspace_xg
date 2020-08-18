package com.jf.entity;

import com.jf.common.constant.Constant;

public class MchtSettleCompareExt extends MchtSettleCompare {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
