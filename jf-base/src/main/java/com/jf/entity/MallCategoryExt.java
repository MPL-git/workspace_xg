package com.jf.entity;

import com.jf.common.constant.Constant;

public class MallCategoryExt extends MallCategory {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
