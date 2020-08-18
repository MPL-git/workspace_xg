package com.jf.entity;

import com.jf.common.constant.Constant;

public class AssistanceDtlExt extends AssistanceDtl {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
