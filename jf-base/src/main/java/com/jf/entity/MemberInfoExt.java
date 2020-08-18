package com.jf.entity;

import com.jf.common.constant.Constant;

public class MemberInfoExt extends MemberInfo {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
