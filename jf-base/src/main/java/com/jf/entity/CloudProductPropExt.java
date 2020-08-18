package com.jf.entity;

import com.jf.common.constant.Constant;

public class CloudProductPropExt extends CloudProductProp {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
