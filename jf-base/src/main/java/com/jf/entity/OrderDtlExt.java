package com.jf.entity;

import com.jf.common.constant.Constant;

public class OrderDtlExt extends OrderDtl {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
