package com.jf.entity;

import com.jf.common.constant.Constant;

public class ActivityAuditLogExt extends ActivityAuditLog {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
