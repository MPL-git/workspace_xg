package com.jf.entity;

import com.jf.common.constant.Constant;

public class MchtSettledApplyRecordExt extends MchtSettledApplyRecord {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
