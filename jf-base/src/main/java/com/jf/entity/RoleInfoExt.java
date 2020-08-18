package com.jf.entity;

import com.jf.common.constant.Constant;

public class RoleInfoExt extends RoleInfo {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
