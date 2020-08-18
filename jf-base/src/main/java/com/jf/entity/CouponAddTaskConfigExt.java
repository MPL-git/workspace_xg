package com.jf.entity;

import com.jf.common.constant.Constant;

public class CouponAddTaskConfigExt extends CouponAddTaskConfig {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
