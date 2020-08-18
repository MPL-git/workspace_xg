package com.jf.entity;

import com.jf.common.constant.Constant;

public class ShopownerExt extends Shopowner {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
