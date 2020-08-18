package com.jf.entity;

import com.jf.common.constant.Constant;

public class VideoPvStatisticalExt extends VideoPvStatistical {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
