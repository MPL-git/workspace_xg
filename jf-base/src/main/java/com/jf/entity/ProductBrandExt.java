package com.jf.entity;

import com.jf.common.constant.Constant;

public class ProductBrandExt extends ProductBrand {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
