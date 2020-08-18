package com.jf.entity;

import com.jf.common.constant.Constant;

public class PropertyRightAppealLogExt extends PropertyRightAppealLog {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
