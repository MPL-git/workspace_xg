package com.jf.entity;

import com.jf.common.constant.Constant;

public class StyleExt extends Style {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
