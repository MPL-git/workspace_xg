package com.jf.entity;

import com.jf.common.constant.Constant;

public class SvipPracticeRecordExt extends SvipPracticeRecord {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
