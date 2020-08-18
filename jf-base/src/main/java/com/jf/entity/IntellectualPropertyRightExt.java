package com.jf.entity;

import com.jf.common.constant.Constant;

public class IntellectualPropertyRightExt extends IntellectualPropertyRight {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
