package com.jf.entity;

import com.jf.common.constant.Constant;

public class PropertyRightProsecutionLogExt extends PropertyRightProsecutionLog {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
