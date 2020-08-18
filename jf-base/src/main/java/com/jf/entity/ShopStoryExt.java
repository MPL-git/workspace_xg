package com.jf.entity;

import com.jf.common.constant.Constant;

public class ShopStoryExt extends ShopStory {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
