package com.jf.entity;

import com.jf.common.constant.Constant;

public class InterventionProcessPicExt extends InterventionProcessPic {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
