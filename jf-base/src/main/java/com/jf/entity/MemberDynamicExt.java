package com.jf.entity;

import com.jf.common.constant.Constant;

public class MemberDynamicExt extends MemberDynamic {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
