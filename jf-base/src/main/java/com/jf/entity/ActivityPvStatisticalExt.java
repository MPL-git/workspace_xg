package com.jf.entity;

import com.jf.common.constant.Constant;

public class ActivityPvStatisticalExt extends ActivityPvStatistical {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
