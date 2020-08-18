package com.jf.entity;

import com.jf.common.constant.Constant;

public class FullDiscountExt extends FullDiscount {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
