package com.jf.entity;

import com.jf.common.constant.Constant;

public class MemberAttentionExt extends MemberAttention {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
