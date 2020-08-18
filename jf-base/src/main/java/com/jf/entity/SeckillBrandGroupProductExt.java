package com.jf.entity;

import com.jf.common.constant.Constant;

public class SeckillBrandGroupProductExt extends SeckillBrandGroupProduct {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
