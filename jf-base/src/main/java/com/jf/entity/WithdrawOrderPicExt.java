package com.jf.entity;

import com.jf.common.constant.Constant;

public class WithdrawOrderPicExt extends WithdrawOrderPic {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
