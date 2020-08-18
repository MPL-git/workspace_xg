package com.jf.entity;

import com.jf.common.constant.Constant;

public class ProductVideoExt extends ProductVideo {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
