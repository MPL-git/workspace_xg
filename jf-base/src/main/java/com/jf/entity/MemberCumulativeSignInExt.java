package com.jf.entity;

import com.jf.common.constant.Constant;

public class MemberCumulativeSignInExt extends MemberCumulativeSignIn {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
