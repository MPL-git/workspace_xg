package com.jf.entity;

import com.jf.common.constant.Constant;

public class MobileVerificationCodeExt extends MobileVerificationCode {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
