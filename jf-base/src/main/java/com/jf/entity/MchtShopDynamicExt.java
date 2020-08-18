package com.jf.entity;

import com.jf.common.constant.Constant;

public class MchtShopDynamicExt extends MchtShopDynamic {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
