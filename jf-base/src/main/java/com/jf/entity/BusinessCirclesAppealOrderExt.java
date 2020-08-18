package com.jf.entity;

import com.jf.common.constant.Constant;

public class BusinessCirclesAppealOrderExt extends BusinessCirclesAppealOrder {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
