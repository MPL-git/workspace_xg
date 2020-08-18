package com.jf.entity;

import com.jf.common.constant.Constant;

public class DouyinSprDtlExt extends DouyinSprDtl {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
