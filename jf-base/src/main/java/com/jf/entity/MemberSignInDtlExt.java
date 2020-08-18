package com.jf.entity;

import com.jf.common.constant.Constant;

public class MemberSignInDtlExt extends MemberSignInDtl {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
