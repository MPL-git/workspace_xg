package com.jf.entity;

import com.jf.common.constant.Constant;

public class WorkHistoryExt extends WorkHistory {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
