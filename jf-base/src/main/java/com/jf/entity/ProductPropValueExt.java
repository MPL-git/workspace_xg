package com.jf.entity;

import com.jf.common.constant.Constant;

public class ProductPropValueExt extends ProductPropValue {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
