package com.jf.entity;

import com.jf.common.constant.Constant;

public class SaleWeightCnfExt extends SaleWeightCnf {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
