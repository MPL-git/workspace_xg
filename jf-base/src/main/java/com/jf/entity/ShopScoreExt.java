package com.jf.entity;

import com.jf.common.constant.Constant;

public class ShopScoreExt extends ShopScore {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
