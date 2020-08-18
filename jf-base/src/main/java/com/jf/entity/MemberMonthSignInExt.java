package com.jf.entity;

import com.jf.common.constant.Constant;

public class MemberMonthSignInExt extends MemberMonthSignIn {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
