package com.jf.entity;

import com.jf.common.constant.Constant;

public class SupplierProductBrandExt extends SupplierProductBrand {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
