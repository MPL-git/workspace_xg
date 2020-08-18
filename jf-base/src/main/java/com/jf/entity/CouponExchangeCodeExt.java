package com.jf.entity;

import com.jf.common.constant.Constant;

public class CouponExchangeCodeExt extends CouponExchangeCode {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
