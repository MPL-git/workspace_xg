package com.jf.entity;

import com.jf.common.constant.Constant;

public class ReportGdtLogExt extends ReportGdtLog {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
