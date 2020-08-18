package com.jf.entity;

import com.jf.common.constant.Constant;

public class ShopModulePicMapExt extends ShopModulePicMap {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
