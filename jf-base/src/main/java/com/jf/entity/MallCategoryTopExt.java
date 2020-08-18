package com.jf.entity;

import com.jf.common.constant.Constant;

public class MallCategoryTopExt extends MallCategoryTop {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
