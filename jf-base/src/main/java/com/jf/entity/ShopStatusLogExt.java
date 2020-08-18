package com.jf.entity;

import com.jf.common.constant.Constant;

public class ShopStatusLogExt extends ShopStatusLog {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
