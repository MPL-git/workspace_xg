package com.jf.entity;

import com.jf.common.constant.Constant;

public class HelpTagExt extends HelpTag {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
