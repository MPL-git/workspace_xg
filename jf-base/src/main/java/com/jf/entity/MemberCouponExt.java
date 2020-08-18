package com.jf.entity;

import com.jf.common.constant.Constant;

public class MemberCouponExt extends MemberCoupon {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
