package com.jf.entity;

import com.jf.common.constant.Constant;

public class ProductItemExt extends ProductItem {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
