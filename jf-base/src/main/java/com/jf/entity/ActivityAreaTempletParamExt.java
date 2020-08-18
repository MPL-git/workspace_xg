package com.jf.entity;

import com.jf.common.constant.Constant;

public class ActivityAreaTempletParamExt extends ActivityAreaTempletParam {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
