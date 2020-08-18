package com.jf.entity;

import com.jf.common.constant.Constant;

public class ShopDecorateAreaExt extends ShopDecorateArea {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
