package com.jf.entity;

import com.jf.common.constant.Constant;

public class SingleProductActivityAuditLogExt extends SingleProductActivityAuditLog {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
