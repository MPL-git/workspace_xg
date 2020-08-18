package com.jf.entity;

import com.jf.common.constant.Constant;

public class CouponRainSetupExt extends CouponRainSetup {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
