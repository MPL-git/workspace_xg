package com.jf.entity;

import com.jf.common.constant.Constant;

public class MallCategoryItemExt extends MallCategoryItem {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
