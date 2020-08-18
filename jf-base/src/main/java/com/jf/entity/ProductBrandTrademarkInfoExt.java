package com.jf.entity;

import com.jf.common.constant.Constant;

public class ProductBrandTrademarkInfoExt extends ProductBrandTrademarkInfo {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
