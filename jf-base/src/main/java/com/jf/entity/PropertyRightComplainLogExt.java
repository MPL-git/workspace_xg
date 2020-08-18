package com.jf.entity;

import com.jf.common.constant.Constant;

public class PropertyRightComplainLogExt extends PropertyRightComplainLog {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
