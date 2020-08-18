package com.jf.entity;

import com.jf.common.constant.Constant;

public class PaymentPlatformReceivableDtlExt extends PaymentPlatformReceivableDtl {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
