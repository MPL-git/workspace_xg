package com.jf.entity;

import com.jf.common.constant.Constant;

public class ZsProductTypeExt extends ZsProductType {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
