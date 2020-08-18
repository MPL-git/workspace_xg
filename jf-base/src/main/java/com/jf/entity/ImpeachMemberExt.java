package com.jf.entity;

import com.jf.common.constant.Constant;

public class ImpeachMemberExt extends ImpeachMember {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
