package com.jf.entity;

import com.jf.common.constant.Constant;

public class MemberCumulativeSignInCopyExt extends MemberCumulativeSignInCopy {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
