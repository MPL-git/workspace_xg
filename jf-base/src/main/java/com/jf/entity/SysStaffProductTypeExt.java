package com.jf.entity;

import com.jf.common.constant.Constant;

public class SysStaffProductTypeExt extends SysStaffProductType {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
