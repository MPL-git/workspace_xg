package com.jf.entity;

import com.jf.common.constant.Constant;

public class PropertyRightProsecutionExt extends PropertyRightProsecution {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
