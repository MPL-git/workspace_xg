package com.jf.entity;

import com.jf.common.constant.Constant;

public class SalesmanExt extends Salesman {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
