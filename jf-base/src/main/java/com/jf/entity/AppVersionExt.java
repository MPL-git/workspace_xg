package com.jf.entity;

import com.jf.common.constant.Constant;

public class AppVersionExt extends AppVersion {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
