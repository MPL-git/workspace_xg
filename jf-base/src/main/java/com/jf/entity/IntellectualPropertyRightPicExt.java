package com.jf.entity;

import com.jf.common.constant.Constant;

public class IntellectualPropertyRightPicExt extends IntellectualPropertyRightPic {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
