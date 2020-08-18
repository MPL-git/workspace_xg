package com.jf.entity;

import com.jf.common.constant.Constant;

public class WithdrawOrderStatusLogExt extends WithdrawOrderStatusLog {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
