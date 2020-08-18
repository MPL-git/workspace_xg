package com.jf.entity;

import com.jf.common.constant.Constant;

public class ProductAuditLogExt extends ProductAuditLog {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
