package com.jf.entity;

import com.jf.common.constant.Constant;

public class ShopDecorateInfoExt extends ShopDecorateInfo {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
