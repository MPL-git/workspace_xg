package com.jf.entity;

import com.jf.common.constant.Constant;

public class CloudProductPropValueExt extends CloudProductPropValue {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
