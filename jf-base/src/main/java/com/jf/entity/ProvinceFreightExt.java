package com.jf.entity;

import com.jf.common.constant.Constant;

public class ProvinceFreightExt extends ProvinceFreight {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
