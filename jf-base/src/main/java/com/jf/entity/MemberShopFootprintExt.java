package com.jf.entity;

import com.jf.common.constant.Constant;

public class MemberShopFootprintExt extends MemberShopFootprint {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
