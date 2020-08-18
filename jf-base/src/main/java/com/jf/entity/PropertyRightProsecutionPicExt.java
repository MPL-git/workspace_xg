package com.jf.entity;

import com.jf.common.constant.Constant;

public class PropertyRightProsecutionPicExt extends PropertyRightProsecutionPic {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
