package com.jf.entity;

import com.jf.common.constant.Constant;

public class SmsExt extends Sms {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
