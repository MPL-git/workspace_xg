package com.jf.entity;

import com.jf.common.constant.Constant;

public class MemberAddressExt extends MemberAddress {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
