package com.jf.entity;

import com.jf.common.constant.Constant;

public class MemberPvMiddleExt extends MemberPvMiddle {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
