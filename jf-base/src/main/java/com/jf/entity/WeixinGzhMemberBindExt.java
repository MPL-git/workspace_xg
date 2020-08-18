package com.jf.entity;

import com.jf.common.constant.Constant;

public class WeixinGzhMemberBindExt extends WeixinGzhMemberBind {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
