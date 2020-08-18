package com.jf.entity;

import com.jf.common.constant.Constant;

public class SobotCustomerServiceExt extends SobotCustomerService {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
