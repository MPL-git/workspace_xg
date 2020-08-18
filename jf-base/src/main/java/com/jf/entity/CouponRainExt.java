package com.jf.entity;

import com.jf.common.constant.Constant;

public class CouponRainExt extends CouponRain {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
