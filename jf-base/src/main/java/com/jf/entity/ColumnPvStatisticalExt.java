package com.jf.entity;

import com.jf.common.constant.Constant;

public class ColumnPvStatisticalExt extends ColumnPvStatistical {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
