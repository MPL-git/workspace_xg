package com.jf.entity;

import com.jf.common.constant.Constant;

public class PropertyRightComplainPicExt extends PropertyRightComplainPic {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
