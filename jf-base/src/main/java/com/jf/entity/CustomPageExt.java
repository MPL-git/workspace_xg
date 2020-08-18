package com.jf.entity;

import com.jf.common.constant.Constant;

public class CustomPageExt extends CustomPage {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
