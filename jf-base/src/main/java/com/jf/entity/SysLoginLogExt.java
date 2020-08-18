package com.jf.entity;

import com.jf.common.constant.Constant;

public class SysLoginLogExt extends SysLoginLog {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
