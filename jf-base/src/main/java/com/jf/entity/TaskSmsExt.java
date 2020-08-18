package com.jf.entity;

import com.jf.common.constant.Constant;

public class TaskSmsExt extends TaskSms {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
