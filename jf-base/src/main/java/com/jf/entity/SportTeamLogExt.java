package com.jf.entity;

import com.jf.common.constant.Constant;

public class SportTeamLogExt extends SportTeamLog {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
