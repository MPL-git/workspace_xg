package com.jf.entity;

import com.jf.common.constant.Constant;

public class MemberFootprintExt extends MemberFootprint {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
