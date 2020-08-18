package com.jf.entity;

import com.jf.common.constant.Constant;

public class MchtBrandProductTypeExt extends MchtBrandProductType {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
