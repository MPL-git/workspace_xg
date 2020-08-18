package com.jf.entity;

import com.jf.common.constant.Constant;

public class PropertyRightComplainExt extends PropertyRightComplain {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
