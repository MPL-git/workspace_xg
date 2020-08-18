package com.jf.entity;

import com.jf.common.constant.Constant;

public class SysPaymentExt extends SysPayment {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
