package com.jf.entity;

import com.jf.common.constant.Constant;

public class MchtSupplierUserExt extends MchtSupplierUser {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
