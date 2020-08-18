package com.jf.entity;

import com.jf.common.constant.Constant;

public class ShoppingCartExt extends ShoppingCart {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
