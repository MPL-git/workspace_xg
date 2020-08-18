package com.jf.entity;

import com.jf.common.constant.Constant;

public class MemberLabelGroupExt extends MemberLabelGroup {

	public boolean isDeleted(){
		return Constant.FLAG_TRUE.equals(getDelFlag());
	}


}
