package com.jf.entity;

import com.jf.common.constant.Constant;

public class ShopPreferentialInfoExt extends ShopPreferentialInfo {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
