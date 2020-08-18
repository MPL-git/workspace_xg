package com.jf.entity;

import com.jf.common.constant.Constant;

public class StaffMchtContactPermissionExt extends StaffMchtContactPermission {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
