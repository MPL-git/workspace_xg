package com.jf.entity;

import com.jf.common.constant.Constant;

public class AppealLogExt extends AppealLog {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
