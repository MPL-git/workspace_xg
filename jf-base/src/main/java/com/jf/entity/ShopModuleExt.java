package com.jf.entity;

import com.jf.common.constant.Constant;

public class ShopModuleExt extends ShopModule {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
