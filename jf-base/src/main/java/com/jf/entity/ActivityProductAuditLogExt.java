package com.jf.entity;

import com.jf.common.constant.Constant;

public class ActivityProductAuditLogExt extends ActivityProductAuditLog {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
