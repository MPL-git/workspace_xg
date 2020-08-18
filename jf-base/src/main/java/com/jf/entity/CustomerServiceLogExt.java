package com.jf.entity;

import com.jf.common.constant.Constant;

public class CustomerServiceLogExt extends CustomerServiceLog {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
