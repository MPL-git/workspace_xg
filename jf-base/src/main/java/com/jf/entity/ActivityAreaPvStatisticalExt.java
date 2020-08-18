package com.jf.entity;

import com.jf.common.constant.Constant;

public class ActivityAreaPvStatisticalExt extends ActivityAreaPvStatistical {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
