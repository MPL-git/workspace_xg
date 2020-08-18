package com.jf.entity;

import com.jf.common.constant.Constant;

public class SmsBlackIpExt extends SmsBlackIp {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
