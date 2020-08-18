package com.jf.entity;

import com.jf.common.constant.Constant;

public class MemberBlackPicExt extends MemberBlackPic {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
