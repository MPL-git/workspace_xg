package com.jf.entity;

import com.jf.common.constant.Constant;

public class ShopStoryDetailExt extends ShopStoryDetail {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
