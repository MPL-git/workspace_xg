package com.jf.entity;

import com.jf.common.constant.Constant;

public class ProductExt extends Product {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
