package com.jf.entity;

import com.jf.common.constant.Constant;

public class MemberCouponLogExt extends MemberCouponLog {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
