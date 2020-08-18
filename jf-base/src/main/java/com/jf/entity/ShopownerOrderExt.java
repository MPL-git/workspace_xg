package com.jf.entity;

import com.jf.common.constant.Constant;

public class ShopownerOrderExt extends ShopownerOrder {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
