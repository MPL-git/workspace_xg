package com.jf.entity;

import com.jf.common.constant.Constant;

public class MemberRemindExt extends MemberRemind {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
