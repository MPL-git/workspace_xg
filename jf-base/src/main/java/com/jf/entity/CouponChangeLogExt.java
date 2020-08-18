package com.jf.entity;

import com.jf.common.constant.Constant;

public class CouponChangeLogExt extends CouponChangeLog {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
