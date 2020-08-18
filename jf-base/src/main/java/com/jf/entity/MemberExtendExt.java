package com.jf.entity;

import com.jf.common.constant.Constant;

public class MemberExtendExt extends MemberExtend {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
