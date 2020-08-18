package com.jf.entity;

import com.jf.common.constant.Constant;

public class CouponExt extends Coupon {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
