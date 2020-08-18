package com.jf.entity;

import com.jf.common.constant.Constant;

public class MemberPvDtlExt extends MemberPvDtl {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
