package com.jf.entity;

import com.jf.common.constant.Constant;

public class ActivityProductDepositExt extends ActivityProductDeposit {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
