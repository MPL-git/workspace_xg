package com.jf.entity;

import com.jf.common.constant.Constant;

public class CloudProductItemExt extends CloudProductItem {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
