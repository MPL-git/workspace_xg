package com.jf.entity;

import com.jf.common.constant.Constant;

public class ProductWeightExt extends ProductWeight {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
