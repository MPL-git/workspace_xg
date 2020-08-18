package com.jf.entity;

import com.jf.common.constant.Constant;

public class SysAppMessageMemberExt extends SysAppMessageMember {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
