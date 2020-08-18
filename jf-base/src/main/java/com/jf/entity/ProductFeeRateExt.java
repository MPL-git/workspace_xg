package com.jf.entity;

import com.jf.common.constant.Constant;

public class ProductFeeRateExt extends ProductFeeRate {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
