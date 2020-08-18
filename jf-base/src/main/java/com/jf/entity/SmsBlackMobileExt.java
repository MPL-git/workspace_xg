package com.jf.entity;

import com.jf.common.constant.Constant;

public class SmsBlackMobileExt extends SmsBlackMobile {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
