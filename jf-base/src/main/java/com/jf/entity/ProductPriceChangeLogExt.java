package com.jf.entity;

import com.jf.common.constant.Constant;

public class ProductPriceChangeLogExt extends ProductPriceChangeLog {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
